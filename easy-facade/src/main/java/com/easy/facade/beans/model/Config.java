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
 * 参数配置
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/16 14:30
 */
@ApiModel(value = "参数配置")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "easy_config")
public class Config extends BaseEntity {

    /**
     * 参数名称
     */
    @TableField(value = "config_name")
    @ApiModelProperty(value = "参数名称")
    private String configName;

    /**
     * 参数键名
     */
    @TableField(value = "config_key")
    @ApiModelProperty(value = "参数键名")
    private String configKey;

    /**
     * 参数键值
     */
    @TableField(value = "config_value")
    @ApiModelProperty(value = "参数键值")
    private String configValue;

    /**
     * 系统内置（0否,1是 ）
     */
    @TableField(value = "config_type")
    @ApiModelProperty(value = "系统内置(0-否,1-是)")
    private YesOrNoEnum configType;
}
