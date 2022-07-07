package com.easy.facade.controller;

import com.easy.facade.beans.base.ResultBean;
import com.easy.facade.beans.dto.RoleDTO;
import com.easy.facade.services.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 角色管理
 * </p>
 *
 * @Author Matt
 * @Date 2022/7/5 15:43
 */
@RestController
@RequestMapping("admin/role")
@Api(tags = "角色管理")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 新增角色
     *
     * @return 操作结果
     */
    @PostMapping("add")
    @ApiOperation(value = "新增角色", httpMethod = "POST")
    public ResultBean<String> addRole(@Valid @RequestBody RoleDTO dto) {
        roleService.addRole(dto);
        return ResultBean.success("新增成功");
    }

}
