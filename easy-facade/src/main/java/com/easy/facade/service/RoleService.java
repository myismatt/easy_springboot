package com.easy.facade.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easy.facade.beans.model.Role;
import com.easy.facade.dao.RoleMapper;
import org.springframework.stereotype.Service;

/**
 * 角色
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/16 14:38
 */
@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> {

}
