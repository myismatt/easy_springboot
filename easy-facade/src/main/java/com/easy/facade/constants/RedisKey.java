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

    private RedisKey() {
        throw new IllegalAccessError("RedisKey.class");
    }
}
