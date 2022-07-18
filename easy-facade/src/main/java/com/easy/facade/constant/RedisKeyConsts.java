package com.easy.facade.constant;

/**
 * Redis的Key
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/22 14:11
 */
public final class RedisKeyConsts {
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
    public static final String CONFIG_LIST_KEY = "system_config_list_key:";

    /**
     * 字典缓存键
     */
    public static final String DICT_KEY = "system_dict:";

    private RedisKeyConsts() {
        throw new IllegalAccessError("RedisKey.class");
    }
}