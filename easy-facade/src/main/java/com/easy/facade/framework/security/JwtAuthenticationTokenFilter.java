package com.easy.facade.framework.security;

import com.easy.facade.beans.base.ResultBean;
import com.easy.facade.beans.entity.LoginUserDetails;
import com.easy.facade.constant.RedisKeyConsts;
import com.easy.facade.enums.HttpStatus;
import com.easy.facade.framework.config.AllowListProperties;
import com.easy.facade.framework.config.KeyConfig;
import com.easy.facade.framework.redis.RedisUtils;
import com.easy.utils.encryption.DesUtils;
import com.easy.utils.io.ResponseUtils;
import com.easy.utils.json.FastJsonUtils;
import com.easy.utils.jwt.JwtUtils;
import com.easy.utils.lang.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token过滤器 验证token有效性
 *
 * @Author Matt
 * @Date 2022年06月28日
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final RedisUtils redisUtils;
    private final AllowListProperties allowListProperties;

    public JwtAuthenticationTokenFilter(RedisUtils redisUtils, AllowListProperties allowListProperties) {
        this.redisUtils = redisUtils;
        this.allowListProperties = allowListProperties;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        // 获取请求地址
        String requestUrl = request.getRequestURI();
        // 放行接口不校验token
        for (String url : allowListProperties.getIgnoreUrl()) {
            // 模糊匹配单独处理
            if (url.endsWith("/**") || url.endsWith("/*")) {
                if (requestUrl.startsWith(url.replace("/**", ""))) {
                    chain.doFilter(request, response);
                    return;
                }
            } else {
                if (requestUrl.equals(url)) {
                    chain.doFilter(request, response);
                    return;
                }
            }
        }
        // 获取token
        String token = SecurityUtils.getToken(request);
        // 未登录
        if (StringUtils.isEmpty(token)) {
            chain.doFilter(request, response);
            return;
        }
        // 校验token合法性
        if (!JwtUtils.verify(token)) {
            ResponseUtils.writeJson(response, ResultBean.custom(HttpStatus.TOKEN_EXCEPTION));
            return;
        }
        // 验证token是否过期
        if (JwtUtils.isExpired(token)) {
            ResponseUtils.writeJson(response, ResultBean.custom(HttpStatus.TOKEN_EXPIRED));
            return;
        }
        // 获取账号唯一标识码
        String userKey = JwtUtils.getId(token);
        // 获取缓存中加密的用户数据
        String encryptUserString = redisUtils.getCacheObject(RedisKeyConsts.TOKEN_USERINFO_KEY + userKey);
        // 未获取到用户加密信息则表示登录过期或者被强制下线
        if (StringUtils.isBlank(encryptUserString)) {
            ResponseUtils.writeJson(response, ResultBean.custom(HttpStatus.TOKEN_EXPIRED));
            return;
        }
        // 解密并反序列化缓存信息
        LoginUserDetails loginUser = FastJsonUtils.jsonToObject(DesUtils.decryptByDes(encryptUserString, KeyConfig.getDesKey()), LoginUserDetails.class);
        //
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } else {
            ResponseUtils.writeJson(response, ResultBean.custom(HttpStatus.TOKEN_EXCEPTION));
            return;
        }
        chain.doFilter(request, response);
    }
}