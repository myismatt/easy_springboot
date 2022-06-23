package com.easy.facade.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easy.facade.beans.model.RoleMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色菜单关联
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/16 14:43
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
}
