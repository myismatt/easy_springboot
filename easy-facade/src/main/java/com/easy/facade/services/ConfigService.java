package com.easy.facade.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easy.facade.beans.dto.ConfigDTO;
import com.easy.facade.beans.dto.ConfigSearchDTO;
import com.easy.facade.beans.dto.ConfigUpdateDTO;
import com.easy.facade.beans.model.Config;
import com.easy.facade.beans.vo.ConfigVO;
import com.easy.facade.dao.ConfigMapper;
import com.easy.facade.enums.YesOrNoEnum;
import com.easy.facade.utils.ConfigUtils;
import com.easy.utils.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 参数配置
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/16 14:30
 */
@Service
public class ConfigService extends ServiceImpl<ConfigMapper, Config> {

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
            ConfigUtils.getInstance().setConfigList(list);
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
    public IPage<Config> pageConfig(ConfigSearchDTO dto) {
        return lambdaQuery().eq(StringUtils.isNotBlank(dto.getConfigName()), Config::getConfigName, dto.getConfigName())
                .eq(StringUtils.isNotBlank(dto.getConfigKey()), Config::getConfigKey, dto.getConfigKey())
                .eq(StringUtils.isNotNull(dto.getConfigType()), Config::getConfigType, dto.getConfigType())
                .page(new Page<Config>().setCurrent(dto.getCurrent()).setSize(dto.getSize()));
    }

    /**
     * 新增参数配置
     *
     * @param dto 入参
     */
    public void addConfig(ConfigDTO dto) {
        Config newConfig = new Config();
        BeanUtils.copyProperties(dto, newConfig);
        this.save(newConfig);
        // 如果是系统缓存
        if (dto.getConfigType().equals(YesOrNoEnum.YES)) {
            // 增加缓存
            ConfigVO cache = new ConfigVO();
            BeanUtils.copyProperties(newConfig, cache);
            ConfigUtils.getInstance().addConfigList(cache);
        }
    }

    /**
     * 更新参数配置
     *
     * @param dto 入参
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateConfig(ConfigUpdateDTO dto) {
        Config config = new Config();
        BeanUtils.copyProperties(dto, config);
        this.updateById(config);
        // 变更缓存
        ConfigVO cache = new ConfigVO();
        BeanUtils.copyProperties(config, cache);
        ConfigUtils.getInstance().updateConfig(cache);
    }

    /**
     * 删除参数配置
     *
     * @param ids 主键集合
     */
    public void delConfig(String[] ids) {
        this.removeByIds(Arrays.asList(ids));
        ConfigUtils.getInstance().delConfig(List.of(ids));
    }

    /**
     * 获取参数配置
     *
     * @param configKey key
     * @return ConfigCache
     */
    public ConfigVO getConfig(String configKey) {
        AtomicReference<ConfigVO> configCache = new AtomicReference<>();
        List<ConfigVO> list = ConfigUtils.getInstance().getConfigList();
        list.parallelStream().forEach(config -> {
            if (configKey.equals(config.getConfigKey())) {
                configCache.set(config);
            }
        });
        return configCache.get();
    }
}
