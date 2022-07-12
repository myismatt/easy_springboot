package com.easy.facade.controller;

import com.easy.facade.beans.base.ResultBean;
import com.easy.facade.beans.dto.LoginParamDTO;
import com.easy.facade.beans.entity.TokenInfo;
import com.easy.facade.beans.vo.UserInfoVO;
import com.easy.facade.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 登录
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/24 16:42
 */
@RestController
@Api(tags = "登录")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 登录接口
     *
     * @param dto 登录参数
     * @return TokenInfo
     */
    @PostMapping("login")
    @ApiOperation(value = "登录", httpMethod = "POST")
    public ResultBean<TokenInfo> login(@Valid @RequestBody LoginParamDTO dto) throws Exception {
        return ResultBean.success(loginService.login(dto));
    }

    /**
     * 获取用户信息
     *
     * @return UserInfoVO
     */
    @GetMapping(value = "userInfo", headers = "Authorization")
    @ApiOperation(value = "获取用户信息", httpMethod = "GET")
    public ResultBean<UserInfoVO> gtUserInfo() {
        return ResultBean.success(loginService.getUserInfo());
    }
}