package com.easy.facade.framework.oauth.exception;

import com.easy.facade.beans.base.ResultBean;
import com.easy.facade.enums.HttpStatus;
import com.easy.utils.json.FastJsonUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限异常处理
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/17 11:16
 */
@Component
public class EasyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpStatus.FORBIDDEN.getValue());
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.getWriter().println(FastJsonUtils.objectToJson(ResultBean.custom(HttpStatus.FORBIDDEN)));
        response.getWriter().flush();
    }
}
