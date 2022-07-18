package com.easy.facade.beans.vo;

import com.easy.facade.enums.YesOrNoEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 字典类型缓存数据
 * <p>
 * 2022/3/9 9:55
 *
 * @Author Matt
 */
@Data
@ApiModel("字典类型缓存数据")
public class DictTypeVO implements Serializable {


    private static final long serialVersionUID = 8399490605336010213L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private String id;

    /**
     * 字典名称
     */
    @ApiModelProperty(value = "字典名称")
    private String dictName;

    /**
     * 字典类型
     */
    @ApiModelProperty(value = "字典类型")
    private String dictType;

    /**
     * 是否启用
     */
    @ApiModelProperty(value = "是否启用（0-否,1-是）", allowEmptyValue = true)
    private YesOrNoEnum enable;


    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", allowEmptyValue = true)
    private String remark;

    /**
     * 字典数据集
     */
    @ApiModelProperty(value = "字典数据集", allowEmptyValue = true)
    private List<DictDataVO> dictDataList;

}