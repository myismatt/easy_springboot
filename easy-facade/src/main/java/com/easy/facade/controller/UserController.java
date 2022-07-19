package com.easy.facade.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.easy.facade.beans.base.ResultBean;
import com.easy.facade.beans.dto.UserInfoDTO;
import com.easy.facade.beans.dto.UserSearchDTO;
import com.easy.facade.beans.dto.UserUpdateDTO;
import com.easy.facade.beans.vo.UserInfoVO;
import com.easy.facade.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    /**
     * 校验账号是否存在
     *
     * @param username 用户名字
     * @return true不存在, false存在
     */
    @GetMapping("check_username/{username}")
    @ApiOperation(value = "校验账号是否存在", httpMethod = "GET")
    public ResultBean<Boolean> checkUsername(@PathVariable String username) {
        return ResultBean.success("操作成功", userService.accountIsNotExist(username));
    }

    /**
     * 查询用户列表
     *
     * @param dto 查询参数
     * @return 结合列表
     */
    @GetMapping(value = "query", headers = "Authorization")
    @PreAuthorize("@auth.hasKey('admin:user:query')")
    @ApiOperation(value = "查询用户信息集合", httpMethod = "GET")
    public ResultBean<List<UserInfoVO>> getUserList(UserSearchDTO dto) {
        return ResultBean.success("查询成功", userService.getUserList(dto));
    }

    /**
     * 查询用户列表
     *
     * @param dto 查询参数
     * @return 结合列表
     */
    @GetMapping(value = "page", headers = "Authorization")
    @PreAuthorize("@auth.hasKey('admin:user:page')")
    @ApiOperation(value = "查询用户信息分页", httpMethod = "GET")
    public ResultBean<IPage<UserInfoVO>> getUserPage(UserSearchDTO dto) {
        return ResultBean.success("查询成功", userService.getUserPage(dto));
    }

    /**
     * 新增用户
     *
     * @param dto 入参
     * @return 操作结果
     */
    @PostMapping(value = "add", headers = "Authorization")
    @PreAuthorize("@auth.hasKey('admin:user:add')")
    @ApiOperation(value = "新增", httpMethod = "POST")
    public ResultBean<String> addUserInfo(@Valid @RequestBody UserInfoDTO dto) {
        userService.addUserInfo(dto);
        return ResultBean.success("操作成功");
    }


    /**
     * 更新用户信息
     *
     * @param dto 入参
     * @return 操作结果
     */
    @PutMapping(value = "update", headers = "Authorization")
    @PreAuthorize("@auth.hasKey('admin:user:update')")
    @ApiOperation(value = "更新", httpMethod = "PUT")
    public ResultBean<String> updateUserInfo(@Valid @RequestBody UserUpdateDTO dto) {
        userService.updateUserInfo(dto);
        return ResultBean.success("操作成功");
    }

    /**
     * 重置用户密码
     *
     * @param userIds 用户id集合
     * @return 操作结果
     */
    @PostMapping(value = "reset_pwd", headers = "Authorization")
    @PreAuthorize("@auth.hasKey('admin:user:reset_pwd')")
    @ApiOperation(value = "重置用户密码", httpMethod = "POST")
    public ResultBean<String> resetUserPassword(@RequestBody List<String> userIds) {
        userService.resetUserPassword(userIds);
        return ResultBean.success("操作成功");
    }
}