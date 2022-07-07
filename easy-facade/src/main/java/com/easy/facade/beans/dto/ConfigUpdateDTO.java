package com.easy.facade.beans.dto;

import com.easy.facade.enums.YesOrNoEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 参数配置更新入参
 * </p>
 *
 * @Author Matt
 * @Date 2022/7/7 14:47
 */
@Data
@ApiModel("参数配置更新入参")
public class ConfigUpdateDTO {

    @NotBlank(message = "数据ID不能为空")
    @ApiModelProperty(value = "主键ID")
    private String id;

    /**
     * 参数名称
     */
    @NotBlank(message = "参数名称不能为空")
    @ApiModelProperty(value = "参数名称")
    private String configName;

    /**
     * 参数键值
     */
    @NotBlank(message = "参数键值不能为空")
    @ApiModelProperty(value = "参数键值")
    private String configValue;

    /**
     * 系统内置（0否,1是 ）
     */
    @NotNull(message = "请选择是否系统内置")
    @ApiModelProperty(value = "系统内置(0-否,1-是)")
    private YesOrNoEnum configType;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
