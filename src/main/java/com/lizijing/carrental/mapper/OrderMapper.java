package com.lizijing.carrental.mapper;

import com.lizijing.carrental.bean.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author LiZijing
 * @since 2022-02-27
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
