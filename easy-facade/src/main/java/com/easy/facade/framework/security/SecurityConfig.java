package com.easy.facade.framework.security;

import com.easy.facade.framework.annotation.IgnoreAuth;
import com.easy.facade.framework.config.AllowListProperties;
import com.easy.utils.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;

/**
 * security配置
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/16 16:43
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
    /**
     * 授权白名单
     */
    private final AllowListProperties allowListProperties;
    private final EasyAuthenticationEntryPoint authenticationEntryPoint;
    private final EasyAccessDeniedHandler accessDeniedHandler;
    private final JwtAuthenticationTokenFilter authenticationTokenFilter;

    private final RequestMappingHandlerMapping requestMappingHandlerMapping;

    public SecurityConfig(AllowListProperties allowListProperties, EasyAuthenticationEntryPoint authenticationEntryPoint, EasyAccessDeniedHandler accessDeniedHandler, JwtAuthenticationTokenFilter authenticationTokenFilter, RequestMappingHandlerMapping requestMappingHandlerMapping) {
        this.allowListProperties = allowListProperties;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
        this.authenticationTokenFilter = authenticationTokenFilter;
        this.requestMappingHandlerMapping = requestMappingHandlerMapping;
    }


    /**
     * 认证管理器
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 允许用户使用HTTP基本身份验证进行身份验证
        http.httpBasic()
                // 禁用 csrf
                .and().csrf().disable()
                // 关闭session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 白名单
        for (String ignoreUrl : allowListProperties.getIgnoreUrl()) {
            http.authorizeRequests().antMatchers(ignoreUrl).permitAll();
        }
        // 打印白名单日志
        logger.info("URL WHITELIST:{}", allowListProperties.getIgnoreUrl().toString());
        // 权限配置
        http.authorizeRequests()
                // 跨域预检请求
                .antMatchers(HttpMethod.OPTIONS, "/**").denyAll()
                // 其他所有请求需要身份认证
                .anyRequest().authenticated();

        // 权限不足处理
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                // 认证异常处理
                .authenticationEntryPoint(authenticationEntryPoint);
        // 添加JWT filter
        http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        // 禁用缓存
        http.headers().frameOptions().disable().cacheControl();
    }

    /**
     * 配置放行
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 忽略URL
        WebSecurity and = web.ignoring().and();
        and.ignoring().antMatchers("/**/*.js", "/lang/*.json", "/**/*.css", "/**/*.js", "/**/*.map", "/**/*.html", "/**/*.png",
                "/**/*.ico", "/**/*.jpg", "/favicon.ico", "/doc.html", "/webjars/**", "/druid/*", "/swagger**/**", "/v2/**", "/v3/**");
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        handlerMethods.forEach((info, method) -> {
            // 带IgnoreAuth注解的方法直接放行
            if (StringUtils.isNotNull(method.getMethodAnnotation(IgnoreAuth.class))) {
                // 根据请求类型做不同的处理
                info.getMethodsCondition().getMethods().forEach(requestMethod -> {
                    switch (requestMethod) {
                        case GET:
                            // getPatternsCondition得到请求url数组，遍历处理
                            info.getPatternsCondition().getPatterns().forEach(pattern -> {
                                and.ignoring().antMatchers(HttpMethod.GET, pattern);
                            });
                            break;
                        case POST:
                            info.getPatternsCondition().getPatterns().forEach(pattern -> {
                                and.ignoring().antMatchers(HttpMethod.POST, pattern);
                            });
                            break;
                        case DELETE:
                            info.getPatternsCondition().getPatterns().forEach(pattern -> {
                                and.ignoring().antMatchers(HttpMethod.DELETE, pattern);
                            });
                            break;
                        case PUT:
                            info.getPatternsCondition().getPatterns().forEach(pattern -> {
                                and.ignoring().antMatchers(HttpMethod.PUT, pattern);
                            });
                            break;
                        default:
                            break;
                    }
                });
            }
        });
    }


    /**
     * 密码配置
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 跨域配置
     *
     * @return 基于URL的跨域配置信息
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 注册跨域配置
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}