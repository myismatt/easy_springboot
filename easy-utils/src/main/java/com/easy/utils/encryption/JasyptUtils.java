package com.easy.utils.encryption;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

/**
 * Jasypt参数加密
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/28 11:02
 */
public class JasyptUtils {

    private static final String PBE_WITH_HMAC_SHA512_AND_AES_256 = "PBEWITHHMACSHA512ANDAES_256";

    private JasyptUtils() {
        throw new IllegalAccessError("JasyptUtils.class");
    }

    /**
     * Jasypt 加密（PBE_WITH_HMAC_SHA512_AND_AES_256）
     *
     * @param plainText 待加密的原文
     * @param password  加密秘钥
     * @return java.lang.String
     */
    public static String encryptWithSHA512(String plainText, String password) {
        // 1. 创建加解密工具实例
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        // 2. 加解密配置
        SimpleStringPBEConfig config = getSimpleStringPBEConfig(password);
        encryptor.setConfig(config);
        // 3. 加密
        return encryptor.encrypt(plainText);
    }


    /**
     * Jasypt解密（PBE_WITH_HMAC_SHA512_AND_AES_256）
     *
     * @param encryptedText 待解密密文
     * @param password      解密秘钥
     * @return java.lang.String
     */
    public static String decryptWithSHA512(String encryptedText, String password) {
        // 1. 创建加解密工具实例
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        // 2. 加解密配置
        SimpleStringPBEConfig config = getSimpleStringPBEConfig(password);
        encryptor.setConfig(config);
        // 3. 解密
        return encryptor.decrypt(encryptedText);
    }

    /**
     * 加解密配置
     *
     * @param password 加密密码
     * @return SimpleStringPBEConfig
     */
    private static SimpleStringPBEConfig getSimpleStringPBEConfig(String password) {
        // 2. 加解密配置
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(password);
        config.setAlgorithm(PBE_WITH_HMAC_SHA512_AND_AES_256);
        // 为减少配置文件的书写，以下都是 Jasypt 3.x 版本，配置文件默认配置
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        return config;
    }
}
