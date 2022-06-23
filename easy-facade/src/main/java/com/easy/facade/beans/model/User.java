package com.easy.facade.beans.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.easy.facade.beans.base.BaseEntity;
import com.easy.facade.enums.AccountStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/16 14:28
 */
@ApiModel(value = "用户")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "easy_user")
public class User extends BaseEntity {
    /**
     * 用户特殊编码
     */
    @TableField(value = "user_key")
    @ApiModelProperty(value = "用户特殊编码")
    private String userKey;

    /**
     * 账号
     */
    @TableField(value = "username")
    @ApiModelProperty(value = "账号")
    private String username;

    /**
     * 手机号
     */
    @TableField(value = "phone")
    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 密码
     */
    @TableField(value = "`password`")
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 注册时间
     */
    @TableField(value = "register_time")
    @ApiModelProperty(value = "注册时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String registerTime;

    /**
     * 账号状态
     */
    @TableField(value = "account_status")
    @ApiModelProperty(value = "账号状态")
    private AccountStatusEnum accountStatus;
}
