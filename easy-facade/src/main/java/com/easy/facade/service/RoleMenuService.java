package com.easy.facade.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easy.facade.beans.model.RoleMenu;
import com.easy.facade.dao.RoleMenuMapper;
import org.springframework.stereotype.Service;

/**
 * 角色菜单权限
 * </p>
 *
 * @Author Matt
 * @Date 2022/7/8 10:54
 */
@Service
public class RoleMenuService extends ServiceImpl<RoleMenuMapper, RoleMenu> {
}
