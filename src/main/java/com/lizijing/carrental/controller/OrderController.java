package com.lizijing.carrental.controller;


import com.lizijing.carrental.entity.vo.OrderAddVO;
import com.lizijing.carrental.entity.vo.OrderFinishVO;
import com.lizijing.carrental.entity.vo.OrderUpdateVO;
import com.lizijing.carrental.result.CommonResult;
import com.lizijing.carrental.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Positive;
import java.util.Map;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author LiZijing
 * @since 2022-02-27
 */
@RestController
@RequestMapping("/order")
@Api("订单操作接口")
public class OrderController {
    @Resource
    private OrderService orderService;

    @ApiOperation(value = "新增订单")
    @ApiImplicitParam(name = "orderAddVO", value = "新增订单接口参数", required = true, dataTypeClass = OrderAddVO.class)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public CommonResult<Map<Object, Object>> addOne(@Validated @RequestBody OrderAddVO orderAddVO) {
        return orderService.addOne(orderAddVO);
    }

    @ApiOperation(value = "完成订单")
    @ApiImplicitParam
    @RequestMapping(value = "/finish", method = RequestMethod.PUT)
    public CommonResult<Map<Object, Object>> finishOne(@Validated @RequestBody OrderFinishVO orderFinishVO) {
        return orderService.finish(orderFinishVO);
    }

    @ApiOperation(value = "获取所有订单信息")
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
        return orderService.getAll(pageSize, pageIndex);
    }

    @ApiOperation(value = "筛选订单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "订单 ID", example = "1", allowEmptyValue = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "orderNum", value = "订单编号", allowEmptyValue = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户 ID", example = "1", allowEmptyValue = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "carId", value = "车辆 ID", example = "1", allowEmptyValue = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "operatorId", value = "操作员 ID", example = "1", allowEmptyValue = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "status", value = "订单状态", allowEmptyValue = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "query", name = "totalPrice", value = "订单总价", allowEmptyValue = true, dataTypeClass = Double.class),
            @ApiImplicitParam(paramType = "query", name = "startTime", value = "开始时间", allowEmptyValue = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "query", name = "finishTime", value = "结束时间", allowEmptyValue = true, dataTypeClass = String.class)
    })
    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public CommonResult<Map<Object, Object>> select(@RequestParam(value = "id", required = false) @Positive(message = "不合法的订单 ID") Integer id,
                                                    @RequestParam(value = "orderNum", required = false) String orderNum,
                                                    @RequestParam(value = "userId", required = false) @Positive(message = "不合法的用户 ID") Integer userId,
                                                    @RequestParam(value = "carId", required = false) @Positive(message = "不合法的车辆 ID") Integer carId,
                                                    @RequestParam(value = "operatorId", required = false) @Positive(message = "不合法的操作员 ID") Integer operatorId,
                                                    @RequestParam(value = "status", required = false) String status,
                                                    @RequestParam(value = "totalPrice", required = false) Double totalPrice,
                                                    @RequestParam(value = "startTime", required = false) String startTime,
                                                    @RequestParam(value = "startTime", required = false) String finishTime) {
        return orderService.select(id, orderNum, userId, carId, operatorId, status, totalPrice, startTime, finishTime);
    }

    @ApiOperation(value = "修改订单信息")
    @ApiImplicitParam(name = "orderUpdateVO", value = "修改订单信息接口参数", required = true, dataTypeClass = OrderUpdateVO.class)
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public CommonResult<Map<Object, Object>> updateOne(@Validated @RequestBody OrderUpdateVO orderUpdateVO) {
        return orderService.updateOne(orderUpdateVO);
    }
}

