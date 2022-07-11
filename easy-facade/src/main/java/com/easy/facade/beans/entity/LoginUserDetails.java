package com.easy.facade.beans.entity;

import com.easy.facade.beans.vo.UserInfoVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * 登录用户信息
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/17 11:22
 */
@Data
@ApiModel(value = "登录用户信息")
public class LoginUserDetails implements UserDetails {

    @ApiModelProperty(value = "主键ID")
    private String id;

    /**
     * 用户特殊编码
     */
    @ApiModelProperty(value = "用户特殊编码")
    private String userKey;

    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    private String username;

    /**
     * 权限列表
     */
    @ApiModelProperty(value = "权限列表")
    private Set<String> permissions;

    /**
     * 用户信息
     */
    @ApiModelProperty(value = "用户信息")
    private UserInfoVO userInfo;


    public LoginUserDetails() {
    }

    public LoginUserDetails(String id, String userKey, String username, Set<String> permissions, UserInfoVO userInfo) {
        this.id = id;
        this.userKey = userKey;
        this.username = username;
        this.permissions = permissions;
        this.userInfo = userInfo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return userInfo.getPassword();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
