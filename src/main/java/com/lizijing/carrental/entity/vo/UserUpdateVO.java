package com.lizijing.carrental.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

/**
 * <p> 修改用户信息接口参数 </p>
 *
 * @author LiZijing
 * @date 2022/3/11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "修改用户信息接口参数")
public class UserUpdateVO {

    @ApiModelProperty(
            name = "id",
            value = "用户 ID",
            example = "1",
            required = true,
            position = 1
    )
    private Integer id;

    @ApiModelProperty(
            name = "nickname",
            value = "用户昵称",
            example = "测试用户 1",
            position = 2
    )
    private String nickname;

    @ApiModelProperty(
            name = "password",
            value = "密码",
            example = "123456",
            position = 3
    )
    private String password;

    @ApiModelProperty(
            name = "realName",
            value = "真实姓名",
            example = "测试用户",
            position = 4
    )
    private String realName;

    @ApiModelProperty(
            name = "sex",
            value = "性别",
            example = "男",
            position = 5
    )
    private String sex;

    @ApiModelProperty(
            name = "phoneNumber",
            value = "手机号码",
            example = "17612341234",
            position = 6
    )
    private String phoneNumber;
    // todo:增加手机号校验

    @ApiModelProperty(
            name = "email",
            value = "邮箱",
            example = "test1@test.com",
            position = 7
    )
    @Email(message = "不正确的邮箱格式")
    private String email;

    @ApiModelProperty(
            name = "description",
            value = "备注",
            allowEmptyValue = true,
            position = 8
    )
    private String description;
}
