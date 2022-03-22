package com.lizijing.carrental.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizijing.carrental.entity.bean.Car;
import com.lizijing.carrental.entity.enums.StatusEnum;
import com.lizijing.carrental.entity.vo.CarAddVO;
import com.lizijing.carrental.entity.vo.CarUpdateVO;
import com.lizijing.carrental.exception.ImplException;
import com.lizijing.carrental.mapper.CarMapper;
import com.lizijing.carrental.result.CommonResult;
import com.lizijing.carrental.result.ResultCode;
import com.lizijing.carrental.service.CarService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 车辆表 服务实现类
 * </p>
 *
 * @author LiZijing
 * @since 2022-02-27
 */
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements CarService {

    @Resource
    private StoreServiceImpl storeService;

    @Resource
    private CarMapper carMapper;

    @Override
    public CommonResult<Map<Object, Object>> addOne(CarAddVO carAddVO) {
        Map<Object, Object> res = new HashMap<>(8);
        Car addCar = BeanUtil.copyProperties(carAddVO, Car.class);
        // 若入库的店铺不为空，则进行调仓
        if (addCar.getStoreName() != null && !storeService.addCarStock(addCar.getStoreName())) {
            throw new ImplException(ResultCode.STORE_MORE_ERROR);
        }
        this.save(addCar);
        res.put("carInfo", addCar);
        return CommonResult.success("add car success", res);
    }

    @Override
    public CommonResult<Map<Object, Object>> delOne(Integer carId, Integer operatorId) {
        Map<Object, Object> res = new HashMap<>(8);
        // 验证操作者
        //
        // 验证车辆状态
        Car delCar = this.getById(carId);
        if (delCar == null) {
            throw new ImplException(ResultCode.CAR_EXIST_ERROR);
        }
        if (!delCar.getStatus().equals(StatusEnum.CAR_NORMAL.getStatus())) {
            throw new ImplException(ResultCode.CAR_STATUS_ERROR);
        }
        // 库存操作
        if (delCar.getStoreName() != null && !storeService.reduceCarStock(delCar.getStoreName())) {
            throw new ImplException(ResultCode.STORE_ZERO_ERROR);
        }
        // 逻辑删除
        this.removeById(delCar);
        res.put("delCarId", delCar.getId());
        res.put("delCarNumber", delCar.getCarNumber());
        return CommonResult.success("delete car success", res);
    }

    @Override
    public CommonResult<Map<Object, Object>> getAll(Integer pageSize, Integer pageIndex) {
        Map<Object, Object> res = new LinkedHashMap<>();
        IPage<Car> carInfos = carMapper.selectPage(new Page<>(pageIndex, pageSize), null);
        res.put("totalRecords", carInfos.getTotal());
        res.put("dataList", carInfos.getRecords());
        res.put("pageSize", carInfos.getSize());
        res.put("pageNums", carInfos.getPages());
        res.put("currentIndex", carInfos.getCurrent());
        return CommonResult.success("get cars info success", res);
    }

    @Override
    public CommonResult<Map<Object, Object>> updateOne(CarUpdateVO carUpdateVO) {
        Map<Object, Object> res = new HashMap<>(8);
        Car updateCar = BeanUtil.copyProperties(carUpdateVO, Car.class);
        String oldStoreName = carMapper.getStoreName(updateCar.getId());
        // 检查是否更改车辆所在位置
        if (updateCar.getStoreName() != null && !storeService.removeCar(oldStoreName, updateCar.getStoreName())) {
            throw new ImplException(ResultCode.STORE_ERROR);
        }
        this.updateById(updateCar);
        res.put("carInfo", updateCar);
        return CommonResult.success("update car info success", res);
    }

    @Override
    public CommonResult<Map<Object, Object>> select(Integer id, String carNumber, String model, String storeName, String type, LocalDateTime createTime, Boolean isUsable) {
        Map<Object, Object> res = new LinkedHashMap<>();
        LambdaQueryWrapper<Car> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(id != null, Car::getId, id)
                .eq(StrUtil.isNotBlank(carNumber), Car::getCarNumber, carNumber)
                .eq(StrUtil.isNotBlank(model), Car::getModel, model)
                .eq(StrUtil.isNotBlank(storeName), Car::getStoreName, storeName)
                .eq(StrUtil.isNotBlank(type), Car::getType, type)
                .ge(StrUtil.isNotBlank(createTime.toString()), Car::getCreateTime, createTime)
                .eq(isUsable != null, Car::getIsUsable, isUsable);
        List<Car> cars = carMapper.selectList(queryWrapper);
        res.put("carInfos", cars);
        return CommonResult.success("get infos success", res);
    }

    @Override
    public boolean changeStatus(Long carId, String status) {
        try {
            Car car = this.getById(carId);
            car.setStatus(status);
            this.updateById(car);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean changeStore(Long carId, Long storeId) {
        try {
            Car car = this.getById(carId);
            car.setStoreName(storeService.getNameById(storeId));
            this.updateById(car);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
