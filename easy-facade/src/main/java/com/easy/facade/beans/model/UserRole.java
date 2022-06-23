package com.easy.facade.beans.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.easy.facade.beans.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户角色关联
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/16 14:40
 */
@ApiModel(value = "用户角色关联")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "easy_user_role")
public class UserRole extends BaseEntity {
    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    @ApiModelProperty(value = "角色ID")
    private Long roleId;
}
