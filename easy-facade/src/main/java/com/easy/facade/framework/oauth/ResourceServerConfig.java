package com.easy.facade.framework.oauth;

import com.easy.facade.config.WhitelistProperties;
import com.easy.facade.framework.oauth.exception.EasyAccessDeniedHandler;
import com.easy.facade.framework.oauth.exception.EasyOauth2AuthenticationEntryPoint;
import com.easy.facade.framework.security.EasyAuthenticationEntryPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 资源服务
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/20 11:22
 */
@EnableResourceServer
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    public static final String RESOURCE_NAME = "my-resource";

    private final Logger logger = LoggerFactory.getLogger(ResourceServerConfig.class);
    private final EasyAccessDeniedHandler accessDeniedHandler;
    private final EasyOauth2AuthenticationEntryPoint oauth2AuthenticationEntryPoint;
    private final EasyAuthenticationEntryPoint authenticationEntryPoint;
    /**
     * 授权白名单
     */
    private final WhitelistProperties whitelistProperties;

    public ResourceServerConfig(EasyAccessDeniedHandler accessDeniedHandler, EasyOauth2AuthenticationEntryPoint oauth2AuthenticationEntryPoint, EasyAuthenticationEntryPoint authenticationEntryPoint, WhitelistProperties whitelistProperties) {
        this.accessDeniedHandler = accessDeniedHandler;
        this.oauth2AuthenticationEntryPoint = oauth2AuthenticationEntryPoint;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.whitelistProperties = whitelistProperties;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                //只有基于token的认证才可以访问这些资源
                .resourceId(RESOURCE_NAME).stateless(true)
                // 认证异常处理
                .authenticationEntryPoint(authenticationEntryPoint)
                // 权限不足异常类重写
                .accessDeniedHandler(accessDeniedHandler)
                // token异常类重写
                .authenticationEntryPoint(oauth2AuthenticationEntryPoint);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 允许用户使用HTTP基本身份验证进行身份验证
        http.httpBasic();
        // 禁用 csrf
        http.csrf().disable();
        // 关闭session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 打印白名单日志
        logger.info("URL WHITELIST:{}", whitelistProperties.getIgnoreUrl().toString());
        // 白名单
        for (String ignoreUrl : whitelistProperties.getIgnoreUrl()) {
            http.authorizeRequests().antMatchers(ignoreUrl).permitAll();
        }
        // 权限配置
        http.authorizeRequests()
                // 跨域预检请求
                .antMatchers(HttpMethod.OPTIONS, "/**").denyAll()
                // 其他所有请求需要身份认证
                .anyRequest().authenticated().and().headers().frameOptions().disable();
        // 禁用缓存
        http.headers().cacheControl();
    }
}
