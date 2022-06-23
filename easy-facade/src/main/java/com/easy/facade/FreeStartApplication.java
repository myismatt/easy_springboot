package com.easy.facade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动类
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/13 15:57
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.easy.*"})
@EnableScheduling
public class FreeStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreeStartApplication.class, args);
    }
}

