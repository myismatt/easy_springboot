package com.easy.facade.beans.vo;

import com.easy.facade.enums.AccountStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

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
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private String id;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickname;

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
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 注册时间
     */
    @ApiModelProperty(value = "注册时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String registerTime;

    /**
     * 账号状态
     */
    @ApiModelProperty(value = "账号状态")
    private AccountStatusEnum accountStatus;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 角色信息
     */
    @ApiModelProperty(value = "角色信息")
    private List<RoleInfoVO> roleList;

}
