package com.easy.facade.beans.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.easy.facade.beans.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/16 14:38
 */
@ApiModel(value = "角色")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "easy_role")
public class Role extends BaseEntity {
    /**
     * 角色名字
     */
    @TableField(value = "role_name")
    @ApiModelProperty(value = "角色名字")
    private String roleName;

    /**
     * 角色key
     */
    @TableField(value = "role_key")
    @ApiModelProperty(value = "角色key")
    private String roleKey;

    /**
     * 显示顺序
     */
    @TableField(value = "role_sort")
    @ApiModelProperty(value = "显示顺序")
    private Integer roleSort;

    /**
     * 角色状态（0正常 1停用）
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value = "角色状态（0正常 1停用）")
    private Integer status;
}
