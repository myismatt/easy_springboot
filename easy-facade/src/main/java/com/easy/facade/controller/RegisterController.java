package com.easy.facade.controller;

import com.easy.facade.beans.base.ResultBean;
import com.easy.facade.beans.dto.RegisterDTO;
import com.easy.facade.beans.model.User;
import com.easy.facade.enums.AccountStatusEnum;
import com.easy.facade.framework.exception.CustomException;
import com.easy.facade.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 注册账号
     *
     * @param dto 入参
     * @return 操作结果
     */
    @PostMapping("register")
    @ApiOperation(value = "注册账号", httpMethod = "POST")
    public ResultBean<String> register(@Valid @RequestBody RegisterDTO dto) {
        boolean verifyAccount = userService.accountIsNotExist(dto.getUsername());
        if (!verifyAccount) {
            throw new CustomException("账号已存在");
        }
        try {
            userService.registerNewUser(dto.getUsername(), dto.getPassword(), dto.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBean.error("注册失败");
        }
        return ResultBean.success("注册成功");
    }

    /**
     * 发送激活码
     *
     * @param username 账号
     * @return 结果
     */
    @GetMapping("activation_code/{username}")
    @ApiOperation(value = "发送激活码", httpMethod = "GET")
    public ResultBean<String> sendActivationCode(@PathVariable String username) {
        User user = userService.lambdaQuery().eq(User::getUsername, username).one();
        if (user == null) {
            return ResultBean.error("账号不存在");
        }
        if (!user.getAccountStatus().equals(AccountStatusEnum.INACTIVATED)) {
            return ResultBean.success("账号无须激活");
        }
        userService.sendActivationCode(user);
        return ResultBean.success("发送成功");
    }
}