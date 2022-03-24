package com.lizijing.carrental.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lizijing.carrental.entity.bean.Order;
import com.lizijing.carrental.entity.vo.OrderAddVO;
import com.lizijing.carrental.entity.vo.OrderFinishVO;
import com.lizijing.carrental.entity.vo.OrderUpdateVO;
import com.lizijing.carrental.result.CommonResult;

import java.util.Map;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author LiZijing
 * @since 2022-02-27
 */
public interface OrderService extends IService<Order> {

    /**
     * 创建订单
     *
     * @param orderAddVO 创建订单参数
     * @return 结果
     */
    CommonResult<Map<Object, Object>> addOne(OrderAddVO orderAddVO);

    /**
     * 完成订单
     *
     * @param orderFinishVO 完成订单参数
     * @return 结果
     */
    CommonResult<Map<Object, Object>> finish(OrderFinishVO orderFinishVO);

    /**
     * 获取所有订单接口
     *
     * @param pageSize  分页数
     * @param pageIndex 页码
     * @return 结果
     */
    CommonResult<Map<Object, Object>> getAll(Integer pageSize, Integer pageIndex);

    /**
     * 筛选订单
     *
     * @param id         订单 ID
     * @param orderNum   订单编号
     * @param userId     用户 ID
     * @param carId      车辆 ID
     * @param operatorId 操作员 ID
     * @param status     订单状态
     * @param totalPrice 订单总价
     * @param startTime  开始时间
     * @param finishTime 结束时间
     * @return 结果
     */
    CommonResult<Map<Object, Object>> select(Integer id, String orderNum, Integer userId, Integer carId, Integer operatorId, String status, Double totalPrice, String startTime, String finishTime);

    /**
     * 更新订单信息
     *
     * @param orderUpdateVO 更新订单信息接口参数
     * @return 结果
     */
    CommonResult<Map<Object, Object>> updateOne(OrderUpdateVO orderUpdateVO);
}
