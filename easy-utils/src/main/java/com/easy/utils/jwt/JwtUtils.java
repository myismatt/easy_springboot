package com.easy.utils.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;
import java.util.Date;

/**
 * JWT工具类
 * </p>
 *
 * @Author Matt
 * @Date 2021/7/26 15:51
 */
@Configuration
public class JwtUtils {
    /**
     * token生效时间(默认是从当前开始生效) 默认：new Date(System.currentTimeMillis() + START_TIME)
     */
    private static final Long START_TIME = 0L;
    /**
     * token在什么时间之前是不可用的（默认从当前时间） 默认：new Date(System.currentTimeMillis() + BEFORE_TIME)
     */
    private static final Long BEFORE_TIME = 0L;

    /**
     * 密钥
     */
    private static String secret;

    public static String getSecret() {
        return secret;
    }

    @Value("${jwt.secret:Hj5RzB7YeMhmEf1v}")
    public void setSecret(String secret) {
        JwtUtils.secret = secret;
    }

    /**
     * 获取加密
     *
     * @return 加密密钥
     */
    private static Algorithm getAlgorithm() {
        return Algorithm.HMAC256(JwtUtils.secret);
    }

    /**
     * 创建token
     *
     * @param sub     token所面向的用户 主题
     * @param aud     接收token的一方 用户
     * @param jti     token的唯一身份标识，主要用来作为一次性token,从而回避重放攻击
     * @param iss     token签发者 发行人
     * @param expType 过期类型,参考java.util.Calendar类中的时间 到期时间
     * @param amount  过期时间
     * @return 加密后的token字符串
     */
    public static String createToken(String keyId, String iss, String sub, String aud, String jti, int expType, int amount) {
        return generateToken(keyId, iss, sub, aud, jti, expType, amount, getAlgorithm());
    }

    /**
     * 创建token
     *
     * @param sub       token所面向的用户
     * @param aud       接收token的一方
     * @param jti       token的唯一身份标识，主要用来作为一次性token,从而回避重放攻击
     * @param iss       token签发者
     * @param expType   过期类型,参考java.util.Calendar类中的时间
     * @param amount    过期时间
     * @param algorithm 加密信息
     * @return 加密后的token字符串
     */
    private static String generateToken(String keyId, String iss, String sub, String aud, String jti, int expType, int amount, Algorithm algorithm) {
        Calendar instance = Calendar.getInstance();
        instance.add(expType, amount);
        return JWT.create()
                .withNotBefore(new Date(System.currentTimeMillis() + START_TIME))
                .withIssuedAt(new Date(System.currentTimeMillis() + BEFORE_TIME))
                .withKeyId(keyId)
                .withIssuer(iss)
                .withSubject(sub)
                .withAudience(aud)
                .withJWTId(jti)
                .withExpiresAt(instance.getTime())
                .sign(algorithm);
    }

    /**
     * 验证token，通过返回true
     *
     * @param token 需要校验的串
     **/
    public static boolean verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(getAlgorithm()).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    /**
     * 验证token，通过返回true
     *
     * @param token 需要校验的串
     **/
    public static boolean verify(String token, Algorithm algorithm) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
