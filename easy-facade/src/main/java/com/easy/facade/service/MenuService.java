package com.easy.facade.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easy.facade.beans.dto.MenuDTO;
import com.easy.facade.beans.dto.MenuSearchDTO;
import com.easy.facade.beans.model.Menu;
import com.easy.facade.beans.model.UserRole;
import com.easy.facade.beans.vo.MenuVO;
import com.easy.facade.dao.MenuMapper;
import com.easy.facade.framework.exception.CustomizeException;
import com.easy.facade.util.MenuUtils;
import com.easy.utils.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单路由
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/16 14:41
 */
@Service
public class MenuService extends ServiceImpl<MenuMapper, Menu> {
    private final UserRoleService userRoleService;

    public MenuService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    public void addMenu(MenuDTO dto) {
        Menu newMenu = new Menu();
        BeanUtils.copyProperties(dto, newMenu);
        save(newMenu);
    }

    public IPage<Menu> getPage(MenuSearchDTO dto) {
        return lambdaQuery()
                .like(StringUtils.isNotBlank(dto.getMenuName()), Menu::getMenuName, dto.getMenuName())
                .like(StringUtils.isNotBlank(dto.getPath()), Menu::getPath, dto.getPath())
                .like(StringUtils.isNotBlank(dto.getComponent()), Menu::getComponent, dto.getComponent())
                .like(StringUtils.isNotBlank(dto.getPerms()), Menu::getPerms, dto.getPerms())
                .eq(StringUtils.isNotNull(dto.getMenuType()), Menu::getMenuType, dto.getMenuType())
                .page(new Page<Menu>().setCurrent(dto.getCurrent()).setSize(dto.getSize()));
    }

    public List<Menu> getList(MenuSearchDTO dto) {
        return lambdaQuery()
                .like(StringUtils.isNotBlank(dto.getMenuName()), Menu::getMenuName, dto.getMenuName())
                .like(StringUtils.isNotBlank(dto.getPath()), Menu::getPath, dto.getPath())
                .like(StringUtils.isNotBlank(dto.getComponent()), Menu::getComponent, dto.getComponent())
                .like(StringUtils.isNotBlank(dto.getPerms()), Menu::getPerms, dto.getPerms())
                .eq(StringUtils.isNotNull(dto.getMenuType()), Menu::getMenuType, dto.getMenuType())
                .list();
    }

    /**
     * 获取用户菜单权限列表
     *
     * @param userId 用户id
     * @return List<MenuVO>
     */
    public List<MenuVO> getUserMenu(String userId) {

        UserRole userRole = userRoleService.lambdaQuery().eq(UserRole::getUserId, userId).one();
        if (userRole == null) {
            throw new CustomizeException("未查询到角色信息");
        }
        // 查询角色关联的菜单权限
        List<MenuVO> roleMenuList = this.getBaseMapper().selectMenuByRoleId(userRole.getRoleId());
        return MenuUtils.menuTree(roleMenuList);
    }
}