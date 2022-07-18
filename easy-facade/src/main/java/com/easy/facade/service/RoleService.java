package com.easy.facade.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easy.facade.beans.dto.RoleDTO;
import com.easy.facade.beans.model.Role;
import com.easy.facade.beans.model.RoleMenu;
import com.easy.facade.beans.vo.RoleInfoVO;
import com.easy.facade.beans.vo.RoleMenuVO;
import com.easy.facade.dao.RoleMapper;
import com.easy.facade.framework.exception.CustomizeException;
import com.easy.facade.util.MenuUtils;
import com.easy.utils.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/16 14:38
 */
@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> {

    private static final Logger logger = LoggerFactory.getLogger(RoleService.class);

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
            throw new CustomizeException("角色代码存在");
        }
        Role newRole = new Role();
        BeanUtils.copyProperties(dto, newRole);
        save(newRole);
        if (StringUtils.isNotEmpty(dto.getMenuIdList())) {
            List<RoleMenu> roleMenuList = dto.getMenuIdList().parallelStream().map(menuId -> new RoleMenu(newRole.getId(), menuId)).collect(Collectors.toList());
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

    /**
     * 获取角色信息
     *
     * @param roleKey 角色关键词
     * @return RoleInfoVO
     */
    public RoleInfoVO getRoleInfo(String roleKey) {
        // 查询角色信息
        Role role = lambdaQuery().eq(Role::getRoleKey, roleKey).one();
        if (role == null) {
            throw new CustomizeException("未查询到角色信息");
        }
        RoleInfoVO data = new RoleInfoVO();
        // 查询角色关联的菜单权限
        List<RoleMenuVO> roleMenuList = roleMenuService.getBaseMapper().selectRoleMenuByRoleId(role.getId());
        if (roleMenuList != null) {
            BeanUtils.copyProperties(role, data);
            // 构建树形结构
            data.setMenuList(MenuUtils.roleMenuTree(roleMenuList));
        }
        return data;
    }


}