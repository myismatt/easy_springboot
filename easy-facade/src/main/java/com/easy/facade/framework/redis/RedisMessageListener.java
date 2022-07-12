package com.easy.facade.framework.redis;

import com.easy.facade.constants.RedisListenerTopicConsts;
import com.easy.facade.listenner.EmailActivationCodeListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * redis消息队列
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/22 15:52
 */
@Configuration
public class RedisMessageListener {

    private final EmailActivationCodeListener emailActivationCodeListener;

    public RedisMessageListener(EmailActivationCodeListener emailActivationCodeListener) {
        this.emailActivationCodeListener = emailActivationCodeListener;
    }

    /**
     * redis订阅发布
     */
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        //将消息侦听器添加到（可能正在运行的）容器中。 如果容器正在运行，则侦听器会尽快开始接收（匹配）消息。
        // 邮件激活码服务 订阅了 emailActivationCode一个频道
        container.addMessageListener(emailActivationCodeListener, new PatternTopic(RedisListenerTopicConsts.EMAIL_ACTIVATION_CODE_TOPIC));
        return container;
    }
}
