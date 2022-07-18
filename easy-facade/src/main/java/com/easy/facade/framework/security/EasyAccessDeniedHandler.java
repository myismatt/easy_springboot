package com.easy.facade.framework.security;

import com.easy.facade.beans.base.ResultBean;
import com.easy.facade.enums.HttpStatus;
import com.easy.utils.io.ResponseUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限不足处理
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/24 10:46
 */
@Component
public class EasyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseUtils.writeJson(response, ResultBean.custom(HttpStatus.UNAuthorIZED));
    }
}