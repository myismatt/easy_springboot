package com.easy.facade.services;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easy.facade.beans.dto.RoleDTO;
import com.easy.facade.beans.model.Role;
import com.easy.facade.dao.RoleMapper;
import com.easy.facade.framework.exception.CustomException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 角色
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/16 14:38
 */
@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> {

    public void addRole(RoleDTO dto) {
        if (checkRoleKeyExists(dto.getRoleKey())) {
            throw new CustomException("角色代码存在");
        }
        Role newRole = new Role();
        BeanUtils.copyProperties(dto, newRole);
        save(newRole);
    }

    /**
     * 校验roleKey是否不存在
     *
     * @param roleKey roleKey
     * @return false 不存在;true 存在
     */
    public boolean checkRoleKeyExists(String roleKey) {
        Long keyCount = this.lambdaQuery().eq(Role::getRoleKey, roleKey).count();
        return keyCount > 0;
    }
}
