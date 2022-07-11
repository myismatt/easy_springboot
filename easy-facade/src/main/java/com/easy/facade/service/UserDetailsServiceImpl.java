package com.easy.facade.service;

import com.easy.facade.beans.entity.LoginUserDetails;
import com.easy.facade.beans.vo.UserInfoVO;
import com.easy.facade.framework.exception.CustomException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * security用户实现
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/16 17:08
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfoVO userInfo = userService.getBaseMapper().loadUserByUsername(username);
        // 账号存在
        if (userInfo != null) {
            // 处理账号状态问题
            switch (userInfo.getAccountStatus()) {
                case NORMAL:
                    break;
                case INACTIVATED:
                    throw new CustomException("账号未激活");
                case STOP:
                    throw new CustomException("账号被停用");
                default:
                    throw new CustomException("账号信息异常");
            }
        } else {
            throw new CustomException("账号不存在");
        }
        // TODO 获取用户角色以及权限
        return loadLoginUser(userInfo);
    }

    private UserDetails loadLoginUser(UserInfoVO userInfo) {
        return new LoginUserDetails(userInfo.getId(), userInfo.getUserKey(), userInfo.getUsername(), userService.getUserMenu(userInfo.getId()), userInfo);
    }
}
