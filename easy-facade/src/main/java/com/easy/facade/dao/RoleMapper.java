package com.easy.facade.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easy.facade.beans.model.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/16 14:38
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
