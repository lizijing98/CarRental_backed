package com.lizijing.carrental.entity.bean;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author LiZijing
 * @since 2022-02-27
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_order")
@ApiModel(value = "Order对象", description = "订单表")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单 ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("订单编号")
    @TableField("order_num")
    private String orderNum;

    @ApiModelProperty("用户 ID")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("车辆 ID")
    @TableField("car_id")
    private Long carId;

    @ApiModelProperty("操作员 ID")
    @TableField("operator_id")
    private Long operatorId;

    @ApiModelProperty("租车门店 ID")
    @TableField(value = "start_store_id")
    private Long startStoreId;

    @ApiModelProperty("还车门店 ID")
    @TableField(value = "finish_store_id")
    private Long finishStoreId;

    @ApiModelProperty("开始时间")
    @TableField("start_time")
    private LocalDateTime startTime;

    @ApiModelProperty("结束时间")
    @TableField("finish_time")
    private LocalDateTime finishTime;

    @ApiModelProperty("订单总时间")
    @TableField("total_time")
    private Double totalTime;

    @ApiModelProperty("订单总价")
    @TableField("total_price")
    private Double totalPrice;

    @ApiModelProperty("当前状态")
    @TableField("`status`")
    private String status;

    @ApiModelProperty("备注")
    @TableField("description")
    private String description;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("删除标记,0 代表未删除,1 代表已删除")
    @TableField("is_deleted")
    @TableLogic(value = "0", delval = "1")
    private Boolean isDeleted;


}
