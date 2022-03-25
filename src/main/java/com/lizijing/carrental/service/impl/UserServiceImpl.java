package com.lizijing.carrental.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizijing.carrental.entity.bean.User;
import com.lizijing.carrental.entity.bean.UserRole;
import com.lizijing.carrental.entity.bean.Userinfo;
import com.lizijing.carrental.entity.enums.RoleEnum;
import com.lizijing.carrental.entity.vo.UserAddVO;
import com.lizijing.carrental.entity.vo.UserEnhanceVO;
import com.lizijing.carrental.entity.vo.UserUpdateVO;
import com.lizijing.carrental.exception.ImplException;
import com.lizijing.carrental.mapper.UserMapper;
import com.lizijing.carrental.mapper.UserinfoMapper;
import com.lizijing.carrental.result.CommonResult;
import com.lizijing.carrental.result.ResultCode;
import com.lizijing.carrental.service.UserRoleService;
import com.lizijing.carrental.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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
    @Resource
    private UserinfoMapper userinfoMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public CommonResult<Map<Object, Object>> addOne(UserAddVO userAddVO) {
        Map<Object, Object> res = new HashMap<>(8);
        if (userMapper.selectByUsername(userAddVO.getUsername()) != null) {
            throw new ImplException(ResultCode.DB_Duplicate_ERROR, "username: " + userAddVO.getUsername());
        }
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
        res.put("userInfo", userinfoMapper.selectById(addUser.getId()));
        return CommonResult.success("add user success", res);
    }

    @Override
    public CommonResult<Map<Object, Object>> delOne(Integer userId, Integer operatorId) {
        Map<Object, Object> res = new HashMap<>(8);
        // 验证操作者
        //
        // 验证车辆状态
        User delUser = this.getById(userId);
        if (delUser == null) {
            throw new ImplException(ResultCode.USER_EXIST_ERROR);
        }
        // 逻辑删除
        this.removeById(delUser);
        res.put("delUserId", delUser.getId());
        return CommonResult.success("delete user success", res);
    }

    @Override
    public CommonResult<Map<Object, Object>> getAllUser(Integer pageSize, Integer pageIndex) {
        return CommonResult.success("get user infos success", getInfos(pageSize, pageIndex, RoleEnum.USER.name().toLowerCase()));
    }

    @Override
    public CommonResult<Map<Object, Object>> getAllSales(Integer pageSize, Integer pageIndex) {
        return CommonResult.success("get salesman infos success", getInfos(pageSize, pageIndex, RoleEnum.SALESMAN.name().toLowerCase()));
    }

    @Override
    public CommonResult<Map<Object, Object>> getAllShooter(Integer pageSize, Integer pageIndex) {
        return CommonResult.success("get troubleshooter info success", getInfos(pageSize, pageIndex, RoleEnum.TROUBLESHOOTER.name().toLowerCase()));
    }

    @Override
    public CommonResult<Map<Object, Object>> select(Integer id, String username, String nickname, String realName, String phoneNumber, String email, String roleName) {
        Map<Object, Object> res = new LinkedHashMap<>();
        LambdaQueryWrapper<Userinfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(id != null, Userinfo::getId, id)
                .like(StrUtil.isNotBlank(username), Userinfo::getUsername, username)
                .like(StrUtil.isNotBlank(nickname), Userinfo::getNickname, nickname)
                .eq(StrUtil.isNotBlank(realName), Userinfo::getRealName, realName)
                .eq(StrUtil.isNotBlank(phoneNumber), Userinfo::getPhoneNumber, phoneNumber)
                .eq(StrUtil.isNotBlank(email), Userinfo::getEmail, email)
                .eq(StrUtil.isNotBlank(roleName), Userinfo::getRoleName, roleName);
        List<Userinfo> users = userinfoMapper.selectList(queryWrapper);
        res.put("userInfos", users);
        return CommonResult.success("get infos success", res);
    }

    @Override
    public CommonResult<Map<Object, Object>> updateOne(UserUpdateVO userUpdateVO) {
        Map<Object, Object> res = new HashMap<>(8);
        User updateUser = BeanUtil.copyProperties(userUpdateVO, User.class);
        this.updateById(updateUser);
        res.put("userInfo", updateUser);
        return CommonResult.success("update user info success", res);
    }

    @Override
    public CommonResult<Map<Object, Object>> enhance(UserEnhanceVO userEnhanceVO) {
        Map<Object, Object> res = new HashMap<>(8);
        UserRole updateRole = new UserRole().setUserId(userEnhanceVO.getUserId())
                .setRoleId(RoleEnum.valueOf(userEnhanceVO.getRoleName()).ordinal() + 1);
        userRoleService.update(updateRole, new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, userEnhanceVO.getUserId()));
        res.put("userInfo", userinfoMapper.selectById(userEnhanceVO.getUserId()));
        return CommonResult.success("user's role update success", res);
    }

    private Map<Object, Object> getInfos(Integer pageSize, Integer pageIndex, String roleName) {
        Map<Object, Object> res = new LinkedHashMap<>();
        LambdaQueryWrapper<Userinfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Userinfo::getRoleName, roleName);
        IPage<Userinfo> infos = userinfoMapper.selectPage(new Page<>(pageIndex, pageSize), queryWrapper);
        res.put("totalRecords", infos.getTotal());
        res.put("dataList", infos.getRecords());
        res.put("pageSize", infos.getSize());
        res.put("pageNums", infos.getPages());
        res.put("currentIndex", infos.getCurrent());
        return res;
    }
}
