package com.easy.facade.beans.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.easy.facade.enums.AccountStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

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
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 账号状态
     */
    @TableField(value = "account_status")
    @ApiModelProperty(value = "账号状态")
    private AccountStatusEnum accountStatus;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
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
