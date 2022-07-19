package com.easy.facade.beans.dto;

import com.easy.facade.enums.AccountStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户信息
 * </p>
 *
 * @Author Matt
 * @Date 2022/7/19 13:52
 */
@Data
@ApiModel("用户信息")
public class UserInfoDTO {

    /**
     * 账号
     */
    @NotBlank(message = "账号不能为空")
    @ApiModelProperty(value = "账号")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    @ApiModelProperty(value = "昵称")
    private String nickname;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 账号状态
     */
    @NotNull(message = "账号状态不能为空")
    @ApiModelProperty(value = "账号状态")
    private AccountStatusEnum accountStatus;

    /**
     * 角色ID集合
     */
    @ApiModelProperty(value = "角色ID集合")
    private List<String> roleIdList;
}