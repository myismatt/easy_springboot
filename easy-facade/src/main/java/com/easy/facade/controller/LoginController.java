package com.easy.facade.controller;

import com.easy.facade.beans.base.ResultBean;
import com.easy.facade.beans.dto.LoginParamDTO;
import com.easy.facade.beans.entity.TokenInfo;
import com.easy.facade.beans.vo.MenuVO;
import com.easy.facade.beans.vo.UserInfoVO;
import com.easy.facade.framework.security.SecurityUtils;
import com.easy.facade.service.LoginService;
import com.easy.facade.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

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
    private final MenuService menuService;

    public LoginController(LoginService loginService, MenuService menuService) {
        this.loginService = loginService;
        this.menuService = menuService;
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
    @GetMapping(value = "user_info", headers = "Authorization")
    @ApiOperation(value = "获取用户信息", httpMethod = "GET")
    public ResultBean<UserInfoVO> gtUserInfo() {
        return ResultBean.success(loginService.getUserInfo());
    }

    /**
     * 获取用户菜单
     *
     * @return List<RoleMenuVO>
     */
    @GetMapping(value = "user_menu", headers = "Authorization")
    @ApiOperation(value = "获取用户菜单", httpMethod = "GET")
    public ResultBean<List<MenuVO>> getUserMenu() {
        return ResultBean.success("查询成功", menuService.getUserMenu(SecurityUtils.getLoginUserId()));
    }
}