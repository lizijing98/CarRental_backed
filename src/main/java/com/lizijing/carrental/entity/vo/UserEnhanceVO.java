package com.lizijing.carrental.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p> 修改用户角色接口参数 </p>
 *
 * @author LiZijing
 * @date 2022/3/11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "修改用户角色接口参数")
public class UserEnhanceVO {

    @ApiModelProperty(
            name = "userId",
            value = "用户 ID",
            required = true,
            example = "1",
            position = 1
    )
    private Long userId;

    @ApiModelProperty(
            name = "roleName",
            value = "角色名称",
            required = true,
            example = "USER",
            position = 2
    )
    private String roleName;
    // todo:增加用户角色校验
}
