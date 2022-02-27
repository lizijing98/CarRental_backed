package com.lizijing.carrental.mapper;

import com.lizijing.carrental.entity.bean.Car;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 车辆表 Mapper 接口
 * </p>
 *
 * @author LiZijing
 * @since 2022-02-27
 */
@Mapper
public interface CarMapper extends BaseMapper<Car> {

}
