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

    /**
     * token中用户信息map的key
     */
    public final static String TOKEN_USERINFO_MAP_KEY = "user";

    private EasyConstants() {
        throw new IllegalAccessError("Constants.class");
    }
}
