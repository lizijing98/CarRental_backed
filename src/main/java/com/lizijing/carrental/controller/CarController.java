package com.lizijing.carrental.controller;


import com.lizijing.carrental.entity.vo.CarAddVO;
import com.lizijing.carrental.result.CommonResult;
import com.lizijing.carrental.service.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
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
    @ApiImplicitParam(name = "carAddVO", value = "增加车辆接口参数", required = true, dataTypeClass = CarAddVO.class)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public CommonResult<Map<Object, Object>> addOne(@Validated @RequestBody CarAddVO carAddVO) {
        return carService.addOne(carAddVO);
    }

    @ApiOperation(value = "删除车辆信息")
    @RequestMapping(value = "/del/{carId}?operator={operatorId}", method = RequestMethod.DELETE)
    public CommonResult<Map<Object, Object>> delOne(@PathVariable("carId") @NotNull(message = "车辆 ID 不能为空") Long carId,
                                                    @PathVariable("operatorId") @NotNull(message = "操作者 ID 不能为空") Long operatorId) {
        return null;
    }
}

