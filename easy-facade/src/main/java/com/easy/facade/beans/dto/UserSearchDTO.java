package com.easy.facade.beans.dto;

import com.easy.facade.beans.base.BaseDTO;
import com.easy.facade.enums.AccountStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户信息查询/入参
 * </p>
 *
 * @Author Matt
 * @Date 2022/7/19 13:59
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("用户信息查询-入参")
public class UserSearchDTO extends BaseDTO {
    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    private String username;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 角色名字
     */
    @ApiModelProperty(value = "角色名字")
    private String roleName;

    /**
     * 账号状态
     */
    @ApiModelProperty(value = "账号状态")
    private AccountStatusEnum accountStatus;
}