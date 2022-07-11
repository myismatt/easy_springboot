package com.easy.facade.controller;

import com.easy.facade.beans.base.ResultBean;
import com.easy.facade.beans.dto.RegisterDTO;
import com.easy.facade.framework.exception.CustomException;
import com.easy.facade.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 注册
 * </p>
 *
 * @Author Matt
 * @Date 2022/7/5 15:36
 */
@RestController
@Api(tags = "注册")
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    @ApiOperation(value = "注册账号", httpMethod = "POST")
    public ResultBean<String> register(@Valid @RequestBody RegisterDTO dto) {
        boolean verifyAccount = userService.accountIsNotExist(dto.getUsername());
        if (!verifyAccount) {
            throw new CustomException("账号已存在");
        }
        try {
            userService.registerNewUser(dto.getUsername(), dto.getPassword(), dto.getEmail());
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResultBean.error("注册失败");
        }
        return ResultBean.success("注册成功");
    }
}
