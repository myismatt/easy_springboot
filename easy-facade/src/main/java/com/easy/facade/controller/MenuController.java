package com.easy.facade.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.easy.facade.beans.base.ResultBean;
import com.easy.facade.beans.dto.MenuDTO;
import com.easy.facade.beans.dto.MenuSearchDTO;
import com.easy.facade.beans.model.Menu;
import com.easy.facade.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 菜单管理
 * </p>
 *
 * @Author Matt
 * @Date 2022/7/5 15:44
 */
@RestController
@Api(tags = "菜单权限管理")
@RequestMapping("admin/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }


    /**
     * 获取菜单(集合)
     *
     * @param dto 查询参数
     * @return 集合数据
     */
    @GetMapping(value = "query", headers = "Authorization")
    @ApiOperation(value = "列表", httpMethod = "GET")
    public ResultBean<List<Menu>> getList(MenuSearchDTO dto) {
        return ResultBean.success("查询成功", menuService.getList(dto));
    }

    /**
     * 获取菜单(分页)
     *
     * @param dto 查询参数
     * @return 分页数据
     */
    @GetMapping(value = "page", headers = "Authorization")
    @ApiOperation(value = "分页", httpMethod = "GET")
    public ResultBean<IPage<Menu>> getPage(MenuSearchDTO dto) {
        return ResultBean.success("查询成功", menuService.getPage(dto));
    }

    /**
     * 新增菜单权限
     *
     * @return 操作结果
     */
    @PostMapping(value = "add", headers = "Authorization")
    @ApiOperation(value = "新增", httpMethod = "POST")
    public ResultBean<String> addMenu(@Valid @RequestBody MenuDTO dto) {
        menuService.addMenu(dto);
        return ResultBean.success("操作成功");
    }

}