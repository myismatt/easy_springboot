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
 * 字典数据
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/16 14:36
 */
@ApiModel(value = "字典数据")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "easy_dict_data")
public class DictData extends BaseEntity {
    /**
     * 字典类型ID
     */
    @TableField(value = "dict_type_id")
    @ApiModelProperty(value = "字典类型ID")
    private String dictTypeId;

    /**
     * 字典类型
     */
    @TableField(value = "dict_type")
    @ApiModelProperty(value = "字典类型")
    private String dictType;

    /**
     * 字典排序
     */
    @TableField(value = "dict_sort")
    @ApiModelProperty(value = "字典排序")
    private Integer dictSort;

    /**
     * 字典标签
     */
    @TableField(value = "dict_label")
    @ApiModelProperty(value = "字典标签")
    private String dictLabel;

    /**
     * 字典键值
     */
    @TableField(value = "dict_value")
    @ApiModelProperty(value = "字典键值")
    private String dictValue;

    /**
     * 样式属性（其他样式扩展）
     */
    @TableField(value = "css_class")
    @ApiModelProperty(value = "样式属性（其他样式扩展）")
    private String cssClass;

    /**
     * 表格回显样式
     */
    @TableField(value = "list_class")
    @ApiModelProperty(value = "表格回显样式")
    private String listClass;

    /**
     * 是否默认(0否,1-是)
     */
    @TableField(value = "is_default")
    @ApiModelProperty(value = "是否默认(0否,1-是)")
    private YesOrNoEnum isDefault;

    /**
     * 状态（0正常 1停用）
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value = "状态（0正常 1停用）")
    private Integer status;
}
