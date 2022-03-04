package com.lizijing.carrental.controller;


import com.lizijing.carrental.entity.vo.CarAddVO;
import com.lizijing.carrental.result.CommonResult;
import com.lizijing.carrental.service.CarService;
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
 * 车辆表 前端控制器
 * </p>
 *
 * @author LiZijing
 * @since 2022-02-27
 */
@RestController
@RequestMapping("/car")
@Api(value = "车辆操作接口")
public class CarController {
    @Resource
    private CarService carService;

    @ApiOperation(value = "增加车辆信息")
    @ApiImplicitParam(name = "carAddVO", value = "增加车辆接口参数", required = true)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public CommonResult<Map<Object, Object>> addOne(@Validated @RequestBody CarAddVO carAddVO) {
        return carService.addOne(carAddVO);
    }
}

