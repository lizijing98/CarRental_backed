package com.lizijing.carrental.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * <p> 修改车辆信息接口参数 </p>
 *
 * @author LiZijing
 * @date 2022/3/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "修改车辆信息接口参数")
public class CarUpdateVO {

    @ApiModelProperty(
            name = "id",
            value = "车辆 ID",
            required = true,
            example = "1",
            position = 1
    )
    @NotNull(message = "车辆 ID")
    private Integer id;

    @ApiModelProperty(
            name = "carNumber",
            value = "车牌号",
            example = "苏A00000",
            position = 2
    )
    private String carNumber;

    @ApiModelProperty(
            name = "brand",
            value = "品牌",
            example = "丰田",
            position = 3
    )
    private String brand;

    @ApiModelProperty(
            name = "model",
            value = "型号",
            example = "亚洲龙 2020 款",
            position = 4
    )
    private String model;

    @ApiModelProperty(
            name = "type",
            value = "类型",
            example = "轿车",
            position = 5
    )
    private String type;

    @ApiModelProperty(
            name = "price",
            value = "租金/天",
            example = "200.00",
            position = 6
    )
    @DecimalMin(value = "0", message = "不合法的金额")
    private Double price;

    @ApiModelProperty(
            name = "deposit",
            value = "定金",
            example = "1000.00",
            position = 7
    )
    @DecimalMin(value = "0", message = "不合法的金额")
    private Double deposit;

    @ApiModelProperty(
            name = "storeName",
            value = "当前所在门店名称",
            example = "公司总仓库",
            position = 8
    )
    private String storeName;

    @ApiModelProperty(
            name = "img",
            value = "车辆照片",
            position = 9,
            allowEmptyValue = true
    )
    private String img;

    @ApiModelProperty(
            name = "operatorId",
            value = "操作员 ID",
            example = "1",
            position = 10
    )
    @NotNull(message = "操作员 ID 不能为空")
    private Long operatorId;

    @ApiModelProperty(
            name = "description",
            value = "备注",
            allowEmptyValue = true,
            position = 10
    )
    private String description;
}
