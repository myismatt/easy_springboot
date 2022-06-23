package com.easy.facade.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * 账号状态枚举
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/13 18:13
 */
public enum AccountStatusEnum implements IEnum<Integer> {
    /**
     * 账号状态
     */
    INACTIVATED(0, "未激活"),
    NORMAL(1, "正常"),
    STOP(9, "停用"),
    ;
    private final Integer value;

    private final String desc;

    AccountStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
