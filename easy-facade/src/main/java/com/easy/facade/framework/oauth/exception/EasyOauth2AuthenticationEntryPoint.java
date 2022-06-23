package com.easy.facade.framework.oauth.exception;

import com.easy.facade.beans.base.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token异常处理
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/20 14:28
 */
@Component
public class EasyOauth2AuthenticationEntryPoint extends OAuth2AuthenticationEntryPoint {

    private static final Logger log = LoggerFactory.getLogger(EasyOauth2AuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {


        Throwable cause = authException.getCause();
        ResultBean<String> resultBean;
        if (cause instanceof OAuth2AccessDeniedException) {
            resultBean = ResultBean.error("资源ID不在resource_ids范围内");
        }
        else if (cause instanceof InvalidTokenException) {
            resultBean = ResultBean.error("Token解析失败");
        }
        else if (authException instanceof InsufficientAuthenticationException) {
            resultBean = ResultBean.error("未携带token");
        }
        else {
            resultBean = ResultBean.error("未知异常信息");
        }

        response.setStatus(resultBean.getCode());
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
    }
}
