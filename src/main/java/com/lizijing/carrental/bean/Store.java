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
 * 门店表
 * </p>
 *
 * @author LiZijing
 * @since 2022-02-27
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_store")
@ApiModel(value = "Store对象", description = "门店表")
public class Store implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("门店 ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("门店名称")
    @TableField("store_name")
    private String storeName;

    @ApiModelProperty("门店地址")
    @TableField("address")
    private String address;

    @ApiModelProperty("门店车辆存放上限")
    @TableField("stock_limit")
    private Integer stockLimit;

    @ApiModelProperty("店长 ID")
    @TableField("manager_id")
    private Long managerId;

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

    @ApiModelProperty("禁用标记,0 代表未禁用,1 代表已禁用")
    @TableField("is_disable")
    private Boolean isDisable;


}
