package com.lizijing.carrental.entity.vo;

import com.lizijing.carrental.utils.validation.Mobile;
import com.lizijing.carrental.utils.validation.RoleName;
import com.lizijing.carrental.utils.validation.Sex;
import com.lizijing.carrental.utils.validation.Username;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

/**
 * <p> 增加用户接口参数 </p>
 *
 * @author LiZijing
 * @date 2022/3/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "增加用户接口参数")
public class UserAddVO {

    @ApiModelProperty(
            name = "username",
            value = "用户名",
            required = true,
            example = "test-user1",
            position = 1
    )
    @Length(max = 10,message = "用户名支持 0~10 位字符")
    @Username
    private String username;

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
            required = true,
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
    @Sex
    private String sex;

    @ApiModelProperty(
            name = "phoneNumber",
            value = "手机号码",
            example = "17612341234",
            position = 6
    )
    @Mobile
    private String phoneNumber;

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

    @ApiModelProperty(
            name = "roleName",
            value = "角色名称",
            required = true,
            example = "USER",
            position = 9
    )
    @RoleName
    private String roleName;
}
