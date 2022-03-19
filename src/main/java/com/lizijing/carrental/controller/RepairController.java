package com.lizijing.carrental.controller;


import com.lizijing.carrental.entity.vo.RepairAddVO;
import com.lizijing.carrental.entity.vo.RepairUpdateVO;
import com.lizijing.carrental.result.CommonResult;
import com.lizijing.carrental.service.RepairService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Map;

/**
 * <p>
 * 维修单表 前端控制器
 * </p>
 *
 * @author LiZijing
 * @since 2022-03-17
 */
@RestController
@RequestMapping("/repair")
@Api("维修单操作接口")
public class RepairController {
    @Resource
    private RepairService repairService;

    @ApiOperation(value = "增加维修单信息")
    @ApiImplicitParam(name = "repairAddVO", value = "增加维修单接口参数", required = true, dataTypeClass = RepairAddVO.class)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public CommonResult<Map<Object, Object>> addOne(@Validated @RequestBody RepairAddVO repairAddVO) {
        return repairService.addOne(repairAddVO);
    }

    @ApiOperation(value = "删除维修单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "repairNum", value = "维修单编号", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "query", name = "operatorId", value = "操作者 ID", required = true, dataTypeClass = Integer.class)
    })
    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    public CommonResult<Map<Object, Object>> delOne(@RequestParam(value = "repairNum")
                                                    @NotBlank(message = "维修单编号不能为空")
                                                            String repairNum,
                                                    @RequestParam(value = "operatorId")
                                                    @NotNull(message = "操作者 ID 不能为空")
                                                    @Positive(message = "不合法的操作者 ID")
                                                            Integer operatorId) {
        return repairService.delOne(repairNum, operatorId);
    }

    @ApiOperation(value = "获取所有维修单信息")
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
        return repairService.getAll(pageSize, pageIndex);
    }

    @ApiOperation(value = "筛选维修单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "维修单 ID", example = "1", allowEmptyValue = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "repairNum", value = "维修单编号", allowEmptyValue = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户 ID", example = "1", allowEmptyValue = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "carId", value = "车辆 ID", example = "1", allowEmptyValue = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "operatorId", value = "操作员 ID", example = "1", allowEmptyValue = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "status", value = "维修单状态", allowEmptyValue = true, dataTypeClass = String.class),
    })
    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public CommonResult<Map<Object, Object>> select(@RequestParam(value = "id", required = false) @Positive(message = "不合法的维修单 ID") Integer id,
                                                    @RequestParam(value = "repairNum", required = false) String repairNum,
                                                    @RequestParam(value = "userId", required = false) @Positive(message = "不合法的用户 ID") Integer userId,
                                                    @RequestParam(value = "carId", required = false) @Positive(message = "不合法的车辆 ID") Integer carId,
                                                    @RequestParam(value = "operatorId", required = false) @Positive(message = "不合法的操作员 ID") Integer operatorId,
                                                    @RequestParam(value = "status", required = false) String status) {
        return repairService.select(id, repairNum, userId, carId, operatorId, status);
    }

    @ApiOperation(value = "修改维修单信息")
    @ApiImplicitParam(name = "repairUpdateVO", value = "修改维修单信息接口参数", required = true, dataTypeClass = RepairUpdateVO.class)
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public CommonResult<Map<Object, Object>> updateOne(@Validated @RequestBody RepairUpdateVO repairUpdateVO) {
        return repairService.updateOne(repairUpdateVO);
    }

    @ApiOperation(value = "修改维修单状态")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "repairNum", value = "维修单编号", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "query", name = "status", value = "维修单状态", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "operatorId", value = "操作者 ID", required = true, dataTypeClass = Integer.class)
    })
    @RequestMapping(value = "/modifyStatus", method = RequestMethod.PUT)
    public CommonResult<Map<Object, Object>> modifyStatus(@RequestParam(value = "repairNum")
                                                          @NotBlank(message = "维修单编号不能为空")
                                                                  String repairNum,
                                                          @RequestParam(value = "status")
                                                          @NotNull(message = "维修单状态不能为空")
                                                                  Integer status,
                                                          @RequestParam(value = "operatorId")
                                                          @NotNull(message = "操作者 ID 不能为空")
                                                          @Positive(message = "不合法的操作者 ID")
                                                                  Integer operatorId) {
        return repairService.modifyStatus(repairNum, status, operatorId);
    }

}

