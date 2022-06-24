package com.easy.facade.service;

import com.easy.facade.beans.dto.LoginParamDTO;
import com.easy.facade.beans.entity.LoginUserDetails;
import com.easy.facade.beans.entity.TokenInfo;
import com.easy.facade.framework.redis.RedisUtils;
import com.easy.utils.idUtils.IdUtils;
import com.easy.utils.jwt.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * 登录接口
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/24 16:50
 */
@Service
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final RedisUtils redisUtils;


    public LoginService(AuthenticationManager authenticationManager, RedisUtils redisUtils) {
        this.authenticationManager = authenticationManager;
        this.redisUtils = redisUtils;
    }

    public TokenInfo login(LoginParamDTO dto) {
        // 验证用户
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dto.getUsername(), dto.getPassword()));
        // 获取用户信息
        LoginUserDetails userDetails = (LoginUserDetails) authentication.getPrincipal();
        // 清除密码
        userDetails.setPassword(null);
        String redisUidKey = IdUtils.generate12Code();
        // 生成 accessToken
        String accessToken = JwtUtils.generateToken(userDetails.getId(), userDetails.getUsername(), userDetails.getUserKey(), redisUidKey, 8);
        return new TokenInfo(accessToken, IdUtils.fastSimpleUuid());

    }
}
