package com.lizijing.carrental.entity.vo;

import com.lizijing.carrental.utils.validation.isNum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

/**
 * <p> 修改订单信息参数 </p>
 *
 * @author LiZijing
 * @date 2022/3/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "修改订单信息参数")
public class OrderUpdateVO {

    @ApiModelProperty(
            name = "id",
            value = "订单 ID",
            example = "1",
            position = 1
    )
    @Positive(message = "不合法的订单 ID")
    private Long id;

    @ApiModelProperty(
            name = "orderNum",
            value = "订单编号",
            example = "ORD20220317000001",
            position = 2
    )
    @isNum(message = "订单编号格式不正确")
    private String orderNum;

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
            value = "员工 ID",
            example = "1",
            position = 5
    )
    @Positive(message = "不合法的员工 ID")
    private Long operatorId;

    @ApiModelProperty(
            name = "startTime",
            value = "开始时间",
            example = "2022-03-21 21:55:03",
            position = 6
    )
    private LocalDateTime startTime;

    @ApiModelProperty(
            name = "finishTime",
            value = "结束时间",
            example = "2022-03-22 14:21:20",
            position = 7
    )
    private LocalDateTime finishTime;

    @ApiModelProperty(
            name = "startStoreId",
            value = "租车门店 ID",
            example = "1",
            position = 8
    )
    @Positive(message = "不合法的门店 ID")
    private Long startStoreId;

    @ApiModelProperty(
            name = "finishStoreId",
            value = "还车门店 ID",
            example = "1",
            position = 9
    )
    @Positive(message = "不合法的门店 ID")
    private Long finishStoreId;

    @ApiModelProperty(
            name = "description",
            value = "订单描述",
            position = 10
    )
    private String description;
}
