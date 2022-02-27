package com.lizijing.carrental.service.impl;

import com.lizijing.carrental.entity.bean.User;
import com.lizijing.carrental.mapper.UserMapper;
import com.lizijing.carrental.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author LiZijing
 * @since 2022-02-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
