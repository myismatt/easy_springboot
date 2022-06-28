package com.easy.facade.constants;

/**
 * Redis的Key
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/22 14:11
 */
public final class RedisKey {
    /**
     * 邮件激活码key
     */
    public final static String EMAIL_ACTIVATION_CODE = "email_activation_code:";

    /**
     * token中用户信息的key
     */
    public final static String TOKEN_USERINFO_KEY = "user_key:";

    /**
     * 配置参数缓存键
     */
    public static final String CONFIG_LIST_KEY = "config_list_key:";

    private RedisKey() {
        throw new IllegalAccessError("RedisKey.class");
    }
}
