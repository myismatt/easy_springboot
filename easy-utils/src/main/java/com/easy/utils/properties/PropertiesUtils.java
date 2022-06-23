package com.easy.utils.properties;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * 配置文件读取
 * </p>
 *
 * @Author Matt
 * @Date 2021/9/13 10:35
 */
public class PropertiesUtils {

    /**
     * 私有构造方法，防止工具类被new
     */
    private PropertiesUtils() {
        throw new IllegalAccessError();
    }

    /**
     * 根据json文件名称获取json
     *
     * @param fileName json文件名称前缀，如果在resource下直接写文件名，如果有路径，请在前面添加路径如："com/xxx/abc"
     * @return JSONObject
     */
    public static JSONObject getJsonResource(String fileName) {
        fileName += ".json";
        ClassLoader classLoader = getClassLoader();

        Enumeration<URL> resources;
        JSONObject jsonObject = new JSONObject();
        try {
            resources = classLoader.getResources(fileName);
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            try {
                String json = Resources.toString(url, Charsets.UTF_8);
                // 有多个的时候，后面的覆盖前面的
                jsonObject.putAll(JSON.parseObject(json));
            }
            catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return jsonObject;
    }

    /**
     * 根据json文件名称获取json数组
     *
     * @param fileName json文件名称前缀，如果在resource下直接写文件名，如果有路径，请在前面添加路径如："com/xxx/abc"
     * @return JSONArray
     */
    public static JSONArray getJsonArrayResource(String fileName) {
        fileName += ".json";
        ClassLoader classLoader = getClassLoader();

        Enumeration<URL> resources;
        try {
            resources = classLoader.getResources(fileName);
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        if (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            try {
                String json = Resources.toString(url, Charsets.UTF_8);
                // 有多个的时候，后面的覆盖前面的
                return JSON.parseArray(json);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static ClassLoader getClassLoader() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader != null) {
            return classLoader;
        }
        return PropertiesUtils.class.getClassLoader();
    }
}
