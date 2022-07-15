package com.easy.facade.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * 行政级别枚举
 * </p>
 *
 * @Author Matt
 * @Date 2022/7/14 14:48
 */
public enum AreaTypeEnum implements IEnum<Integer> {
    /**
     * 0.国家; 1.省; 2.城市; 3.区县; 4.街道; 5.村;
     */
    nation(0, "国家"),
    province(1, "省"),
    city(2, "城市"),
    districts(3, "区县"),
    street(4, "街道"),
    village(5, "村/居委会"),
    ;

    private final Integer value;

    private final String desc;

    AreaTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}