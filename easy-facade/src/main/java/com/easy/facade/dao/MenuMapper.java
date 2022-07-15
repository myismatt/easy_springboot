package com.easy.facade.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easy.facade.beans.model.Menu;
import com.easy.facade.beans.vo.MenuVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单路由
 * </p>
 *
 * @Author Matt
 * @Date 2022/7/7 14:40
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 根据角色ID获取菜单信息
     *
     * @param roleId 角色ID
     * @return 菜单信息
     */
    List<MenuVO> selectMenuByRoleId(@Param("roleId") String roleId);
}