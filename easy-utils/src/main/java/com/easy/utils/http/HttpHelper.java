package com.easy.utils.http;

import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.servlet.ServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * 通用http工具封装
 * </p>
 *
 * @Author Matt
 * @Date 2021/5/19 10:08
 */
public final class HttpHelper {

    private HttpHelper() {
        throw new IllegalAccessError("HttpHelper.class");
    }

    public static String getBodyString(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try (InputStream inputStream = request.getInputStream()) {
            reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        catch (IOException e) {
            throw new RuntimeException("getBodyString出现问题！");
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (IOException e) {
                    throw new RuntimeException(ExceptionUtils.getMessage(e));
                }
            }
        }
        return sb.toString();
    }
}
