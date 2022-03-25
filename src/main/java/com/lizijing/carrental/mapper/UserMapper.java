package com.lizijing.carrental.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lizijing.carrental.entity.bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author LiZijing
 * @since 2022-02-27
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过用户名查询用户实体
     *
     * @param username 用户名
     * @return 用户实体
     */
    User selectByUsername(String username);
}
