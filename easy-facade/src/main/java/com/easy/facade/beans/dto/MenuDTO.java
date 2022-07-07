package com.easy.facade.beans.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.easy.facade.enums.MenuTypeEnum;
import com.easy.facade.enums.YesOrNoEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 菜单入参
 * </p>
 *
 * @Author Matt
 * @Date 2022/7/7 14:22
 */
@Data
@ApiModel("菜单入参")
public class MenuDTO {
    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空")
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
    @NotNull(message = "顺序不能为空")
    @ApiModelProperty(value = "显示顺序")
    private Integer orderNum;

    /**
     * 路由地址
     */
    @ApiModelProperty(value = "路由地址")
    private String path;

    /**
     * 组件路径
     */
    @ApiModelProperty(value = "组件路径")
    private String component;

    /**
     * 是否为外链(0-否,1-是)
     */
    @NotNull(message = "是否为外链不能为空")
    @ApiModelProperty(value = "是否为外链(0-否,1-是)")
    private YesOrNoEnum isFrame;

    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    @NotBlank(message = "菜单类型不能为空")
    @ApiModelProperty(value = "菜单类型")
    private MenuTypeEnum menuType;

    /**
     * 是否显示
     */
    @NotNull(message = "显示状态不能为空")
    @ApiModelProperty(value = "是否显示")
    private YesOrNoEnum show;

    /**
     * 是否启用
     */
    @NotNull(message = "启用状态不能为空")
    @ApiModelProperty(value = "是否启用")
    private YesOrNoEnum enable;

    /**
     * 权限标识
     */
    @ApiModelProperty(value = "权限标识")
    private String perms;

    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标")
    private String icon;
}
