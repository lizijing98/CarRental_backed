package com.lizijing.carrental.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p> 增加车辆接口参数 </p>
 *
 * @author LiZijing
 * @date 2022/2/27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "增加车辆接口参数")
public class CarAddVO {

    @ApiModelProperty(
            name = "carNumber",
            value = "车牌号",
            required = true,
            example = "苏A00000",
            position = 1
    )
    @NotBlank(message = "车牌号不能为空")
    private String carNumber;

    @ApiModelProperty(
            name = "brand",
            value = "品牌",
            required = true,
            example = "丰田",
            position = 2
    )
    @NotBlank(message = "品牌不能为空")
    private String brand;

    @ApiModelProperty(
            name = "model",
            value = "型号",
            required = true,
            example = "亚洲龙 2020 款",
            position = 3
    )
    @NotBlank(message = "型号不能为空")
    private String model;

    @ApiModelProperty(
            name = "type",
            value = "类型",
            required = true,
            example = "轿车",
            position = 4
    )
    @NotBlank(message = "类型不能为空")
    private String type;

    @ApiModelProperty(
            name = "price",
            value = "租金/天",
            example = "200.00",
            position = 5
    )
    @DecimalMin(value = "0", message = "不合法的金额")
    private Double price;

    @ApiModelProperty(
            name = "deposit",
            value = "定金",
            example = "1000.00",
            position = 6
    )
    @DecimalMin(value = "0", message = "不合法的金额")
    private Double deposit;

    @ApiModelProperty(
            name = "storeName",
            value = "当前所在门店名称",
            example = "公司总仓库",
            position = 7
    )
    private String storeName;

    @ApiModelProperty(
            name = "img",
            value = "车辆照片",
            position = 8,
            allowEmptyValue = true
    )
    private String img;

    @ApiModelProperty(
            name = "operatorId",
            value = "操作员 ID",
            example = "1",
            position = 9
    )
    @NotNull(message = "操作员 ID 不能为空")
    private Long operatorId;
}
