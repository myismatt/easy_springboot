package com.easy.facade.framework.security;

import com.easy.facade.beans.entity.LoginUserDetails;
import com.easy.facade.constants.EasyConstants;
import com.easy.facade.framework.exception.CustomException;
import com.easy.utils.lang.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletRequest;

/**
 * security工具
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/24 11:43
 */
public class SecurityUtils {

    private final static BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

    /**
     * 获取请求中的token
     *
     * @param request 请求
     * @return token
     */
    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader(EasyConstants.TOKEN_HEADER);
        if (StringUtils.isNotBlank(token) && token.startsWith(EasyConstants.TOKEN_PREFIX)) {
            return token.replace(EasyConstants.TOKEN_PREFIX, "");
        }
        return null;
    }

    /**
     * 获取用户信息
     *
     * @return LoginUserDetails
     */
    public static LoginUserDetails getLoginUserInfo() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof LoginUserDetails)) {
            throw new CustomException("登录信息获取异常");
        }
        return (LoginUserDetails) principal;
    }

    /**
     * 密码加密
     *
     * @param password 明文密码
     * @return 密文
     */
    public static String encode(String password) {
        return bcrypt.encode(password);
    }

    /**
     * 加密前后对比(一般用来比对前端提交过来的密码和数据库存储密码, 也就是明文和密文的对比)
     *
     * @param rawPassword     明文
     * @param encodedPassword 密文
     * @return true/false
     */
    public static boolean matches(String rawPassword, String encodedPassword) {
        return bcrypt.matches(rawPassword, encodedPassword);
    }
}
