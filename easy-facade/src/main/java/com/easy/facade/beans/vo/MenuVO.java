package com.easy.facade.beans.vo;

import com.easy.facade.enums.MenuTypeEnum;
import com.easy.facade.enums.YesOrNoEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单权限
 * </p>
 *
 * @Author Matt
 * @Date 2022/7/15 14:44
 */
@Data
@ApiModel("参数配置信息")
public class MenuVO implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private String id;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    /**
     * 父菜单ID
     */
    @ApiModelProperty(value = "父菜单ID")
    private String parentId;

    /**
     * 显示顺序
     */
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
     * 是否为外链(0-否;1-是)
     */
    @ApiModelProperty(value = "是否为外链(0-否;1-是),")
    private YesOrNoEnum isFrame;

    /**
     * 菜单类型（0-目录;1-页面;2-按钮）
     */
    @ApiModelProperty(value = "菜单类型（0目录;1页面;2按钮）")
    private MenuTypeEnum menuType;

    /**
     * 菜单是否显示（0隐藏;1显示）
     */
    @ApiModelProperty(value = "菜单是否显示（0隐藏;1显示）")
    private YesOrNoEnum show;

    /**
     * 菜单是否启用（0停用;1正常 ）
     */
    @ApiModelProperty(value = "菜单是否启用（0停用;1正常 ）")
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

    @ApiModelProperty(value = "子菜单")
    private List<MenuVO> child;
}