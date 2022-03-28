package com.lizijing.carrental.entity.vo;

import com.lizijing.carrental.utils.validation.isNum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

/**
 * <p> 修改事故单接口参数 </p>
 *
 * @author LiZijing
 * @date 2022/3/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "修改事故单接口参数")
public class AccidentUpdateVO {

    @ApiModelProperty(
            name = "id",
            value = "事故单 ID",
            example = "1",
            position = 1
    )
    @Positive(message = "不合法的 ID")
    private Long id;

    @ApiModelProperty(
            name = "accidentNum",
            value = "事故单编号",
            example = "ACC20220317000001",
            position = 2
    )
    @isNum(message = "事故单编号格式不正确")
    private String accidentNum;

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
            value = "操作员 ID",
            example = "1",
            position = 5
    )
    @Positive(message = "不合法的操作员 ID")
    private Long operatorId;

    @ApiModelProperty(
            name = "repairNum",
            value = "维修单编号",
            example = "REP20220318000001",
            position = 6
    )
    @isNum(message = "维修单编号格式不正确")
    private String repairNum;

    @ApiModelProperty(
            name = "description",
            value = "事故单描述",
            position = 7
    )
    private String description;
}
