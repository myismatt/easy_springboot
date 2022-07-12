package com.easy.facade.constant;

/**
 * Redis订阅topic
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/21 14:43
 */
public final class RedisListenerTopicConsts {

    /**
     * 邮件激活码topic
     */
    public static final String EMAIL_ACTIVATION_CODE_TOPIC = "emailActivationCode";

    private RedisListenerTopicConsts() {
        throw new IllegalAccessError("RedisListenerTopic.class");
    }
}