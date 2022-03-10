package com.lizijing.carrental.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizijing.carrental.entity.bean.User;
import com.lizijing.carrental.entity.bean.UserRole;
import com.lizijing.carrental.entity.enums.RoleEnum;
import com.lizijing.carrental.entity.vo.UserAddVO;
import com.lizijing.carrental.mapper.UserMapper;
import com.lizijing.carrental.result.CommonResult;
import com.lizijing.carrental.service.UserRoleService;
import com.lizijing.carrental.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

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
    @Resource
    private UserRoleService userRoleService;

    @Override
    public CommonResult<Map<Object, Object>> addOne(UserAddVO userAddVO) {
        Map<Object, Object> res = new HashMap<>(8);
        User addUser = BeanUtil.copyProperties(userAddVO, User.class);
        this.saveOrUpdate(addUser);
        UserRole addUserRole = userRoleService.getOne(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, addUser.getId()));
        if (addUserRole != null) {
            addUserRole.setRoleId(RoleEnum.valueOf(userAddVO.getRoleName()).ordinal() + 1);
            userRoleService.saveOrUpdate(addUserRole);
        } else {
            addUserRole = new UserRole()
                    .setUserId(addUser.getId())
                    .setRoleId(RoleEnum.valueOf(userAddVO.getRoleName()).ordinal() + 1);
            userRoleService.saveOrUpdate(addUserRole);
        }
        res.put("userInfo", addUser);
        res.put("userRole", addUserRole);
        return CommonResult.success("add user success", res);
    }
}
