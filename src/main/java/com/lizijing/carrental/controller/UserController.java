package com.lizijing.carrental.controller;


import com.lizijing.carrental.entity.vo.CarUpdateVO;
import com.lizijing.carrental.entity.vo.UserAddVO;
import com.lizijing.carrental.entity.vo.UserEnhanceVO;
import com.lizijing.carrental.entity.vo.UserUpdateVO;
import com.lizijing.carrental.result.CommonResult;
import com.lizijing.carrental.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author LiZijing
 * @since 2022-02-27
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户操作接口")
public class UserController {
    @Resource
    private UserService userService;

    @ApiOperation(value = "增加用户信息")
    @ApiImplicitParam(name = "userAddVO", value = "增加用户接口参数", required = true, dataTypeClass = UserAddVO.class)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public CommonResult<Map<Object, Object>> addOne(@Validated @RequestBody UserAddVO userAddVO) {
        return userService.addOne(userAddVO);
    }

    @ApiOperation(value = "删除用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户 ID", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "operatorId", value = "操作者 ID", required = true, dataTypeClass = Integer.class)
    })
    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    public CommonResult<Map<Object, Object>> delOne(@RequestParam(value = "userId")
                                                    @NotNull(message = "用户 ID 不能为空")
                                                    @Positive(message = "不合法的用户 ID")
                                                            Integer userId,
                                                    @RequestParam(value = "operatorId")
                                                    @NotNull(message = "操作者 ID 不能为空")
                                                    @Positive(message = "不合法的操作者 ID")
                                                            Integer operatorId) {
        return userService.delOne(userId, operatorId);
    }

    @ApiOperation(value = "获取所有用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "分页数", example = "1", defaultValue = "1", dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "pageIndex", value = "页码", example = "10", defaultValue = "10", dataTypeClass = Integer.class)
    })
    @RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
    public CommonResult<Map<Object, Object>> getAllUser(@RequestParam(value = "pageSize", defaultValue = "1")
                                                        @Positive(message = "不合法的分页数")
                                                                Integer pageSize,
                                                        @RequestParam(value = "pageIndex", defaultValue = "10")
                                                        @Positive(message = "不合法的页码")
                                                                Integer pageIndex) {
        return userService.getAllUser(pageSize, pageIndex);
    }

    @ApiOperation(value = "获取所有业务员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "分页数", example = "1", defaultValue = "1", dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "pageIndex", value = "页码", example = "10", defaultValue = "10", dataTypeClass = Integer.class)
    })
    @RequestMapping(value = "/getAllSales", method = RequestMethod.GET)
    public CommonResult<Map<Object, Object>> getAllSales(@RequestParam(value = "pageSize", defaultValue = "1")
                                                         @Positive(message = "不合法的分页数")
                                                                 Integer pageSize,
                                                         @RequestParam(value = "pageIndex", defaultValue = "10")
                                                         @Positive(message = "不合法的页码")
                                                                 Integer pageIndex) {
        return userService.getAllSales(pageSize, pageIndex);
    }

    @ApiOperation(value = "获取所有故障处理专员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "分页数", example = "1", defaultValue = "1", dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "pageIndex", value = "页码", example = "10", defaultValue = "10", dataTypeClass = Integer.class)
    })
    @RequestMapping(value = "/getAllShooter", method = RequestMethod.GET)
    public CommonResult<Map<Object, Object>> getAllShooter(@RequestParam(value = "pageSize", defaultValue = "1")
                                                           @Positive(message = "不合法的分页数")
                                                                   Integer pageSize,
                                                           @RequestParam(value = "pageIndex", defaultValue = "10")
                                                           @Positive(message = "不合法的页码")
                                                                   Integer pageIndex) {
        return userService.getAllShooter(pageSize, pageIndex);
    }

    @ApiOperation(value = "筛选用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "用户 ID", example = "1", allowEmptyValue = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "username", value = "用户名", example = "test-user", allowEmptyValue = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "query", name = "nickname", value = "用户昵称", example = "测试用户", allowEmptyValue = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "query", name = "realName", value = "真实姓名", example = "测试用户", allowEmptyValue = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "query", name = "phoneNumber", value = "手机号码", example = "17612341111", allowEmptyValue = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "query", name = "email", value = "邮箱", example = "test1@test.com", allowEmptyValue = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "query", name = "roleName", value = "用户角色", example = "USER", allowEmptyValue = true, dataTypeClass = String.class)
    })
    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public CommonResult<Map<Object, Object>> select(@RequestParam(value = "id", required = false) @Positive(message = "不合法的用户 ID") Integer id,
                                                    @RequestParam(value = "username", required = false) String username,
                                                    @RequestParam(value = "nickname", required = false) String nickname,
                                                    @RequestParam(value = "realName", required = false) String realName,
                                                    @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                                                    @RequestParam(value = "email", required = false) String email,
                                                    @RequestParam(value = "roleName") String roleName) {
        return userService.select(id, username, nickname, realName, phoneNumber, email, roleName);
    }

    @ApiOperation(value = "修改用户信息")
    @ApiImplicitParam(name = "userUpdateVO", value = "修改用户信息接口参数", required = true, dataTypeClass = CarUpdateVO.class)
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public CommonResult<Map<Object, Object>> updateOne(@Validated @RequestBody UserUpdateVO userUpdateVO) {
        return userService.updateOne(userUpdateVO);
    }

    @ApiOperation(value = "修改用户角色")
    @ApiImplicitParam(name = "userEnhanceVO", value = "修改用户角色接口参数", required = true, dataTypeClass =UserEnhanceVO.class)
    @RequestMapping(value = "/enhance", method = RequestMethod.PUT)
    public CommonResult<Map<Object, Object>> enhance(@Validated @RequestBody UserEnhanceVO userEnhanceVO) {
        return userService.enhance(userEnhanceVO);
    }
}

