package com.lizijing.carrental.entity.vo;

import com.lizijing.carrental.utils.validation.isNum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

/**
 * <p> 修改维修单信息接口参数 </p>
 *
 * @author LiZijing
 * @date 2022/3/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "修改维修单信息接口参数")
public class RepairUpdateVO {

    @ApiModelProperty(
            name = "id",
            value = "维修单 ID",
            example = "1",
            position = 1
    )
    @Positive(message = "不合法的维修单 ID")
    private Long id;

    @ApiModelProperty(
            name = "repairNum",
            value = "维修单编号",
            example = "REP20220317000001",
            position = 2
    )
    @isNum(message = "维修单编号格式不正确")
    private String repairNum;

    @ApiModelProperty(
            name = "userId",
            value = "用户 ID",
            example = "1",
            position = 3
    )
    @Positive(message = "不合法的用户 ID")
    private Long userId;

    @ApiModelProperty(
            name = "carId",
            value = "车辆 ID",
            example = "1",
            position = 4
    )
    @Positive(message = "不合法的车辆 ID")
    private Long carId;

    @ApiModelProperty(
            name = "operatorId",
            value = "维修员 ID",
            example = "1",
            position = 5
    )
    @Positive(message = "不合法的维修员 ID")
    private Long operatorId;

    @ApiModelProperty(
            name = "description",
            value = "维修单描述",
            position = 6
    )
    private String description;
}
