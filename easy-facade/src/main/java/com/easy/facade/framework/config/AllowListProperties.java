package com.easy.facade.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 白名单
 *
 * @Author Matt
 * @Date 2021/5/24 15:01
 */
@Configuration
@ConfigurationProperties(prefix = "allow-list")
public class AllowListProperties {
    /**
     * 白名单url集合
     */
    private List<String> ignoreUrl = new ArrayList<>();

    public List<String> getIgnoreUrl() {
        return ignoreUrl;
    }

    public void setIgnoreUrl(List<String> ignoreUrl) {
        this.ignoreUrl = ignoreUrl;
    }
}