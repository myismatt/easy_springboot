package com.easy.facade.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.easy.facade.beans.base.ResultBean;
import com.easy.facade.beans.dto.DictDataDTO;
import com.easy.facade.beans.dto.DictDataSearchDTO;
import com.easy.facade.beans.dto.DictDataUpdateDTO;
import com.easy.facade.beans.model.DictData;
import com.easy.facade.service.DictDataService;
import com.easy.facade.service.DictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 字典数据
 * <p>
 * 2022/1/13 18:05
 *
 * @Author Matt
 */
@RestController
@RequestMapping("admin/dictData")
@Api(tags = "字典数据")
public class DictDataController {

    private final DictDataService dictDataService;
    private final DictTypeService dictTypeService;

    public DictDataController(DictDataService dictDataService, DictTypeService dictTypeService) {
        this.dictDataService = dictDataService;
        this.dictTypeService = dictTypeService;
    }


    /**
     * 集合查询
     *
     * @param dto 分页查询入参
     * @return 分页数据
     */
    @GetMapping(value = "query", headers = "Authorization")
    @PreAuthorize("@auth.hasKey('admin:dictData:query')")
    @ApiOperation(value = "集合查询字典数据", httpMethod = "GET")
    public ResultBean<List<DictData>> listDictData(DictDataSearchDTO dto) {
        return ResultBean.success("查询成功", dictDataService.listDictData(dto));
    }

    /**
     * 分页查询
     *
     * @param dto 分页查询入参
     * @return 分页数据
     */
    @GetMapping(value = "page", headers = "Authorization")
    @PreAuthorize("@auth.hasKey('admin:dictData:page')")
    @ApiOperation(value = "分页查询字典数据", httpMethod = "GET")
    public ResultBean<IPage<DictData>> pageDictData(DictDataSearchDTO dto) {
        return ResultBean.success("查询成功", dictDataService.pageDictData(dto));
    }

    /**
     * 新增字典数据
     *
     * @param dto 字典数据入参
     */
    @PostMapping(value = "add", headers = "Authorization")
    @PreAuthorize("@auth.hasKey('admin:dictData:add')")
    @ApiOperation(value = "新增字典数据", httpMethod = "POST")
    public ResultBean<String> addDictData(@Valid @RequestBody DictDataDTO dto) {
        dictDataService.addDictData(dto);
        // 重载缓存
        dictTypeService.initDictCache();
        return ResultBean.success("操作成功");
    }

    /**
     * 更新字典数据
     *
     * @param updateDTO 字典数据
     */
    @PutMapping(value = "update", headers = "Authorization")
    @PreAuthorize("@auth.hasKey('admin:dictData:update')")
    @ApiOperation(value = "更新字典数据", httpMethod = "PUT")
    public ResultBean<String> updateDictData(@Valid @RequestBody DictDataUpdateDTO updateDTO) {
        dictDataService.updateDictData(updateDTO);
        // 重载缓存
        dictTypeService.initDictCache();
        return ResultBean.success("操作成功");
    }

    /**
     * 删除字典数据
     *
     * @param ids 主键ID集合
     */
    @DeleteMapping(value = "del", headers = "Authorization")
    @PreAuthorize("@auth.hasKey('admin:dictData:del')")
    @ApiOperation(value = "删除字典数据", httpMethod = "DELETE")
    public ResultBean<String> delDictData(@RequestBody List<String> ids) {
        dictDataService.delDictData(ids);
        // 重载缓存
        dictTypeService.initDictCache();
        return ResultBean.success("操作成功");
    }
}