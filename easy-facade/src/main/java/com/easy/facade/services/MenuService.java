package com.easy.facade.services;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easy.facade.beans.model.Menu;
import com.easy.facade.dao.MenuMapper;
import org.springframework.stereotype.Service;

/**
 * 菜单路由
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/16 14:41
 */
@Service
public class MenuService extends ServiceImpl<MenuMapper, Menu> {

}