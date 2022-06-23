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
        String plainText = "Dt#FE95utW3D";

        String encryptWithSHA512Str = encryptWithSHA512(plainText, factor);
        String decryptWithSHA512Str = decryptWithSHA512(encryptWithSHA512Str, factor);
        System.out.println("采用AES256加密前原文密文：" + encryptWithSHA512Str);
        System.out.println("采用AES256解密后密文原文:" + decryptWithSHA512Str);
    }

}
