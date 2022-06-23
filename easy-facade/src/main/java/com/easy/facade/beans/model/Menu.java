package com.easy.facade.beans.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.easy.facade.beans.base.BaseEntity;
import com.easy.facade.enums.YesOrNoEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单路由
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/16 14:41
 */
@ApiModel(value = "菜单路由")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "easy_menu")
public class Menu extends BaseEntity {
    /**
     * 菜单名称
     */
    @TableField(value = "menu_name")
    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    /**
     * 父菜单ID
     */
    @TableField(value = "parent_id")
    @ApiModelProperty(value = "父菜单ID")
    private String parentId;

    /**
     * 显示顺序
     */
    @TableField(value = "order_num")
    @ApiModelProperty(value = "显示顺序")
    private Integer orderNum;

    /**
     * 路由地址
     */
    @TableField(value = "`path`")
    @ApiModelProperty(value = "路由地址")
    private String path;

    /**
     * 组件路径
     */
    @TableField(value = "component")
    @ApiModelProperty(value = "组件路径")
    private String component;

    /**
     * 是否为外链(0-否,1-是)
     */
    @TableField(value = "is_frame")
    @ApiModelProperty(value = "是否为外链(0-否,1-是)")
    private YesOrNoEnum isFrame;

    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    @TableField(value = "menu_type")
    @ApiModelProperty(value = "菜单类型（M目录 C菜单 F按钮）")
    private String menuType;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    @TableField(value = "visible")
    @ApiModelProperty(value = "菜单状态（0显示 1隐藏）")
    private Integer visible;

    /**
     * 菜单状态（0正常 1停用）
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value = "菜单状态（0正常 1停用）")
    private Integer status;

    /**
     * 权限标识
     */
    @TableField(value = "perms")
    @ApiModelProperty(value = "权限标识")
    private String perms;

    /**
     * 菜单图标
     */
    @TableField(value = "icon")
    @ApiModelProperty(value = "菜单图标")
    private String icon;
}
