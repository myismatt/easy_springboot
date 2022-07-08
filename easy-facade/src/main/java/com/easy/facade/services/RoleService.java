package com.easy.facade.services;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easy.facade.beans.dto.RoleDTO;
import com.easy.facade.beans.model.Role;
import com.easy.facade.beans.model.RoleMenu;
import com.easy.facade.dao.RoleMapper;
import com.easy.facade.framework.exception.CustomException;
import com.easy.utils.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/16 14:38
 */
@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> {

    private final RoleMenuService roleMenuService;

    public RoleService(RoleMenuService roleMenuService) {
        this.roleMenuService = roleMenuService;
    }

    /**
     * 新增角色
     *
     * @param dto 入参
     */
    @Transactional(rollbackFor = Exception.class)
    public void addRole(RoleDTO dto) {
        if (checkRoleKeyExists(dto.getRoleKey())) {
            throw new CustomException("角色代码存在");
        }
        Role newRole = new Role();
        BeanUtils.copyProperties(dto, newRole);
        save(newRole);
        if (StringUtils.isNotEmpty(dto.getMenuIdList())) {
            List<RoleMenu> roleMenuList = new ArrayList<>();
            dto.getMenuIdList().parallelStream().forEach(k -> {
                roleMenuList.add(new RoleMenu(k, newRole.getId()));
            });
            roleMenuService.saveBatch(roleMenuList);
        }
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
