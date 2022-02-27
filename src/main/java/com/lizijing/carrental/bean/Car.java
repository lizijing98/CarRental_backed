package com.lizijing.carrental.bean;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 车辆表
 * </p>
 *
 * @author LiZijing
 * @since 2022-02-27
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_car")
@ApiModel(value = "Car对象", description = "车辆表")
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("车辆 ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("车牌号")
    @TableField("car_number")
    private String carNumber;

    @ApiModelProperty("品牌")
    @TableField("brand")
    private String brand;

    @ApiModelProperty("型号")
    @TableField("model")
    private String model;

    @ApiModelProperty("车型")
    @TableField("`type`")
    private String type;

    @ApiModelProperty("租金/天")
    @TableField("price")
    private Double price;

    @ApiModelProperty("定金")
    @TableField("deposit")
    private Double deposit;

    @ApiModelProperty("车辆所在地址")
    @TableField("address")
    private String address;

    @ApiModelProperty("车辆照片")
    @TableField("img")
    private String img;

    @ApiModelProperty("车辆当前状态")
    @TableField("`status`")
    private String status;

    @ApiModelProperty("备注")
    @TableField("description")
    private String description;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty("删除标记,0 代表未删除,1 代表已删除")
    @TableField("is_deleted")
    @TableLogic
    private Boolean isDeleted;

    @ApiModelProperty("可用标记,0 为可用,1 为不可用")
    @TableField("is_usable")
    private Boolean isUsable;


}