package com.lizijing.carrental.controller;


import com.lizijing.carrental.entity.vo.UserAddVO;
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

}

