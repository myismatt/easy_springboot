package com.easy.facade.beans.base;

import com.easy.utils.lang.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * DTO父类
 *
 * @Author Matt
 * @Date 2022年06月28日15
 */
@ApiModel("分页参数")
@ToString
@Setter
public class BaseDTO implements Serializable {

    /**
     * 页码
     */
    @ApiModelProperty("页码")
    private Long current;

    /**
     * 每页数量
     */
    @ApiModelProperty("每页数量")
    private Long size;

    public Long getCurrent() {
        return StringUtils.isNull(current) ? 1 : current;
    }

    public Long getSize() {
        return StringUtils.isNull(size) ? 10 : size;
    }
}
