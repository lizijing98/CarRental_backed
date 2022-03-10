package com.lizijing.carrental.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lizijing.carrental.entity.bean.User;
import com.lizijing.carrental.entity.vo.UserAddVO;
import com.lizijing.carrental.result.CommonResult;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author LiZijing
 * @since 2022-02-27
 */
public interface UserService extends IService<User> {

    /**
     * 增加用户信息接口
     *
     * @param userAddVO 增加用户信息参数
     * @return 结果
     */
    CommonResult<Map<Object, Object>> addOne(UserAddVO userAddVO);

    /**
     * 删除用户接口
     *
     * @param userId     用户 ID
     * @param operatorId 操作者 ID
     * @return 结果
     */
    CommonResult<Map<Object, Object>> delOne(Integer userId, Integer operatorId);
}
