package com.easy.facade.framework.security;

import com.easy.facade.beans.base.ResultBean;
import com.easy.facade.enums.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证异常处理
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/20 15:55
 */
@Component
public class EasyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // 设置编码格式
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(ResultBean.custom(HttpStatus.FORBIDDEN));
        response.getWriter().flush();
    }
}
