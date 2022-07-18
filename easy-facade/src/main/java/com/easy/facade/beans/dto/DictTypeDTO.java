package com.easy.facade.beans.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.easy.facade.enums.YesOrNoEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 字典类型入参
 * <p>
 * 2022/3/9 9:43
 *
 * @Author Matt
 */
@Data
@ApiModel("字典类型入参")
public class DictTypeDTO {

    /**
     * 字典名称
     */
    @NotBlank(message = "字典名称不能为空")
    @ApiModelProperty(value = "字典名称")
    private String dictName;

    /**
     * 字典类型
     */
    @NotBlank(message = "请选择字典类型")
    @ApiModelProperty(value = "字典类型")
    private String dictType;


    /**
     * 是否启用(0-否,1-是)
     */
    @TableField(value = "`enable`")
    @ApiModelProperty(value = "是否启用(0-否,1-是)")
    private YesOrNoEnum enable;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}