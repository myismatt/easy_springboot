package com.easy.facade.services;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easy.facade.beans.dto.RoleDTO;
import com.easy.facade.beans.model.Role;
import com.easy.facade.beans.model.RoleMenu;
import com.easy.facade.beans.vo.RoleInfoVO;
import com.easy.facade.beans.vo.RoleMenuVO;
import com.easy.facade.dao.RoleMapper;
import com.easy.facade.enums.MenuTypeEnum;
import com.easy.facade.framework.exception.CustomException;
import com.easy.utils.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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
            throw new CustomException("未查询到角色信息");
        }
        RoleInfoVO data = new RoleInfoVO();
        // 查询角色关联的菜单权限
        List<RoleMenuVO> roleMenuList = roleMenuService.getBaseMapper().selectRoleMenuByRoleId(role.getId());
        if (roleMenuList != null) {
            BeanUtils.copyProperties(role, data);
            // 构建树形结构
            data.setMenuList(menuTree(roleMenuList));
        }
        return data;
    }

    /**
     * 构建树形结构的菜单权限
     *
     * @param sourceList 菜单列表
     * @return RoleMenuVO树形
     */
    public List<RoleMenuVO> menuTree(List<RoleMenuVO> sourceList) {
        if (sourceList == null) {
            return null;
        }
        // 对权限按照类型进行分组
        Map<MenuTypeEnum, List<RoleMenuVO>> sourceListMap = sourceList.stream().collect(Collectors.groupingBy(RoleMenuVO::getMenuType));
        // 取出类型
        List<MenuTypeEnum> collect = new ArrayList<>(sourceListMap.keySet());
        MenuTypeEnum max = Collections.max(collect);
        return sourceListMap.get(max).stream().map(rootVO -> menuTreeByRoot(sourceList, rootVO)).collect(Collectors.toList());
    }

    /**
     * 根据顶级目录构建树形菜单权限
     *
     * @param sourceList 菜单列表
     * @param rootVO     顶级目录
     * @return RoleMenuVO树形
     */
    public RoleMenuVO menuTreeByRoot(List<RoleMenuVO> sourceList, RoleMenuVO rootVO) {
        if (sourceList == null) {
            return null;
        }
        List<RoleMenuVO> childList = sourceList.stream().filter(child -> child.getMenuId().equals(child.getParentId())).map(child -> menuTreeByRoot(sourceList, child)).collect(Collectors.toList());
        if (childList.size() == 0) {
            return rootVO;
        }
        rootVO.setChild(childList);
        return rootVO;
    }
}
