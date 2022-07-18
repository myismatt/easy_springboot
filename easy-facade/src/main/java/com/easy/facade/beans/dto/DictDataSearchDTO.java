package com.easy.facade.beans.dto;

import com.easy.facade.beans.base.BaseDTO;
import com.easy.facade.enums.YesOrNoEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典数据分页查询入参
 * <p>
 * 2022/3/9 9:45
 *
 * @Author Matt
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("字典数据分页查询入参")
public class DictDataSearchDTO extends BaseDTO {

    @ApiModelProperty("字典类型ID")
    private String dictTypeId;

    @ApiModelProperty("字典类型")
    private String dictType;

    @ApiModelProperty("字典标签")
    private String dictLabel;

    @ApiModelProperty("是否启用(NO-否,YES-是)")
    private YesOrNoEnum enable;


}