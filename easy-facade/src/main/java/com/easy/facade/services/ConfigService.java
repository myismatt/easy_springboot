package com.easy.facade.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easy.facade.beans.base.ResultBean;
import com.easy.facade.beans.dto.ConfigDTO;
import com.easy.facade.beans.dto.ConfigSearchDTO;
import com.easy.facade.beans.model.Config;
import com.easy.facade.beans.vo.ConfigVO;
import com.easy.facade.constants.RedisKey;
import com.easy.facade.dao.ConfigMapper;
import com.easy.facade.enums.YesOrNoEnum;
import com.easy.facade.framework.redis.RedisUtils;
import com.easy.utils.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * 参数配置
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/16 14:30
 */
@Service
public class ConfigService extends ServiceImpl<ConfigMapper, Config> {

    private final RedisUtils redisUtils;

    public ConfigService(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }


    /**
     * 项目启动时，初始化系统配置参数到缓存
     */
    @PostConstruct
    public void init() {
        configInitCache();
    }

    /**
     * 缓存系统参数配置
     */
    public void configInitCache() {
        ConfigSearchDTO dto = new ConfigSearchDTO();
        dto.setConfigType(YesOrNoEnum.YES);
        List<ConfigVO> list = this.baseMapper.selectConfigVO(dto);
        if (StringUtils.isNotNull(list)) {
            redisUtils.setCacheList(RedisKey.CONFIG_LIST_KEY, list);
        }
    }

    /**
     * 集合查询
     *
     * @param dto 查询入参
     * @return ConfigCache
     */
    public List<ConfigVO> getList(ConfigSearchDTO dto) {
        return this.baseMapper.selectConfigVO(dto);
    }

    /**
     * 分页查询
     *
     * @param dto 查询入参
     * @return IPage<Config>
     */
    public ResultBean<IPage<Config>> pageConfig(ConfigSearchDTO dto) {
        IPage<Config> page =
                lambdaQuery().eq(StringUtils.isNotBlank(dto.getConfigName()), Config::getConfigName, dto.getConfigName())
                        .eq(StringUtils.isNotBlank(dto.getConfigKey()), Config::getConfigKey, dto.getConfigKey())
                        .eq(StringUtils.isNotNull(dto.getConfigType()), Config::getConfigType, dto.getConfigType())
                        .page(new Page<Config>().setCurrent(dto.getCurrent()).setSize(dto.getSize()));
        return ResultBean.success(page);
    }

    /**
     * 新增参数配置
     *
     * @param dto 入参
     * @return 操作结果
     */
    public ResultBean<String> addConfig(ConfigDTO dto) {
        Config newConfig = new Config();
        newConfig.setConfigName(dto.getConfigName());
        newConfig.setConfigKey(dto.getConfigKey());
        newConfig.setConfigValue(dto.getConfigValue());
        newConfig.setConfigType(dto.getConfigType());
        newConfig.setRemark(dto.getRemark());
        this.save(newConfig);
        // 更新系统参数缓存
        if (dto.getConfigType().equals(YesOrNoEnum.YES)) {
            configInitCache();
        }
        return ResultBean.success();
    }

    /**
     * 更新参数配置
     *
     * @param config 入参
     * @return 操作结果
     */
    public ResultBean<String> updateConfig(Config config) {
        this.updateById(config);
        return ResultBean.success();
    }

    /**
     * 删除参数配置
     *
     * @param ids 主键集合
     * @return 操作结果
     */
    public ResultBean<String> delConfig(String[] ids) {
        this.removeByIds(Arrays.asList(ids));
        return ResultBean.success();
    }

    /**
     * 获取参数配置
     *
     * @param configKey key
     * @return ConfigCache
     */
    public ConfigVO getConfig(String configKey) {
        List<ConfigVO> list = redisUtils.getCacheList(RedisKey.CONFIG_LIST_KEY);
        for (ConfigVO configCache : list) {
            if (configKey.equals(configCache.getConfigKey())) {
                return configCache;
            }
        }
        return null;
    }
}
