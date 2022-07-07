package com.easy.facade.controller;

import com.easy.facade.services.UserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
