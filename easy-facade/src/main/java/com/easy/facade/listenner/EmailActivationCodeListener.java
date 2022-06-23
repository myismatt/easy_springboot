package com.easy.facade.listenner;

import com.easy.facade.beans.entity.EmailActivationCodeMessage;
import com.easy.facade.framework.redis.RedisUtils;
import com.easy.facade.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * 邮件激活码redis监听
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/21 14:16
 */
@Component
public class EmailActivationCodeListener implements MessageListener {

    private RedisUtils redisUtils;
    private MailService mailService;

    @Autowired
    public void setRedisUtils(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        EmailActivationCodeMessage emailActivationCodeMessage = (EmailActivationCodeMessage) redisUtils.getListenerMessage(message.getBody());
        mailService.sendActivationCode(emailActivationCodeMessage);
    }
}
