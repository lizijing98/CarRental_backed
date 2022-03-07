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
 * @since 2022-03-01
 */
@Mapper
public interface CarMapper extends BaseMapper<Car> {

    /**
     * 根据车辆 ID 获取当前车辆所在门店位置
     * @param id 车辆 ID
     * @return 门店名称
     */
    String getStoreName(Long id);
}
