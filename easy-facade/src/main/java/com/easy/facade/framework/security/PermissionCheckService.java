package com.easy.facade.framework.security;

import com.easy.facade.beans.entity.LoginUserDetails;
import com.easy.facade.beans.vo.RoleInfoVO;
import com.easy.facade.constant.SystemConsts;
import com.easy.utils.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Objects;
import java.util.Set;

/**
 * 权限校验
 * </p>
 *
 * @Author Matt
 * @Date 2022/7/11 11:27
 */
@Service("auth")
public class PermissionCheckService {


    /**
     * 验证用户是否具备某权限
     *
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     */
    public boolean hasKey(String permission) {
        if (StringUtils.isEmpty(permission)) {
            return false;
        }
        // 获取登录信息
        LoginUserDetails loginUser = SecurityUtils.getLoginUserInfo();
        if (Objects.equals(loginUser.getUserKey(), SystemConsts.ADMIN_KEY)) {
            return true;
        }
        if (CollectionUtils.isEmpty(loginUser.getPermissions())) {
            return false;
        }
        return hasPermissions(loginUser.getPermissions(), permission);
    }

    /**
     * 判断是否包含权限
     *
     * @param permissions 权限列表
     * @param permission  权限字符串
     * @return 用户是否具备某权限
     */
    private boolean hasPermissions(Set<String> permissions, String permission) {
        return permissions.contains(StringUtils.trim(permission));
    }

    /**
     * 判断用户是否拥有某个角色
     *
     * @param role 角色字符串
     * @return 用户是否具备某角色
     */
    public boolean hasRole(String role) {
        if (StringUtils.isEmpty(role)) {
            return false;
        }
        LoginUserDetails loginUser = SecurityUtils.getLoginUserInfo();
        if (Objects.equals(loginUser.getUserKey(), SystemConsts.ADMIN_KEY)) {
            return true;
        }
        if (CollectionUtils.isEmpty(loginUser.getUserInfo().getRoleList())) {
            return false;
        }
        return loginUser.getUserInfo().getRoleList().stream().map(RoleInfoVO::getRoleKey).anyMatch(roleKey -> roleKey.equals(StringUtils.trim(role)));
    }

}