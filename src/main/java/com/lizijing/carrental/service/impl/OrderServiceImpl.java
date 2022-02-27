package com.lizijing.carrental.service.impl;

import com.lizijing.carrental.entity.bean.Order;
import com.lizijing.carrental.mapper.OrderMapper;
import com.lizijing.carrental.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
