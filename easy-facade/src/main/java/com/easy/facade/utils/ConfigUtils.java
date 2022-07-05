package com.easy.facade.utils;

import com.easy.facade.beans.vo.ConfigVO;
import com.easy.facade.constants.RedisKey;
import com.easy.facade.framework.redis.RedisUtils;
import com.easy.utils.spring.SpringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 参数配置工具
 * </p>
 *
 * @Author Matt
 * @Date 2022/7/5 17:55
 */
public class ConfigUtils {

    /**
     * 配置参数缓存键
     */
    public static final String CONFIG_LIST_KEY = RedisKey.CONFIG_LIST_KEY;

    /**
     * 全局缓存
     */
    private static List<ConfigVO> configList = new ArrayList<>();


    private ConfigUtils() {
    }

    public static synchronized List<ConfigVO> getInstance() {
        if (configList == null) {

        }
        return getCache(CONFIG_LIST_KEY);
    }

    /**
     * 设置redis缓存
     *
     * @param key  key
     * @param data 数据对象
     */
    private static void setCache(String key, Object data) {
        SpringUtils.getBean(RedisUtils.class).setCacheObject(key, data);
    }

    /**
     * 获取缓存
     *
     * @param key key
     * @param <T> 对象
     * @return 缓存
     */
    private static <T> T getCache(String key) {
        return SpringUtils.getBean(RedisUtils.class).getCacheObject(key);
    }

    /**
     * 设置系统参数缓存
     *
     * @param data 数据对象
     */
    public static void setConfigList(Object data) {
        setCache(CONFIG_LIST_KEY, data);
    }

    /**
     * 设置系统参数缓存
     *
     * @param data 数据对象
     */
    public static void addConfigList(ConfigVO data) {
        configList.add(data);
        setCache(CONFIG_LIST_KEY, data);
    }
}
