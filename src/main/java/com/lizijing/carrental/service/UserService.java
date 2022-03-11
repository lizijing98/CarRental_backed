package com.lizijing.carrental.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lizijing.carrental.entity.bean.User;
import com.lizijing.carrental.entity.vo.CarUpdateVO;
import com.lizijing.carrental.entity.vo.UserAddVO;
import com.lizijing.carrental.entity.vo.UserEnhanceVO;
import com.lizijing.carrental.entity.vo.UserUpdateVO;
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

    /**
     * 获取所有用户信息接口
     *
     * @param pageSize  分页数
     * @param pageIndex 页码
     * @return 结果
     */
    CommonResult<Map<Object, Object>> getAllUser(Integer pageSize, Integer pageIndex);

    /**
     * 获取所有业务员信息接口
     *
     * @param pageSize  分页数
     * @param pageIndex 页码
     * @return 结果
     */
    CommonResult<Map<Object, Object>> getAllSales(Integer pageSize, Integer pageIndex);

    /**
     * 获取所有故障处理专员信息接口
     *
     * @param pageSize  分页数
     * @param pageIndex 页码
     * @return 结果
     */
    CommonResult<Map<Object, Object>> getAllShooter(Integer pageSize, Integer pageIndex);

    /**
     * 删选用户信息接口
     *
     * @param id          用户 ID
     * @param username    用户名
     * @param nickname    用户昵称
     * @param realName    真实姓名
     * @param phoneNumber 手机号码
     * @param email       邮箱
     * @param roleName    角色名
     * @return 结果
     */
    CommonResult<Map<Object, Object>> select(Integer id, String username, String nickname, String realName, String phoneNumber, String email, String roleName);

    /**
     * 修改用户信息接口
     * @param userUpdateVO 修改用户信息接口参数
     * @return 结果
     */
    CommonResult<Map<Object, Object>> updateOne(UserUpdateVO userUpdateVO);

    /**
     * 修改用户角色接口
     * @param userEnhanceVO 修改用户角色接口
     * @return 结果
     */
    CommonResult<Map<Object, Object>> enhance(UserEnhanceVO userEnhanceVO);
}
