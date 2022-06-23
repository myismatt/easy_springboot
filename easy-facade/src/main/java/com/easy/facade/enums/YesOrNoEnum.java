package com.easy.facade.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * 是否枚举
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/16 14:32
 */
public enum YesOrNoEnum implements IEnum<Integer> {
    /**
     * 是否
     */
    NO(0, "否"),
    YES(1, "是"),
    ;

    private final Integer value;

    private final String desc;

    YesOrNoEnum(Integer value, String desc) {
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
