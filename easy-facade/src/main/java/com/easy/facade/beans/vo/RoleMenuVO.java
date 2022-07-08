package com.easy.facade.beans.vo;

import com.easy.facade.enums.MenuTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色菜单权限
 * </p>
 *
 * @Author Matt
 * @Date 2022/7/8 17:34
 */
@Data
@ApiModel("角色菜单权限")
public class RoleMenuVO implements Serializable {

    private static final long serialVersionUID = -3030215698036699435L;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    /**
     * 菜单ID
     */
    @ApiModelProperty(value = "菜单ID")
    private String menuId;

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
     * 菜单类型
     */
    @ApiModelProperty(value = "菜单类型（0目录;1页面;2按钮）")
    private MenuTypeEnum menuType;

    /**
     * 子菜单
     */
    @ApiModelProperty(value = "子菜单")
    private List<RoleMenuVO> child;
}
