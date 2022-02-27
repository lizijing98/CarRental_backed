package com.lizijing.carrental.mapper;

import com.lizijing.carrental.entity.bean.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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

}
