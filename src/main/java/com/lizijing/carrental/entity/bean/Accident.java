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
 * 事故单表
 * </p>
 *
 * @author LiZijing
 * @since 2022-03-14
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_accident")
@ApiModel(value = "Accident对象", description = "事故单表")
public class Accident implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("事故单 ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("维修单编号")
    @TableField("accident_num")
    private String accidentNum;

    @ApiModelProperty("用户 ID")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("车辆 ID")
    @TableField("car_id")
    private Long carId;

    @ApiModelProperty("操作员 ID")
    @TableField("operator_id")
    private Long operatorId;

    @ApiModelProperty("当前状态")
    @TableField("`status`")
    private String status;

    @ApiModelProperty("事故单编号")
    @TableField("repair_num")
    private String repairNum;

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
    @TableLogic
    private Boolean isDeleted;


}
