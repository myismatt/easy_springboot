package com.easy.facade.beans.dto;

import com.easy.facade.beans.base.BaseDTO;
import com.easy.facade.enums.YesOrNoEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 参数配置查询/分页入参
 *
 * @Author Matt
 * @Date 2021/8/9 14:13
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("参数配置查询/分页入参")
public class ConfigSearchDTO extends BaseDTO {
    /**
     * 参数名称
     */
    @ApiModelProperty(value = "参数名称")
    private String configName;

    /**
     * 参数键名
     */
    @ApiModelProperty(value = "参数键名")
    private String configKey;

    /**
     * 系统内置
     */
    @ApiModelProperty(value = "系统内置")
    private YesOrNoEnum configType;


}
