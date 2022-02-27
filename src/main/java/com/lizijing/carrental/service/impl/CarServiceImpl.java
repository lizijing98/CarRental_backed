package com.lizijing.carrental.service.impl;

import com.lizijing.carrental.entity.bean.Car;
import com.lizijing.carrental.mapper.CarMapper;
import com.lizijing.carrental.service.CarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
