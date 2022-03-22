package com.lizijing.carrental.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lizijing.carrental.entity.bean.Order;
import com.lizijing.carrental.entity.vo.OrderAddVO;
import com.lizijing.carrental.entity.vo.OrderFinishVO;
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
}
