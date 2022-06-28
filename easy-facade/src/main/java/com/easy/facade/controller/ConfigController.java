package com.easy.facade.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.easy.facade.beans.base.ResultBean;
import com.easy.facade.beans.dto.ConfigDTO;
import com.easy.facade.beans.dto.ConfigSearchDTO;
import com.easy.facade.beans.model.Config;
import com.easy.facade.beans.vo.ConfigVO;
import com.easy.facade.service.ConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 参数配置管理
 *
 * @Author Matt
 * @Date 2021/8/9 14:07
 */
@RestController
@RequestMapping("admin/config")
@Api(tags = "参数配置管理")
public class ConfigController {
    private final ConfigService configService;

    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }


    /**
     * 重载缓存
     */
    @GetMapping("overloadConfig")
    @ApiOperation("重载缓存")
    public void overloadConfig() {
        configService.configInitCache();
    }

    /**
     * 获取参数配置
     *
     * @param configKey key
     * @return ConfigCache
     */
    @GetMapping("getConfig/{configKey}")
    @ApiOperation("获取参数配置")
    public ResultBean<ConfigVO> getConfig(@PathVariable String configKey) {
        return ResultBean.success(configService.getConfig(configKey));
    }

    /**
     * 集合查询
     *
     * @param dto 查询入参
     * @return ConfigCache
     */
    @GetMapping("getListConfig")
    @ApiOperation("集合查询")
    public ResultBean<List<ConfigVO>> getList(ConfigSearchDTO dto) {
        return ResultBean.success(configService.getList(dto));
    }

    /**
     * 分页查询
     *
     * @param dto 查询入参
     * @return IPage<Config>
     */
    @GetMapping("pageConfig")
    @ApiOperation("分页查询")
    public ResultBean<IPage<Config>> pageConfig(ConfigSearchDTO dto) {
        return configService.pageConfig(dto);
    }

    /**
     * 新增参数配置
     *
     * @param dto 入参
     * @return 操作结果
     */
    @PutMapping("addConfig")
    @ApiOperation("新增参数配置")
    public ResultBean<String> addConfig(@Valid @RequestBody ConfigDTO dto) {
        return configService.addConfig(dto);
    }

    /**
     * 更新参数配置
     *
     * @param config 入参
     * @return 操作结果
     */
    @PostMapping("updateConfig")
    @ApiOperation("更新参数配置")
    public ResultBean<String> updateConfig(@Valid @RequestBody Config config) {
        return configService.updateConfig(config);
    }

    /**
     * 删除参数配置
     *
     * @param ids 主键集合
     * @return 操作结果
     */
    @DeleteMapping("delConfig")
    @ApiOperation("删除参数配置")
    public ResultBean<String> delConfig(@RequestBody String[] ids) {
        return configService.delConfig(ids);
    }

}
