package com.easy.utils.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.easy.utils.idUtils.IdUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
     * 创建token,默认过期时间单位为小时
     *
     * @param iss    token签发者
     * @param sub    token所面向的用户
     * @param aud    接收token的一方
     * @param amount 过期时间
     * @return 加密后的token字符串
     */
    public static String generateToken(String iss, String sub, String aud, String jti, int amount) {
        return generateToken(iss, sub, aud, jti, null, Calendar.HOUR, amount);
    }

    /**
     * 创建token,默认过期时间单位为小时
     *
     * @param iss    token签发者
     * @param sub    token所面向的用户
     * @param aud    接收token的一方
     * @param amount 过期时间
     * @return 加密后的token字符串
     */
    public static String generateToken(String iss, String sub, String aud, Map<String, Object> map, int amount) {
        return generateToken(iss, sub, aud, IdUtils.fastSimpleUuid(), map, Calendar.HOUR, amount);
    }


    /**
     * 创建token
     *
     * @param iss     token签发者
     * @param sub     token所面向的用户
     * @param aud     接收token的一方
     * @param expType 过期类型,参考java.util.Calendar类中的时间
     * @param amount  过期时间
     * @return 加密后的token字符串
     */
    public static String generateToken(String iss, String sub, String aud, Map<String, Object> map, int expType, int amount) {
        return generateToken(iss, sub, aud, IdUtils.fastSimpleUuid(), map, expType, amount);
    }

    /**
     * 创建token
     *
     * @param iss     token签发者
     * @param sub     token所面向的用户
     * @param aud     接收token的一方
     * @param jti     token的唯一身份标识，主要用来作为一次性token,从而回避重放攻击
     * @param expType 过期类型,参考java.util.Calendar类中的时间
     * @param amount  过期时间
     * @return 加密后的token字符串
     */
    public static String generateToken(String iss, String sub, String aud, String jti, Map<String, Object> map, int expType, int amount) {
        return generateToken(iss, sub, aud, jti, map, expType, amount, getAlgorithm());
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
     * @param algorithm 加密密钥
     * @return 加密后的token字符串
     */
    private static String generateToken(String iss, String sub, String aud, String jti, Map<String, Object> map, int expType, int amount, Algorithm algorithm) {
        // 计算过期时间
        Calendar instance = Calendar.getInstance();
        instance.add(expType, amount);
        // 获取当前时间
        long nowDate = System.currentTimeMillis();

        // 构建token
        final JWTCreator.Builder builder = JWT.create();
        // 增加载荷
        if (map != null && !map.isEmpty()) {
            builder.withPayload(map);
        }
        return builder
                .withIssuer(iss)
                .withSubject(sub)
                .withAudience(aud)
                .withJWTId(jti)
                .withNotBefore(new Date(nowDate + START_TIME))
                .withIssuedAt(new Date(nowDate + BEFORE_TIME))
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
     * 验证toke是否过期
     *
     * @param token 需要校验的串
     * @return true-过期; false-未过期
     */
    public static boolean isExpired(String token) {
        try {
            final Date expiration = getExpiresAt(token);
            return expiration.before(new Date());
        }
        catch (Exception e) {
            return true;
        }
    }

    /**
     * 获取jwt中的过期时间
     *
     * @param token jwt
     * @return keyId
     */
    public static Date getExpiresAt(String token) {
        return JWT.decode(token).getExpiresAt();
    }


    /**
     * 获取jwt中的keyId
     *
     * @param token jwt
     * @return keyId
     */
    public static String getKeyId(String token) {
        return JWT.decode(token).getKeyId();
    }

    /**
     * 获取jwt中的Id
     *
     * @param token jwt
     * @return Id
     */
    public static String getId(String token) {
        return JWT.decode(token).getId();
    }

    /**
     * 获取jwt中的iss
     *
     * @param token jwt
     * @return iss
     */
    public static String getIssuer(String token) {
        return JWT.decode(token).getIssuer();
    }

    /**
     * 获取jwt中的sub
     *
     * @param token jwt
     * @return sub
     */
    public static String getSubject(String token) {
        return JWT.decode(token).getSubject();
    }

    /**
     * 获取jwt中的aud
     *
     * @param token jwt
     * @return aud
     */
    public static List<String> getAudience(String token) {
        return JWT.decode(token).getAudience();
    }


    /**
     * 获取jwt中的aud
     *
     * @param token jwt
     * @return aud
     */
    public static Object getMapFromKey(String token, String key) {
        return JWT.decode(token).getClaim(key);
    }
}
