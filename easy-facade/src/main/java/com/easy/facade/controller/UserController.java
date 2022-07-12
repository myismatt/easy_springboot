package com.easy.facade.controller;

import com.easy.facade.beans.base.ResultBean;
import com.easy.facade.beans.dto.UserUpdateDTO;
import com.easy.facade.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 用户信息管理
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/14 16:28
 */
@RestController
@RequestMapping("admin/user")
@Api(tags = "用户信息管理")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("@auth.hasKey('admin:user:update')")
    @PutMapping(value = "update", headers = "Authorization")
    @ApiOperation(value = "更新", httpMethod = "PUT")
    public ResultBean<String> updateUserInfo(@Valid @RequestBody UserUpdateDTO dto) {
        userService.updateUserInfo(dto);
        return ResultBean.success("操作成功");
    }
}