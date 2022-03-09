package com.lizijing.carrental.controller;


import com.lizijing.carrental.entity.vo.StoreAddVO;
import com.lizijing.carrental.entity.vo.StoreUpdateVO;
import com.lizijing.carrental.result.CommonResult;
import com.lizijing.carrental.service.StoreService;
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
 * 门店表 前端控制器
 * </p>
 *
 * @author LiZijing
 * @since 2022-03-01
 */
@RestController
@RequestMapping("/store")
@Api("门店操作接口")
public class StoreController {
    @Resource
    private StoreService storeService;

    @ApiOperation(value = "增加门店信息")
    @ApiImplicitParam(name = "storeAddVO", value = "增加门店接口参数", required = true, dataTypeClass = StoreAddVO.class)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public CommonResult<Map<Object, Object>> addOne(@Validated @RequestBody StoreAddVO storeAddVO) {
        return storeService.addOne(storeAddVO);
    }

    @ApiOperation(value = "删除门店信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "storeId", value = "门店 ID", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "operatorId", value = "操作者 ID", required = true, dataTypeClass = Integer.class)
    })
    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    public CommonResult<Map<Object, Object>> delOne(@RequestParam(value = "storeId")
                                                    @NotNull(message = "门店 ID 不能为空")
                                                    @Positive(message = "不合法的门店 ID")
                                                            Integer storeId,
                                                    @RequestParam(value = "operatorId")
                                                    @NotNull(message = "操作者 ID 不能为空")
                                                    @Positive(message = "不合法的操作者 ID")
                                                            Integer operatorId) {
        return storeService.delOne(storeId, operatorId);
    }

    @ApiOperation(value = "获取所有门店信息")
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
        return storeService.getAll(pageSize, pageIndex);
    }

    @ApiOperation(value = "筛选门店信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "门店 ID", example = "1", allowEmptyValue = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "storeName", value = "门店名称", example = "测试门店", allowEmptyValue = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "query", name = "address", value = "门店地址", example = "江苏省南京市浦口区宁六路", allowEmptyValue = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "query", name = "stockLast", value = "当前可用存放量", example = "0", allowEmptyValue = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "managerId", value = "店长 ID", example = "1", allowEmptyValue = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(paramType = "query", name = "isDisable", value = "是否禁用", example = "false", allowEmptyValue = true, dataTypeClass = Boolean.class),
    })
    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public CommonResult<Map<Object, Object>> select(@RequestParam(value = "id", required = false) @Positive(message = "不合法的门店 ID") Integer id,
                                                    @RequestParam(value = "storeName", required = false) String storeName,
                                                    @RequestParam(value = "address", required = false) String address,
                                                    @RequestParam(value = "stockLast", required = false) Integer stockLast,
                                                    @RequestParam(value = "managerId", required = false) @Positive(message = "不合法的用户 ID") Integer managerId,
                                                    @RequestParam(value = "isDisable", required = false) Boolean isDisable) {
        return storeService.select(id, storeName, address, stockLast, managerId, isDisable);
    }

    public CommonResult<Map<Object, Object>> updateOne(@Validated @RequestBody StoreUpdateVO storeUpdateVO) {
        return storeService.updateOne(storeUpdateVO);
    }
}

