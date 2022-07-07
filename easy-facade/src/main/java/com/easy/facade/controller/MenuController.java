package com.easy.facade.controller;

import com.easy.facade.beans.base.ResultBean;
import com.easy.facade.beans.dto.MenuDTO;
import com.easy.facade.services.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
     * 新增菜单权限
     *
     * @return 操作结果
     */
    @PostMapping("add")
    @ApiOperation(value = "新增菜单", httpMethod = "POST")
    public ResultBean<String> addMenu(@Valid @RequestBody MenuDTO dto) {
        menuService.addMenu(dto);
        return ResultBean.success("操作成功");
    }

}
