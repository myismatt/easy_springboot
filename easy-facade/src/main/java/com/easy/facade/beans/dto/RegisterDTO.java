package com.easy.facade.beans.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 入参-注册用户
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/20 10:43
 */
@Data
@ApiModel(value = "入参-注册用户")
public class RegisterDTO {


    @ApiModelProperty(value = "账号")
    @NotBlank(message = "账号不能为空")
    @Pattern(regexp = "^[a-zA-Z\\d]{0,16}$", message = "账号只能有大小写字母与数字,且长度小于16位!")
    private String username;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^(?![a-zA-Z]+$)(?![A-Z\\d]+$)(?![A-Z]+$)(?![a-z\\d]+$)(?![a-z]+$)(?!\\d+$)[a-zA-Z\\d]{8,20}$", message = "密码必须包含小写字母、大写字母与数字,且在8-20位之间!")
    private String password;

    @ApiModelProperty(value = "邮箱")
    @NotBlank(message = "邮箱为找回密码的必须设置")
    @Email(message = "邮箱不正确")
    private String email;
}
