package com.lizijing.carrental.controller;


import com.lizijing.carrental.entity.vo.StoreAddVO;
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

}

