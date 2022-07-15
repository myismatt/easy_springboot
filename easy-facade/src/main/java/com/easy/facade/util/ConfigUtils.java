package com.easy.facade.util;

import com.easy.facade.beans.vo.ConfigVO;
import com.easy.facade.constant.RedisKeyConsts;
import com.easy.facade.enums.YesOrNoEnum;
import com.easy.facade.framework.redis.RedisUtils;
import com.easy.utils.spring.SpringUtils;

import java.util.List;
import java.util.stream.Collectors;

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
    public static final String CONFIG_LIST_KEY = RedisKeyConsts.CONFIG_LIST_KEY;

    private static volatile ConfigUtils configUtils = null;


    private ConfigUtils() {
        getConfigList();
    }

    public static ConfigUtils getInstance() {
        if (configUtils == null) {
            synchronized (ConfigUtils.class) {
                if (configUtils == null) {
                    configUtils = new ConfigUtils();
                }
            }
        }
        return configUtils;
    }


    /**
     * 获取系统参数缓存
     */
    public List<ConfigVO> getConfigList() {
        return getCache();
    }

    /**
     * 设置系统参数缓存
     *
     * @param data 数据对象
     */
    public void setConfigList(Object data) {
        setCache(data);
    }

    /**
     * 新增参数缓存
     *
     * @param data 数据对象
     */
    public void addConfigList(ConfigVO data) {
        List<ConfigVO> cacheList = getInstance().getConfigList();
        cacheList.add(data);
        setCache(cacheList);
    }

    /**
     * 更新参数配置缓存
     *
     * @param data 数据对象
     */
    public void updateConfig(ConfigVO data) {
        List<ConfigVO> cacheList = getInstance().getConfigList();
        List<ConfigVO> oldList = cacheList.parallelStream().filter(config -> config.getId().equals(data.getId())).collect(Collectors.toList());
        if (oldList.size() > 0) {
            cacheList.removeAll(oldList);
        }
        // 如果是设置为系统枚举
        if (data.getConfigType().equals(YesOrNoEnum.YES)) {
            cacheList.add(data);
            setConfigList(cacheList);
        }
    }


    /**
     * 更新参数配置缓存
     *
     * @param ids id集合
     */
    public void delConfig(List<String> ids) {
        List<ConfigVO> cacheList = getInstance().getConfigList();
        List<ConfigVO> oldList = cacheList.parallelStream().filter(config -> ids.contains(config.getId())).collect(Collectors.toList());
        if (oldList.size() > 0) {
            cacheList.removeAll(oldList);
            setConfigList(cacheList);
        }
    }

    /**
     * 设置redis缓存
     *
     * @param data 数据对象
     */
    private void setCache(Object data) {
        SpringUtils.getBean(RedisUtils.class).setCacheObject(ConfigUtils.CONFIG_LIST_KEY, data);
    }

    /**
     * 获取缓存
     *
     * @param <T> 对象
     * @return 缓存
     */
    private <T> T getCache() {
        return SpringUtils.getBean(RedisUtils.class).getCacheObject(ConfigUtils.CONFIG_LIST_KEY);
    }


}