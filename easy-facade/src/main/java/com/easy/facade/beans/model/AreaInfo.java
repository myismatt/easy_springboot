package com.easy.facade.beans.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.easy.facade.beans.base.BaseEntity;
import com.easy.facade.enums.AreaTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 行政区划
 * </p>
 *
 * @Author Matt
 * @Date 2022/7/14 14:46
 */
@ApiModel(value = "行政区划")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "easy_area_info")
public class AreaInfo extends BaseEntity {
    /**
     * 父级名称
     */
    @TableField(value = "parent_name")
    @ApiModelProperty(value = "父级名称")
    private String parentName;

    /**
     * 父级编码
     */
    @TableField(value = "parent_code")
    @ApiModelProperty(value = "父级编码")
    private String parentCode;

    /**
     * 父级编码集合
     */
    @TableField(value = "parent_collection")
    @ApiModelProperty(value = "父级编码集合")
    private String parentCollection;

    /**
     * 名称
     */
    @TableField(value = "area_name")
    @ApiModelProperty(value = "名称")
    private String areaName;

    /**
     * 编码
     */
    @TableField(value = "area_code")
    @ApiModelProperty(value = "编码")
    private String areaCode;

    /**
     * 简称
     */
    @TableField(value = "short_name")
    @ApiModelProperty(value = "简称")
    private String shortName;

    /**
     * 0.省 1.市 2. 区县 3.街道 4.居委会
     */
    @TableField(value = "area_type")
    @ApiModelProperty(value = "0.省 1.市 2. 区县 3.街道 4.居委会 ")
    private AreaTypeEnum areaType;

    /**
     * 城乡分类代码
     */
    @TableField(value = "type_code")
    @ApiModelProperty(value = "城乡分类代码")
    private Integer typeCode;

    /**
     * 行政区划数据年份
     */
    @TableField(value = "data_year")
    @ApiModelProperty(value = "行政区划数据年份")
    private Integer dataYear;
}