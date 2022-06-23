package com.easy.facade.framework;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.TimeZone;

/**
 * Web处理
 * </p>
 *
 * @Author Matt
 * @Date 2021/5/20 14:01
 */
@Configuration
public class ApplicationConfig {

    /**
     * 时区配置
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization() {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.timeZone(TimeZone.getDefault());
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        // 允许跨域的域名，可以用*表示允许任何域名使用
                        .allowedOrigins("*")
                        // 允许任何方法（post、get等）
                        .allowedMethods("*")
                        // 允许任何请求头
                        .allowedHeaders("*")
                        // 带上cookie信息
                        .allowCredentials(true).exposedHeaders(HttpHeaders.SET_COOKIE)
                        // maxAge(3600)表明在3600秒内，不需要再发送预检验请求，可以缓存该结果
                        .maxAge(3600L);
            }
        };
    }
}
