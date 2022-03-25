package com.lizijing.carrental.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizijing.carrental.entity.bean.Car;
import com.lizijing.carrental.entity.bean.Order;
import com.lizijing.carrental.entity.bean.Store;
import com.lizijing.carrental.entity.enums.StatusEnum;
import com.lizijing.carrental.entity.vo.AccidentAddVO;
import com.lizijing.carrental.entity.vo.OrderAddVO;
import com.lizijing.carrental.entity.vo.OrderFinishVO;
import com.lizijing.carrental.entity.vo.OrderUpdateVO;
import com.lizijing.carrental.exception.ImplException;
import com.lizijing.carrental.mapper.OrderMapper;
import com.lizijing.carrental.result.CommonResult;
import com.lizijing.carrental.result.ResultCode;
import com.lizijing.carrental.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author LiZijing
 * @since 2022-02-27
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private CarServiceImpl carService;

    @Resource
    private StoreServiceImpl storeService;

    @Resource
    private AccidentServiceImpl accidentService;

    @Resource
    private OrderMapper orderMapper;

    @Override
    public CommonResult<Map<Object, Object>> addOne(OrderAddVO orderAddVO) {
        Map<Object, Object> res = new HashMap<>(8);
        // 检查车辆状态与所在地
        Car useCar = carService.getById(orderAddVO.getCarId());
        Store startStore = storeService.getById(orderAddVO.getStartStoreId());
        if (!useCar.getStatus().equals(StatusEnum.CAR_NORMAL.status)) {
            throw new ImplException(ResultCode.CAR_STATUS_ERROR);
        } else if (!useCar.getStoreName().equals(startStore.getStoreName())) {
            throw new ImplException(ResultCode.ORDER_NOT_IN_STORE);
        }
        // 门店库存修改
        if (!storeService.reduceCarStock(startStore.getStoreName())) {
            throw new ImplException(ResultCode.STORE_ERROR);
        }
        // 车辆状态修改
        if (!carService.changeStatus(useCar.getId(), StatusEnum.CAR_USING.status)) {
            throw new ImplException(ResultCode.CAR_STATUS_ERROR);
        }
        // 创建订单
        Order addOrder = BeanUtil.copyProperties(orderAddVO, Order.class);
        this.save(addOrder);
        res.put("carInfo", carService.getById(useCar.getId()));
        res.put("orderInfo", this.getById(addOrder.getId()));
        return CommonResult.success("add order success", res);
    }

    @Override
    public CommonResult<Map<Object, Object>> finish(OrderFinishVO orderFinishVO) {
        Map<Object, Object> res = new HashMap<>(8);
        Order order = orderMapper.selectByOrderNum(orderFinishVO.getOrderNum());
        Car car = carService.getById(order.getCarId());
        // 判断是否为已结束订单
        if (!order.getStatus().equals(StatusEnum.ORD_IN_PROGRESS.status)) {
            throw new ImplException(ResultCode.ORDER_IS_FINISHED);
        }
        // 如果是事故单则创建对应事故单信息
        if (orderFinishVO.getStatus().equals(StatusEnum.ORD_ACCIDENT.status)) {
            AccidentAddVO accidentAddVO = BeanUtil.copyProperties(orderFinishVO, AccidentAddVO.class);
            accidentAddVO.setIsCreateRepair(true);
            Map<Object, Object> addAccRes = accidentService.addOne(accidentAddVO).getData();
            carService.changeStatus(order.getCarId(), StatusEnum.CAR_FAULT.status);
            order.setFinishTime(orderFinishVO.getFinishTime())
                    .setStatus(orderFinishVO.getStatus());
            this.updateById(order);
            res.put("repairInfo", addAccRes.get("repairInfo"));
            res.put("accidentInfo", addAccRes.get("accidentInfo"));
            res.put("orderInfo", this.getById(order.getId()));
            return CommonResult.success("finish order success", res);
        } else {
            order.setFinishTime(orderFinishVO.getFinishTime())
                    .setStatus(orderFinishVO.getStatus());
        }
        // 修改车辆状态
        if (!carService.changeStatus(order.getCarId(), StatusEnum.CAR_NORMAL.status)) {
            throw new ImplException(ResultCode.CAR_STATUS_ERROR);
        }
        // 修改门店库存
        if (!storeService.addCarStock(storeService.getNameById(orderFinishVO.getFinishStoreId()))) {
            throw new ImplException(ResultCode.STORE_MORE_ERROR);
        }
        // 修改车辆所在位置
        if (!carService.changeStore(order.getCarId(), orderFinishVO.getFinishStoreId())) {
            throw new ImplException(ResultCode.CAR_WAREHOUSING_ERROR);
        }
        // 计算订单总价
        Date beginTime = Date.from(order.getStartTime().atZone(ZoneId.systemDefault()).toInstant());
        Date endTime = Date.from(order.getFinishTime().atZone(ZoneId.systemDefault()).toInstant());
        Double betweenTime = DateUtil.between(beginTime, endTime, DateUnit.MINUTE) / 60.0;
        order.setTotalTime(betweenTime);
        order.setTotalPrice(betweenTime * car.getPrice());
        this.updateById(order);
        res.put("orderInfo", this.getById(order.getId()));
        // 修改订单状态
        return CommonResult.success("finish order success", res);
    }

    @Override
    public CommonResult<Map<Object, Object>> getAll(Integer pageSize, Integer pageIndex) {
        Map<Object, Object> res = new LinkedHashMap<>();
        IPage<Order> orderInfo = orderMapper.selectPage(new Page<>(pageIndex, pageSize), null);
        res.put("totalRecords", orderInfo.getTotal());
        res.put("dataList", orderInfo.getRecords());
        res.put("pageSize", orderInfo.getSize());
        res.put("pageNums", orderInfo.getPages());
        res.put("currentIndex", orderInfo.getCurrent());
        return CommonResult.success("get orders info success", res);
    }

    @Override
    public CommonResult<Map<Object, Object>> select(Integer id, String orderNum, Integer userId, Integer carId, Integer operatorId, String status, Double totalPrice, String startTime, String finishTime) {
        Map<Object, Object> res = new LinkedHashMap<>();
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(id != null, Order::getId, id)
                .eq(StrUtil.isNotBlank(orderNum), Order::getOrderNum, orderNum)
                .eq(userId != null, Order::getUserId, userId)
                .eq(carId != null, Order::getCarId, carId)
                .eq(operatorId != null, Order::getOperatorId, operatorId)
                .eq(StrUtil.isNotBlank(status), Order::getStatus, status)
                .eq(totalPrice != null, Order::getTotalPrice, totalPrice)
                .ge(StrUtil.isNotBlank(startTime), Order::getStartTime, startTime)
                .le(StrUtil.isNotBlank(finishTime), Order::getFinishTime, finishTime);
        res.put("orderInfos", orderMapper.selectList(queryWrapper));
        return CommonResult.success("get infos success", res);
    }

    @Override
    public CommonResult<Map<Object, Object>> updateOne(OrderUpdateVO orderUpdateVO) {
        Map<Object, Object> res = new HashMap<>(8);
        if (orderUpdateVO.getId() == null && orderUpdateVO.getOrderNum() == null) {
            throw new ImplException(ResultCode.ORDER_LOCATE_ERROR);
        }
        Order updateOrder = BeanUtil.copyProperties(orderUpdateVO, Order.class);
        if (updateOrder.getId() != null) {
            this.updateById(updateOrder);
            res.put("orderInfo", this.getById(updateOrder.getId()));
            return CommonResult.success("update order info success", res);
        } else if (updateOrder.getOrderNum() != null) {
            updateOrder.setId(orderMapper.getIdByOrderNum(updateOrder.getOrderNum()));
            this.updateById(updateOrder);
            res.put("orderInfo", this.getById(updateOrder.getId()));
            return CommonResult.success("update order info success", res);
        } else {
            throw new ImplException(ResultCode.ORDER_LOCATE_ERROR);
        }
    }


}
