package com.lizijing.carrental.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizijing.carrental.entity.bean.Car;
import com.lizijing.carrental.entity.vo.CarAddVO;
import com.lizijing.carrental.mapper.CarMapper;
import com.lizijing.carrental.result.CommonResult;
import com.lizijing.carrental.service.CarService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

    @Override
    public CommonResult<Map<Object, Object>> addOne(CarAddVO carAddVO) {
        Map<Object,Object>res=new HashMap<>(8);
        Car addCar = BeanUtil.copyProperties(carAddVO,Car.class);
        res.put("car",addCar);
        return CommonResult.success("add car success",res);
    }
}
