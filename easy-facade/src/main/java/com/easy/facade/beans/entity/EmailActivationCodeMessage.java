package com.easy.facade.beans.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 邮件激活码信息
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/21 14:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "邮件激活码信息")
public class EmailActivationCodeMessage implements Serializable {

    private static final long serialVersionUID = -1773910494750055517L;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "用户账号")
    private String username;

    @ApiModelProperty(value = "用户邮箱")
    private String[] userEmail;

}
