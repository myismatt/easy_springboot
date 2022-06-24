package com.easy.facade.framework.security;

import com.easy.facade.beans.entity.LoginUserDetails;
import com.easy.facade.framework.exception.CustomException;
import com.easy.facade.service.UserDetailsServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 自定义身份验证
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/17 13:48
 */
@Component
public class EasyAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsServiceImpl userDetailsService;

    public EasyAuthenticationProvider(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取用户输入的用户名和密码
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        if (StringUtils.isAnyBlank(username, password)) {
            throw new CustomException("用户名或者密码不能为空");
        }
        // userDetails为数据库中查询到的用户信息
        LoginUserDetails userDetails = (LoginUserDetails) userDetailsService.loadUserByUsername(username);
        // 账号存在
        if (userDetails != null) {
            // 处理账号状态问题
            switch (userDetails.getAccountStatus()) {
                case NORMAL:
                    break;
                case INACTIVATED:
                    throw new CustomException("账号未激活");
                case STOP:
                    throw new CustomException("账号被停用");
                default:
                    throw new CustomException("账号信息异常");
            }
            // 手动校验密码
            if (StringUtils.isNotBlank(password) && !new BCryptPasswordEncoder().matches(password, userDetails.getPassword())) {
                throw new CustomException("密码错误");
            }
        }
        else {
            throw new CustomException("账号不存在");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
