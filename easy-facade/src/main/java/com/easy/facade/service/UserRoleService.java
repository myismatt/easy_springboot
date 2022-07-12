package com.easy.facade.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easy.facade.beans.model.UserRole;
import com.easy.facade.dao.UserRoleMapper;
import org.springframework.stereotype.Service;

/**
 * 用户角色
 * </p>
 *
 * @Author Matt
 * @Date 2022/7/12 14:57
 */
@Service
public class UserRoleService extends ServiceImpl<UserRoleMapper, UserRole> {
}