package com.lizijing.carrental.controller;


import com.lizijing.carrental.entity.vo.CarAddVO;
import com.lizijing.carrental.result.CommonResult;
import com.lizijing.carrental.service.CarService;
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
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "carId", value = "车辆 ID", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "operatorId", value = "操作者 ID", required = true, dataTypeClass = Integer.class)
    })
    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    public CommonResult<Map<Object, Object>> delOne(@RequestParam(value = "carId")
                                                    @NotNull(message = "车辆 ID 不能为空")
                                                            Integer carId,
                                                    @RequestParam(value = "operatorId")
                                                    @NotNull(message = "操作者 ID 不能为空")
                                                            Integer operatorId) {
        return carService.delOne(carId, operatorId);
    }

    @ApiOperation(value = "获取所有车辆信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "分页数", example = "1", defaultValue = "1", dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "pageIndex", value = "页码", example = "10", defaultValue = "10", dataTypeClass = Integer.class)
    })
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public CommonResult<Map<Object, Object>> getAll(@RequestParam(value = "pageSize", defaultValue = "1")
                                                    @Positive(message = "不合法的分页数")
                                                            Integer pageSize,
                                                    @RequestParam(value = "pageIndex", defaultValue = "10")
                                                    @Positive(message = "不合法的页码")
                                                            Integer pageIndex) {
        return carService.getAll(pageSize, pageIndex);
    }
}

