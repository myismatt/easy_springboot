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
 * 字典类型
 * </p>
 *
 * @Author Matt
 * @Date 2022/7/18 16:40
 */
@ApiModel(value = "字典类型")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "easy_dict_type")
public class DictType extends BaseEntity {
    /**
     * 字典名称
     */
    @TableField(value = "dict_name")
    @ApiModelProperty(value = "字典名称")
    private String dictName;

    /**
     * 字典类型
     */
    @TableField(value = "dict_type")
    @ApiModelProperty(value = "字典类型")
    private String dictType;

    /**
     * 是否启用(0-否,1-是)
     */
    @TableField(value = "`enable`")
    @ApiModelProperty(value = "是否启用(0-否,1-是)")
    private YesOrNoEnum enable;
}