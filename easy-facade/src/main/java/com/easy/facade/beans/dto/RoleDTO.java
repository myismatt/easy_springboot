package com.easy.facade.beans.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 角色入参
 * </p>
 *
 * @Author Matt
 * @Date 2022/7/7 14:06
 */
@Data
@ApiModel("角色入参")
public class RoleDTO {
    /**
     * 角色名字
     */
    @NotBlank(message = "角色名字不能为空")
    @ApiModelProperty(value = "角色名字")
    private String roleName;

    /**
     * 角色key
     */
    @NotBlank(message = "角色代码不能为空")
    @ApiModelProperty(value = "角色代码")
    private String roleKey;

    /**
     * 显示顺序
     */
    @ApiModelProperty(value = "显示顺序")
    private Integer roleSort;
}
