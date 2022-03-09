package com.lizijing.carrental.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * <p> 修改门店信息接口参数 </p>
 *
 * @author LiZijing
 * @date 2022/3/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "修改门店信息接口参数")
public class StoreUpdateVO {

    @ApiModelProperty(
            name = "id",
            value = "门店 ID",
            required = true,
            example = "1",
            position = 1
    )
    private Integer id;

    @ApiModelProperty(
            name = "storeName",
            value = "门店名",
            example = "测试门店 1",
            position = 2
    )
    private String storeName;

    @ApiModelProperty(
            name = "address",
            value = "门店地址",
            example = "江苏省南京市浦口区宁六路 219 号",
            position = 3
    )
    private String address;

    @ApiModelProperty(
            name = "stockLimit",
            value = "门店车库容量",
            example = "5",
            position = 4
    )
    @Positive(message = "不合法的库存数")
    private Integer stockLimit;

    @ApiModelProperty(
            name = "managerId",
            value = "门店店长 ID",
            example = "5",
            position = 5
    )
    @Positive(message = "不合法的 ID")
    private Long managerId;

    @ApiModelProperty(
            name = "operatorId",
            value = "操作员 ID",
            example = "1",
            required = true,
            position = 6
    )
    @NotNull(message = "操作员 ID 不能为空")
    private Long operatorId;

    @ApiModelProperty(
            name = "description",
            value = "备注",
            allowEmptyValue = true,
            position = 7
    )
    private String description;

    @ApiModelProperty(
            name = "isDisable",
            value = "是否禁用",
            allowEmptyValue = true,
            position = 8
    )
    private Boolean isDisable;
}
