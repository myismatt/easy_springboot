package com.easy.facade.beans.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/28 14:38
 */
@Data
@ApiModel(value = "用户信息")
public class UserInfoVO implements Serializable {

    private static final long serialVersionUID = 4395644466334966721L;

    /**
     * 用户特殊编码
     */
    @ApiModelProperty(value = "用户特殊编码")
    private String userKey;

    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    private String username;
}
