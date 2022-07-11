package com.easy.facade.framework.security;

import com.easy.facade.beans.entity.LoginUserDetails;
import com.easy.utils.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * 权限校验
 * </p>
 *
 * @Author Matt
 * @Date 2022/7/11 11:27
 */
@Service("hasRole")
public class PermissionCheckService {


    /**
     * 验证用户是否具备某权限
     *
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     */
    public boolean check(String permission) {
        if (StringUtils.isEmpty(permission)) {
            return false;
        }
        LoginUserDetails loginUser = SecurityUtils.getLoginUserInfo();
        if (CollectionUtils.isEmpty(loginUser.getAuthorities())) {
            return false;
        }
        return hasPermissions(loginUser.getPermissions(), permission);
    }
}
