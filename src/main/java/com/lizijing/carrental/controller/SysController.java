package com.lizijing.carrental.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> 系统Controller </p>
 *
 * @author LiZijing
 * @date 2022/3/25
 */
@RestController
@RequestMapping("/")
@Api("系统接口")
public class SysController {

    @ApiOperation(value = "服务探测")
    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String ping() {
        return "pong";
    }
}
