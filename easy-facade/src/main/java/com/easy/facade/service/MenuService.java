package com.easy.facade.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easy.facade.beans.dto.MenuDTO;
import com.easy.facade.beans.dto.MenuSearchDTO;
import com.easy.facade.beans.model.Menu;
import com.easy.facade.dao.MenuMapper;
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
}

