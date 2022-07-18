package com.easy.facade.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.easy.facade.beans.base.ResultBean;
import com.easy.facade.beans.dto.ConfigDTO;
import com.easy.facade.beans.dto.ConfigSearchDTO;
import com.easy.facade.beans.dto.ConfigUpdateDTO;
import com.easy.facade.beans.model.Config;
import com.easy.facade.beans.vo.ConfigVO;
import com.easy.facade.service.ConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @GetMapping("overload")
    @ApiOperation(value = "重载缓存", httpMethod = "GET")
    public void overloadConfig() {
        configService.configInitCache();
    }

    /**
     * 获取参数配置
     *
     * @param configKey key
     * @return ConfigCache
     */
    @GetMapping("{configKey}")
    @ApiOperation(value = "获取参数配置", httpMethod = "GET")
    public ResultBean<ConfigVO> getConfig(@PathVariable String configKey) {
        return ResultBean.success(configService.getConfig(configKey));
    }

    /**
     * 集合查询
     *
     * @param dto 查询入参
     * @return ConfigCache
     */
    @GetMapping(value = "query", headers = "Authorization")
    @PreAuthorize("@auth.hasKey('admin:config:qurey')")
    @ApiOperation(value = "集合查询参数配置", httpMethod = "GET")
    public ResultBean<List<ConfigVO>> getList(ConfigSearchDTO dto) {
        return ResultBean.success(configService.getList(dto));
    }

    /**
     * 分页查询
     *
     * @param dto 查询入参
     * @return IPage<Config>
     */
    @GetMapping(value = "page", headers = "Authorization")
    @PreAuthorize("@auth.hasKey('admin:config:page')")
    @ApiOperation(value = "分页查询参数配置", httpMethod = "GET")
    public ResultBean<IPage<Config>> pageConfig(ConfigSearchDTO dto) {
        return ResultBean.success(configService.pageConfig(dto));
    }

    /**
     * 新增参数配置
     *
     * @param dto 入参
     * @return 操作结果
     */
    @PostMapping(value = "add", headers = "Authorization")
    @PreAuthorize("@auth.hasKey('admin:config:add')")
    @ApiOperation(value = "新增参数配置", httpMethod = "POST")
    public ResultBean<String> addConfig(@Valid @RequestBody ConfigDTO dto) {
        configService.addConfig(dto);
        return ResultBean.success("新增成功");
    }

    /**
     * 更新参数配置
     *
     * @param dto 入参
     * @return 操作结果
     */
    @PutMapping(value = "update", headers = "Authorization")
    @PreAuthorize("@auth.hasKey('admin:config:update')")
    @ApiOperation(value = "更新参数配置", httpMethod = "PUT")
    public ResultBean<String> updateConfig(@Valid @RequestBody ConfigUpdateDTO dto) {
        configService.updateConfig(dto);
        return ResultBean.success("更新成功");
    }

    /**
     * 删除参数配置
     *
     * @param ids 主键集合
     * @return 操作结果
     */
    @DeleteMapping(value = "delete", headers = "Authorization")
    @PreAuthorize("@auth.hasKey('admin:config:del')")
    @ApiOperation(value = "删除参数配置", httpMethod = "DELETE")
    public ResultBean<String> delConfig(@RequestBody String[] ids) {
        configService.delConfig(ids);
        return ResultBean.success("删除成功");
    }

}