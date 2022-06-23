package com.easy.facade.framework.oauth;

import com.easy.facade.framework.oauth.exception.EasyExtendOauth2ResponseExceptionTranslator;
import com.easy.facade.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;

/**
 * 授权服务
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/20 10:34
 */
@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private static final String JWT_SINGING_KEY = "my-sign-key-xxxxxx";

    private final DataSource dataSource;

    private final AuthenticationManager authenticationManager;


    private final UserDetailsServiceImpl userDetailsService;

    private final EasyExtendOauth2ResponseExceptionTranslator exceptionTranslator;

    public AuthorizationServerConfig(DataSource dataSource, AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService, EasyExtendOauth2ResponseExceptionTranslator exceptionTranslator) {
        this.dataSource = dataSource;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.exceptionTranslator = exceptionTranslator;
    }


    /**
     * 设置第三方client相关的数据信息
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService());
    }

    /**
     * 设定一些与认证相关的配置
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                // 配置token存储信息
                .tokenStore(tokenStore())
                // 配置jwt
                .accessTokenConverter(jwtAccessTokenConverter())
                // 配置认证
                .authenticationManager(authenticationManager)
                // 配置用户信息
                .userDetailsService(userDetailsService)
                // 自定义异常解析
                .exceptionTranslator(exceptionTranslator)
                // 支持OAUTH相关接口以GET和POST方法请求
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }

    /**
     * 一些服务器配置
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.allowFormAuthenticationForClients() //支持请求数据通过表单的形式发送
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()");
    }

    /**
     * 数据库存储token信息
     * 主要表：oauth_access_token、oauth_refresh_token
     */
    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    /**
     * 数据库读取client的相关数据
     * 主要表：oauth_client_details
     * <p>
     * 注意：这里需要重命名bean name，否则会提示 clientDetailsService 无法被override
     */
    @Bean(name = "oauthClientDetailsService")
    public JdbcClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    /**
     * 生成JWT
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(JWT_SINGING_KEY);
        return converter;
    }
}
