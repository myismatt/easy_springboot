package com.easy.facade.constants;

/**
 * 常量
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/24 11:46
 */
public final class EasyConstants {

    /**
     * token 请求头
     */
    public final static String TOKEN_HEADER = "Authorization";

    /**
     * token 前缀
     */
    public final static String TOKEN_PREFIX = "bearer ";

    private EasyConstants() {
        throw new IllegalAccessError("Constants.class");
    }
}
