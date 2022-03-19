package com.lizijing.carrental.controller;


import com.lizijing.carrental.entity.vo.AccidentAddVO;
import com.lizijing.carrental.entity.vo.AccidentUpdateVO;
import com.lizijing.carrental.result.CommonResult;
import com.lizijing.carrental.service.AccidentService;
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
 * 事故单表 前端控制器
 * </p>
 *
 * @author LiZijing
 * @since 2022-03-14
 */
@RestController
@RequestMapping("/accident")
@Api("事故单操作接口")
public class AccidentController {
    @Resource
    private AccidentService accidentService;

    @ApiOperation(value = "增加事故单信息")
    @ApiImplicitParam(name = "accidentAddVO", value = "增加事故单接口参数", required = true, dataTypeClass = AccidentAddVO.class)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public CommonResult<Map<Object, Object>> addOne(@Validated @RequestBody AccidentAddVO accidentAddVO) {
        return accidentService.addOne(accidentAddVO);
    }

    @ApiOperation(value = "删除事故单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "accidentNum", value = "事故单编号", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "query", name = "operatorId", value = "操作者 ID", required = true, dataTypeClass = Integer.class)
    })
    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    public CommonResult<Map<Object, Object>> delOne(@RequestParam(value = "accidentNum")
                                                    @NotBlank(message = "事故单编号不能为空")
                                                            String accidentNum,
                                                    @RequestParam(value = "operatorId")
                                                    @NotNull(message = "操作者 ID 不能为空")
                                                    @Positive(message = "不合法的操作者 ID")
                                                            Integer operatorId) {
        return accidentService.delOne(accidentNum, operatorId);
    }

    @ApiOperation(value = "获取所有事故单信息")
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
        return accidentService.getAll(pageSize, pageIndex);
    }

    @ApiOperation(value = "筛选事故单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "事故单 ID", example = "1", allowEmptyValue = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "accidentNum", value = "事故单编号", allowEmptyValue = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户 ID", example = "1", allowEmptyValue = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "carId", value = "车辆 ID", example = "1", allowEmptyValue = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "operatorId", value = "操作员 ID", example = "1", allowEmptyValue = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "status", value = "事故单状态", allowEmptyValue = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "query", name = "hasRepair", value = "是否有对应事故单", allowEmptyValue = true, dataTypeClass = Boolean.class)
    })
    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public CommonResult<Map<Object, Object>> select(@RequestParam(value = "id", required = false) @Positive(message = "不合法的事故单 ID") Integer id,
                                                    @RequestParam(value = "accidentNum", required = false) String accidentNum,
                                                    @RequestParam(value = "userId", required = false) @Positive(message = "不合法的用户 ID") Integer userId,
                                                    @RequestParam(value = "carId", required = false) @Positive(message = "不合法的车辆 ID") Integer carId,
                                                    @RequestParam(value = "operatorId", required = false) @Positive(message = "不合法的操作员 ID") Integer operatorId,
                                                    @RequestParam(value = "status", required = false) String status,
                                                    @RequestParam(value = "hasRepair", required = false) Boolean hasRepair) {
        return accidentService.select(id, accidentNum, userId, carId, operatorId, status, hasRepair);
    }

    @ApiOperation(value = "修改事故单信息")
    @ApiImplicitParam(name = "accidentUpdateVO", value = "修改事故单信息接口参数", required = true, dataTypeClass = AccidentUpdateVO.class)
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public CommonResult<Map<Object, Object>> updateOne(@Validated @RequestBody AccidentUpdateVO accidentUpdateVO) {
        return accidentService.updateOne(accidentUpdateVO);
    }

    @ApiOperation(value = "修改事故单状态")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "accidentNum", value = "事故单编号", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "query", name = "status", value = "事故单状态", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "operatorId", value = "操作者 ID", required = true, dataTypeClass = Integer.class)
    })
    @RequestMapping(value = "/modifyStatus", method = RequestMethod.PUT)
    public CommonResult<Map<Object, Object>> modifyStatus(@RequestParam(value = "accidentNum")
                                                          @NotBlank(message = "事故单编号不能为空")
                                                                  String accidentNum,
                                                          @RequestParam(value = "status")
                                                          @NotNull(message = "事故单状态不能为空")
                                                                  Integer status,
                                                          @RequestParam(value = "operatorId")
                                                          @NotNull(message = "操作者 ID 不能为空")
                                                          @Positive(message = "不合法的操作者 ID")
                                                                  Integer operatorId) {
        return accidentService.modifyStatus(accidentNum, status, operatorId);
    }
}

