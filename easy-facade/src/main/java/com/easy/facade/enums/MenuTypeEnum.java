package com.easy.facade.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * 路由菜单类型
 * </p>
 *
 * @Author Matt
 * @Date 2022/7/7 14:28
 */
public enum MenuTypeEnum implements IEnum<Integer> {

    /**
     * 路由菜单类型
     */
    MENU(0, "目录"),
    PAGE(1, "页面"),
    BUTTON(2, "按钮");

    private final Integer value;
    private final String desc;

    MenuTypeEnum(Integer value, String desc) {
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