package com.easy.facade.beans.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.easy.facade.enums.YesOrNoEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 字典数据更新-入参
 * <p>
 * 2022/3/9 9:46
 *
 * @Author Matt
 */
@Data
@ApiModel("字典数据更新-入参")
public class DictDataUpdateDTO {

    /**
     * 主键ID
     */
    @NotBlank(message = "主键不能为空")
    @ApiModelProperty(value = "主键ID")
    private String id;

    /**
     * 字典类型ID
     */
    @NotBlank(message = "字典类型ID不能为空")
    @ApiModelProperty(value = "字典类型ID")
    private String dictTypeId;

    /**
     * 字典类型
     */
    @NotBlank(message = "字典类型不能为空")
    @ApiModelProperty(value = "字典类型")
    private String dictType;

    /**
     * 字典标签
     */
    @NotBlank(message = "字典标签不能为空")
    @ApiModelProperty(value = "字典标签")
    private String dictLabel;

    /**
     * 字典键值
     */
    @NotBlank(message = "字典键值不能为空")
    @ApiModelProperty(value = "字典键值")
    private String dictValue;

    /**
     * 字典排序
     */
    @NotNull(message = "字典排序不能为空")
    @ApiModelProperty(value = "字典排序")
    private Integer dictSort;

    /**
     * 样式属性（其他样式扩展）
     */
    @ApiModelProperty(value = "样式属性（其他样式扩展）")
    private String cssClass;

    /**
     * 是否默认（0否 1是 ）
     */
    @NotNull(message = "是否默认不能为空")
    @ApiModelProperty(value = "是否默认（NO-否,YES-是）")
    private YesOrNoEnum isDefault;

    /**
     * 是否启用(0-否,1-是)
     */
    @TableField(value = "`enable`")
    @ApiModelProperty(value = "是否启用(NO-否,YES-是)")
    private YesOrNoEnum enable;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 旧字典类型
     */
    @NotBlank(message = "旧字典类型不能为空")
    @ApiModelProperty(value = "旧字典类型")
    private String oldDictType;


}