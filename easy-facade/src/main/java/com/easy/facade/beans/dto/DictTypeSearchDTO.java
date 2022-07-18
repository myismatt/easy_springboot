package com.easy.facade.beans.dto;

import com.easy.facade.beans.base.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典数据分页查询入参
 * <p>
 * 2022/3/9 9:46
 *
 * @Author Matt
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("字典数据分页查询入参")
public class DictTypeSearchDTO extends BaseDTO {

    @ApiModelProperty("字典名字")
    private String dictName;

    @ApiModelProperty(value = "字典类型")
    private String dictType;
}