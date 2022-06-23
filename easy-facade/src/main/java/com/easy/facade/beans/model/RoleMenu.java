package com.easy.facade.beans.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.easy.facade.beans.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色菜单关联
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/16 14:43
 */
@ApiModel(value = "角色菜单关联")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "easy_role_menu")
public class RoleMenu extends BaseEntity {
    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    @ApiModelProperty(value = "角色ID")
    private String roleId;

    /**
     * 菜单ID
     */
    @TableField(value = "menu_id")
    @ApiModelProperty(value = "菜单ID")
    private String menuId;
}
