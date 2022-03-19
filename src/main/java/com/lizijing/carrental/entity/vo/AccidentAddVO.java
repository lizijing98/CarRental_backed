package com.lizijing.carrental.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

/**
 * <p> 新增事故单接口参数 </p>
 *
 * @author LiZijing
 * @date 2022/3/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "新增事故单接口参数")
public class AccidentAddVO {

    @ApiModelProperty(
            name = "userId",
            value = "用户 ID",
            required = true,
            example = "1",
            position = 1
    )
    @Positive(message = "不合法的用户 ID")
    private Long userId;

    @ApiModelProperty(
            name = "carId",
            value = "车辆 ID",
            required = true,
            example = "1",
            position = 2
    )
    @Positive(message = "不合法的车辆 ID")
    private Long carId;

    @ApiModelProperty(
            name = "operatorId",
            value = "维修员 ID",
            required = true,
            example = "1",
            position = 3
    )
    @Positive(message = "不合法的维修员 ID")
    private Long operatorId;

    @ApiModelProperty(
            name = "description",
            value = "维修单描述",
            required = true,
            position = 4
    )
    private String description;

    @ApiModelProperty(
            name = "isCreateRepair",
            value = "是否创建维修单",
            required = true,
            example = "true",
            position = 5
    )
    private Boolean isCreateRepair;
}
