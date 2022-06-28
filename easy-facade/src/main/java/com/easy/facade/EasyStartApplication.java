package com.easy.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class EasyStartApplication {

    private static final Logger logger = LoggerFactory.getLogger(EasyStartApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(EasyStartApplication.class, args);
        logger.info("\n" +
                "███████╗ █████╗ ███████╗██╗   ██╗\n" +
                "██╔════╝██╔══██╗██╔════╝╚██╗ ██╔╝\n" +
                "█████╗  ███████║███████╗ ╚████╔╝ \n" +
                "██╔══╝  ██╔══██║╚════██║  ╚██╔╝  \n" +
                "███████╗██║  ██║███████║   ██║   \n" +
                "╚══════╝╚═╝  ╚═╝╚══════╝   ╚═╝   \n" +
                "                                 \n");
    }
}

