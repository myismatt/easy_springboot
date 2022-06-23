package com.easy.facade.constants;

/**
 * Redis订阅topic
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/21 14:43
 */
public final class RedisListenerTopic {

    /**
     * 邮件激活码topic
     */
    public static final String EMAIL_ACTIVATION_CODE_TOPIC = "emailActivationCode";

    private RedisListenerTopic() {
        throw new IllegalAccessError("RedisListenerTopic.class");
    }
}
