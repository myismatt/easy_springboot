package com.easy.facade.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easy.facade.beans.model.RoleMenu;
import com.easy.facade.beans.vo.RoleMenuVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色菜单关联
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/16 14:43
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    /**
     * 根据角色ID获取角色菜单权限
     *
     * @param roleId 角色id
     * @return List<RoleMenuVO>
     */
    List<RoleMenuVO> selectRoleMenuByRoleId(@Param("roleId") String roleId);
}
