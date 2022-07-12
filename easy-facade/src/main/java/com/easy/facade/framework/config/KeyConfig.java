package com.easy.facade.framework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 加密密钥
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/28 11:22
 */
@Component
public class KeyConfig {

    /**
     * rsa公钥
     */
    private static String rsaPublicKey;
    /**
     * rsa私钥
     */
    private static String rsaPrivateKey;
    /**
     * des密钥
     */
    private static String desKey;

    public static String getRsaPublicKey() {
        return rsaPublicKey;
    }

    public static String getRsaPrivateKey() {
        return rsaPrivateKey;
    }

    public static String getDesKey() {
        return desKey;
    }

    @Value("${key.desKey:}")
    public void setDesKey(String desKey) {
        KeyConfig.desKey = desKey;
    }

    @Value("${key.rsaPublicKey:}")
    public void setPublicKey(String publicKey) {
        KeyConfig.rsaPublicKey = publicKey;
    }

    @Value("${key.rsaPrivateKey:}")
    public void setPrivateKey(String privateKey) {
        KeyConfig.rsaPrivateKey = privateKey;
    }
}