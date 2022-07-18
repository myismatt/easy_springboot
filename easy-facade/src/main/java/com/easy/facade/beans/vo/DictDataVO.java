package com.easy.facade.beans.vo;

import com.easy.facade.enums.YesOrNoEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 字典数据
 * <p>
 * 2022/3/9 9:54
 *
 * @Author Matt
 */
@Data
@ApiModel("字典数据")
public class DictDataVO implements Serializable {


    private static final long serialVersionUID = 8119341450293676729L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private String id;

    /**
     * 字典类型ID
     */
    @ApiModelProperty(value = "字典类型ID")
    private String dictTypeId;

    /**
     * 字典类型
     */
    @ApiModelProperty(value = "字典类型")
    private String dictType;

    /**
     * 字典标签
     */
    @ApiModelProperty(value = "字典标签", allowEmptyValue = true)
    private String dictLabel;

    /**
     * 字典键值
     */
    @ApiModelProperty(value = "字典键值", allowEmptyValue = true)
    private String dictValue;

    /**
     * 字典排序
     */
    @ApiModelProperty(value = "字典排序", allowEmptyValue = true)
    private Integer dictSort;

    /**
     * 样式属性（其他样式扩展）
     */
    @ApiModelProperty(value = "样式属性（其他样式扩展）", allowEmptyValue = true)
    private String cssClass;

    /**
     * 是否默认(0否,1-是)
     */
    @ApiModelProperty(value = "是否默认(0否,1-是)")
    private YesOrNoEnum isDefault;

    /**
     * 是否启用（0-否,1-是）
     */
    @ApiModelProperty(value = "是否启用（0-否,1-是）", allowEmptyValue = true)
    private YesOrNoEnum enable;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", allowEmptyValue = true)
    private String remark;
}