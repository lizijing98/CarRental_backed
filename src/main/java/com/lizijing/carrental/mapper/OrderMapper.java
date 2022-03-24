package com.lizijing.carrental.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lizijing.carrental.entity.bean.Order;
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

    /**
     * 根据订单编号查询订单
     *
     * @param orderNum 订单编号
     * @return 订单实体
     */
    Order selectByOrderNum(String orderNum);

    /**
     * 根据订单编号查询订单 ID
     * @param orderNum 订单编号
     * @return 订单 ID
     */
    Long getIdByOrderNum(String orderNum);
}
