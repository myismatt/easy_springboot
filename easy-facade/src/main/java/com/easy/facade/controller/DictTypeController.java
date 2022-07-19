package com.easy.facade.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.easy.facade.beans.base.ResultBean;
import com.easy.facade.beans.dto.DictTypeDTO;
import com.easy.facade.beans.dto.DictTypeSearchDTO;
import com.easy.facade.beans.dto.DictTypeUpdateDTO;
import com.easy.facade.beans.model.DictData;
import com.easy.facade.beans.model.DictType;
import com.easy.facade.beans.vo.DictTypeVO;
import com.easy.facade.service.DictDataService;
import com.easy.facade.service.DictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * 字典类型
 * <p>
 * 2022/1/13 18:05
 *
 * @Author Matt
 */
@Api(tags = "字典类型")
@RestController
@RequestMapping("admin/dictType")
public class DictTypeController {

    private final DictDataService dictDataService;
    private final DictTypeService dictTypeService;

    public DictTypeController(DictDataService dictDataService, DictTypeService dictTypeService) {
        this.dictDataService = dictDataService;
        this.dictTypeService = dictTypeService;
    }

    /**
     * 重载字典缓存
     */
    @GetMapping(value = "reload_dict", headers = "Authorization")
    @ApiOperation(value = "重载字典缓存", httpMethod = "GET")
    @PreAuthorize("@auth.hasRole('admin')")
    public void reloadDictCache() {
        dictTypeService.initDictCache();
    }

    /**
     * 获取字典详情
     *
     * @param dictType 字典类型
     * @return 字典详情 DictTypeVO
     */
    @GetMapping("dict_type/{dictType}")
    @ApiOperation(value = "获取字典数据详情", httpMethod = "GET")
    public ResultBean<Optional<DictTypeVO>> getDictTypeInfo(@ApiParam("字典类型") @PathVariable String dictType) {
        return ResultBean.success("查询成功", dictTypeService.getDictTypeInfo(dictType));
    }

    /**
     * 获取字典类型下的所有字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据
     */
    @GetMapping("dict_data/{dictType}")
    @ApiOperation(value = "获取字典类型下的所有字典数据", httpMethod = "GET")
    public ResultBean<List<DictData>> getDictDataListByDictType(@ApiParam("字典类型") @PathVariable String dictType) {
        return ResultBean.success(dictDataService.getDictDataListByDictType(dictType));
    }

    /**
     * 集合查询
     *
     * @param dto 查询入参
     * @return 分页数据
     */
    @GetMapping(value = "query", headers = "Authorization")
    @ApiOperation(value = "分页查询字典类型", httpMethod = "GET")
    @PreAuthorize("@auth.hasKey('admin:dictType:query')")
    public ResultBean<List<DictType>> listDictType(DictTypeSearchDTO dto) {
        return ResultBean.success("查询成功", dictTypeService.listDictType(dto));
    }

    /**
     * 分页查询
     *
     * @param dto 查询入参
     * @return 分页数据
     */
    @GetMapping(value = "page", headers = "Authorization")
    @ApiOperation(value = "分页查询字典类型", httpMethod = "GET")
    @PreAuthorize("@auth.hasKey('admin:dictType:page')")
    public ResultBean<IPage<DictType>> pageDictType(DictTypeSearchDTO dto) {
        return ResultBean.success("查询成功", dictTypeService.pageDictType(dto));
    }

    /**
     * 新增字典类型
     *
     * @param dto 新增入参
     */
    @PostMapping(value = "add", headers = "Authorization")
    @ApiOperation(value = "新增字典类型", httpMethod = "POST")
    @PreAuthorize("@auth.hasKey('admin:dictType:add')")
    public ResultBean<String> addDictType(@Valid @RequestBody DictTypeDTO dto) {
        dictTypeService.addDictType(dto);
        return ResultBean.success("操作成功");
    }

    /**
     * 更新字典类型
     *
     * @param dto 字典信息
     */
    @PutMapping(value = "update", headers = "Authorization")
    @ApiOperation(value = "修改字典类型", httpMethod = "PUT")
    @PreAuthorize("@auth.hasKey('admin:dictType:update')")
    public ResultBean<String> updateDictType(@Valid @RequestBody DictTypeUpdateDTO dto) {
        dictTypeService.updateDictType(dto);
        return ResultBean.success("操作成功");
    }

    /**
     * 删除字典类型
     *
     * @param id 主键
     */
    @DeleteMapping(value = "del", headers = "Authorization")
    @ApiOperation(value = "删除字典类型", httpMethod = "DELETE")
    @PreAuthorize("@auth.hasKey('admin:dictType:del')")
    public ResultBean<String> delDictType(@RequestBody String id) {
        dictTypeService.delDictType(id);
        return ResultBean.success("操作成功");
    }
}