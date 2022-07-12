package com.easy.facade.constant;

/**
 * 系统常量
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/24 11:46
 */
public final class SystemConsts {
    /**
     * 超级管理员key
     */
    public final static String ADMIN_KEY = "admin";
    /**
     * token 请求头
     */
    public final static String TOKEN_HEADER = "Authorization";

    /**
     * token 前缀
     */
    public final static String TOKEN_PREFIX = "bearer ";

    private SystemConsts() {
        throw new IllegalAccessError("Constants.class");
    }
}