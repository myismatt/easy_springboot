package com.easy.facade.beans.vo;

import com.easy.facade.enums.YesOrNoEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色信息
 * </p>
 *
 * @Author Matt
 * @Date 2022/7/8 17:28
 */
@Data
@ApiModel("角色信息")
public class RoleInfoVO implements Serializable {

    private static final long serialVersionUID = 1783498414730270063L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private String id;

    /**
     * 角色名字
     */
    @ApiModelProperty(value = "角色名字")
    private String roleName;

    /**
     * 角色key
     */
    @ApiModelProperty(value = "角色key")
    private String roleKey;

    /**
     * 显示顺序
     */
    @ApiModelProperty(value = "显示顺序")
    private Integer roleSort;

    /**
     * 是否启用（0停用;1正常）
     */
    @ApiModelProperty(value = "是否启用（0停用;1正常）")
    private YesOrNoEnum enable;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 菜单集合
     */
    @ApiModelProperty(value = "菜单集合")
    private List<RoleMenuVO> menuList;
}
