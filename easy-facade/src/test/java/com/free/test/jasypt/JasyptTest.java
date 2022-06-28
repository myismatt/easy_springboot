package com.free.test.jasypt;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

/**
 * jasypt加密
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/16 14:55
 */
public class JasyptTest {

    private static final String PBEWITHHMACSHA512ANDAES_256 = "PBEWITHHMACSHA512ANDAES_256";

    /**
     * @param plainText 待加密的原文
     * @param factor    加密秘钥
     * @return java.lang.String
     * Jasyp 加密（PBEWITHHMACSHA512ANDAES_256）
     */
    public static String encryptWithSHA512(String plainText, String factor) {
        // 1. 创建加解密工具实例
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        // 2. 加解密配置
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(factor);
        config.setAlgorithm(PBEWITHHMACSHA512ANDAES_256);
        // 为减少配置文件的书写，以下都是 Jasyp 3.x 版本，配置文件默认配置
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        // 3. 加密
        return encryptor.encrypt(plainText);
    }

    /**
     * @param encryptedText 待解密密文
     * @param factor        解密秘钥
     * @return java.lang.String
     * Jaspy解密（PBEWITHHMACSHA512ANDAES_256）
     */
    public static String decryptWithSHA512(String encryptedText, String factor) {
        // 1. 创建加解密工具实例
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        // 2. 加解密配置
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(factor);
        config.setAlgorithm(PBEWITHHMACSHA512ANDAES_256);
        // 为减少配置文件的书写，以下都是 Jasyp 3.x 版本，配置文件默认配置
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        // 3. 解密
        return encryptor.decrypt(encryptedText);
    }

    public static void main(String[] args) {
        String factor = "X7m2#q4nMjx5kUeW";
        String plainText = "/a1qXkXuWxm5xHbTdIBw1Jat14br6IocoEZVazsZZFQ2N4bJl9xNXRoe/8EquTgDkduP9v+gpmTgSFgW68kgJjs4cku2R1hGn3SKOPp3o4UAc/uo5Gbdjw1HjsY0vT0j76HhAgMBAAECgYAIqfH03KBCp7oEej0fFLJBG1su9CaT8bSDZore6GPLglUBWmYCgijh6rj8wJ9V2jui+d4hEnUsyS4GOlwt4co00H4C7FLWjPbvvx05sNTaFTPsqRnWEXPXs+dHHq+hH9tgJi5mOOg9S85UiWVYddhy2fUnFkuD1SIzC9FMNGJ0AQJBAL6R+ORyYNNzqHEz+mYL/fTiwSn2m+F6PRrFEhS2avW/W21ICW5dOoSJDCqjgqrDHgIfMhpsGEEOGQq7mPe1GhECQQCskWPakSGPV3chBcXzykW9e3Z4KezyLxwodcXCEYh4pm2H3XXOYsPdoRV4Ns7zLTwI//2n4UUlqTWA3URk1brRAkAhQP+ZfYv/2fLOri9Hl1gdX2QtmmbhFZ+MCE97ZcmMUHP3wdZDd+k6L1/8IMQwYBPgcukfSzWARKGCCfJLZ0LBAkEAqFd8jW8eyfKNKA21uckMl3elbjuYBfmnAmFBdbwc4XWZS/ziO19lqwLFKuxsnhT2h+ER1X+QMNkMdJVlbuiAwQJBALCeDoJSIteFBrNMJWXjLRAevJK+k3Go34oRavMoUi1MPprr8MAlmBtk5AiIJmRdZfjXoVGNBrOF8qBivg/VT/A=";

        String encryptWithSHA512Str = encryptWithSHA512(plainText, factor);
        String decryptWithSHA512Str = decryptWithSHA512(encryptWithSHA512Str, factor);
        System.out.println("采用AES256加密前原文密文：" + encryptWithSHA512Str);
        System.out.println("采用AES256解密后密文原文:" + decryptWithSHA512Str);
    }

}
