package com.easy.facade.beans.dto;

import com.easy.facade.beans.base.BaseDTO;
import com.easy.facade.enums.MenuTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * 菜单查询/分页入参
 * </p>
 *
 * @Author Matt
 * @Date 2022/7/8 10:12
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("菜单查询/分页入参")
public class MenuSearchDTO extends BaseDTO {

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    private String menuName;

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
     * 菜单类型（M目录 C菜单 F按钮）
     */
    @NotBlank(message = "菜单类型不能为空")
    @ApiModelProperty(value = "菜单类型")
    private MenuTypeEnum menuType;

    /**
     * 权限标识
     */
    @ApiModelProperty(value = "权限标识")
    private String perms;
}
