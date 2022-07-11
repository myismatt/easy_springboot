package com.easy.facade.controller;

import com.easy.facade.beans.base.ResultBean;
import com.easy.facade.beans.dto.RoleDTO;
import com.easy.facade.beans.vo.RoleInfoVO;
import com.easy.facade.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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
     * 获取角色信息
     *
     * @param roleKey 角色关键词
     * @return RoleInfoVO
     */
    @GetMapping(value = "info/{roleKey}", headers = "Authorization")
    @ApiOperation(value = "获取角色详情", httpMethod = "GET")
    public ResultBean<RoleInfoVO> getRoleInfo(@PathVariable String roleKey) {
        return ResultBean.success(roleService.getRoleInfo(roleKey));
    }

    /**
     * 检查角色Key是否存在
     *
     * @return false 不存在;true 存在
     */
    @GetMapping("check_role_key/{roleKey}")
    @ApiOperation(value = "检查角色Key是否存在", httpMethod = "GET")
    public ResultBean<Boolean> checkRoleKeyExists(@PathVariable String roleKey) {
        return ResultBean.success("查询成功", roleService.checkRoleKeyExists(roleKey));
    }

    /**
     * 新增角色
     *
     * @return 操作结果
     */
    @PostMapping(value = "add", headers = "Authorization")
    @ApiOperation(value = "新增角色", httpMethod = "POST")
    public ResultBean<String> addRole(@Valid @RequestBody RoleDTO dto) {
        roleService.addRole(dto);
        return ResultBean.success("新增成功");
    }

}
