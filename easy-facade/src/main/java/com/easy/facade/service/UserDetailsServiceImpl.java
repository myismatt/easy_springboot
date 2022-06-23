package com.easy.facade.service;

import com.easy.facade.beans.entity.LoginUserDetails;
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
        LoginUserDetails userDetails = userService.getBaseMapper().loadUserByUsername(username);
        if (userDetails != null) {

        }
        return userDetails;
    }
}
