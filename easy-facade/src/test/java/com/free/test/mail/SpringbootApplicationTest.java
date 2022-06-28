package com.free.test.mail;

import com.easy.facade.EasyStartApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * 邮箱测试
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/21 10:04
 */
@SpringBootTest(classes = {EasyStartApplication.class})
public class SpringbootApplicationTest {

    @Autowired
    private JavaMailSender javaMailSender;

    @Test
    public void contextLoads() throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("812262134@qq.com");
        message.setTo("jiakun0113@gmail.com"); // 接收地址
        message.setSubject("一封简单的邮件"); // 标题
        message.setText("使用Spring Boot发送简单邮件。"); // 内容
        javaMailSender.send(message);
    }
}
