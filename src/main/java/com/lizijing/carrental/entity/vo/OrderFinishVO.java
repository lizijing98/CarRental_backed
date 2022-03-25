package com.lizijing.carrental.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

/**
 * <p> 完成订单接口参数 </p>
 *
 * @author LiZijing
 * @date 2022/3/21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "完成订单接口参数")
public class OrderFinishVO {

    @ApiModelProperty(
            name = "orderNum",
            value = "订单编号",
            example = "ORD20220317000001",
            required = true,
            position = 1
    )
    private String orderNum;

    @ApiModelProperty(
            name = "operatorId",
            value = "员工 ID",
            required = true,
            example = "1",
            position = 2
    )
    @Positive(message = "不合法的员工 ID")
    private Long operatorId;

    @ApiModelProperty(
            name = "finishTime",
            value = "订单结束时间",
            example = "2022-03-21T22:35:39Z",
            required = true,
            position = 3
    )
    private LocalDateTime finishTime;

    @ApiModelProperty(
            name = "finishStoreId",
            value = "还车门店 ID",
            example = "1",
            required = true,
            position = 4
    )
    private Long finishStoreId;

    @ApiModelProperty(
            name = "status",
            value = "订单状态",
            example = "COMPLETED",
            position = 5
    )
    private String status;

}
