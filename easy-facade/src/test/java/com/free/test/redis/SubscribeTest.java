package com.free.test.redis;

import com.easy.facade.EasyStartApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * redis发布订阅测试
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/21 14:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EasyStartApplication.class})
public class SubscribeTest {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    @Test
    public void testSubscribe() {
        String achannel = "emailActivationCode";

        redisTemplate.convertAndSend(achannel, "hello world");
    }

}
