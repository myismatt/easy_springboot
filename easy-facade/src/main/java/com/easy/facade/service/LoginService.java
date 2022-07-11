package com.easy.facade.service;

import com.easy.facade.beans.dto.LoginParamDTO;
import com.easy.facade.beans.entity.LoginUserDetails;
import com.easy.facade.beans.entity.TokenInfo;
import com.easy.facade.beans.vo.UserInfoVO;
import com.easy.facade.constants.RedisKey;
import com.easy.facade.framework.config.KeyConfig;
import com.easy.facade.framework.redis.RedisUtils;
import com.easy.facade.framework.security.SecurityUtils;
import com.easy.utils.encryption.DesUtils;
import com.easy.utils.idUtils.IdUtils;
import com.easy.utils.idUtils.SnowflakeIdUtils;
import com.easy.utils.json.FastJsonUtils;
import com.easy.utils.jwt.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

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

    public TokenInfo login(LoginParamDTO dto) throws Exception {
        // 验证用户
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dto.getUsername(), dto.getPassword()));
        // 获取用户信息
        LoginUserDetails userDetails = (LoginUserDetails) authentication.getPrincipal();
        // 清除密码
        userDetails.getUserInfo().setPassword(null);
        // 过期时间+动态数/单位分钟
        int amount = 480 + IdUtils.randomFive();
        // 随机码
        String uid = SnowflakeIdUtils.getInstance().getNextId();
        // 加密用户信息
        String userJson = DesUtils.encryptByDes(FastJsonUtils.objectToJson(userDetails), KeyConfig.getDesKey());
        // 缓存用户信息
        redisUtils.setCacheObject(RedisKey.TOKEN_USERINFO_KEY + uid, userJson, amount, TimeUnit.MINUTES);
        // 生成 accessToken
        String accessToken = JwtUtils.generateToken(userDetails.getId(), userDetails.getUsername(), userDetails.getUserKey(), uid, amount);
        return new TokenInfo(accessToken, uid);

    }

    public UserInfoVO getUserInfo() {
        return SecurityUtils.getLoginUserInfo().getUserInfo();
    }
}
