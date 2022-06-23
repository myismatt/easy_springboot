package com.easy.facade.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easy.facade.beans.model.UserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户角色关联
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/16 14:40
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
}
