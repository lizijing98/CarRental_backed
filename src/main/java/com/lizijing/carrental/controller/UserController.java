package com.lizijing.carrental.controller;


import com.lizijing.carrental.entity.vo.UserAddVO;
import com.lizijing.carrental.result.CommonResult;
import com.lizijing.carrental.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

}

