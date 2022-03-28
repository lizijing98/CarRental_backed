package com.lizijing.carrental.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

/**
 * <p> 新增订单接口参数 </p>
 *
 * @author LiZijing
 * @date 2022/3/21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "增加订单接口参数")
public class OrderAddVO {

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
            value = "员工 ID",
            required = true,
            example = "1",
            position = 3
    )
    @Positive(message = "不合法的员工 ID")
    private Long operatorId;

    @ApiModelProperty(
            name = "startTime",
            value = "开始时间",
            required = true,
            example = "2022-03-21 21:55:03",
            position = 4
    )
    private LocalDateTime startTime;

    @ApiModelProperty(
            name = "startStoreId",
            value = "租车门店 ID",
            required = true,
            example = "1",
            position = 5
    )
    @Positive(message = "不合法的门店 ID")
    private Long startStoreId;

    @ApiModelProperty(
            name = "description",
            value = "订单描述",
            required = true,
            position = 6
    )
    private String description;
}
