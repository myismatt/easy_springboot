package com.easy.facade.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easy.facade.beans.model.Menu;
import org.apache.ibatis.annotations.Mapper;

/**
 * 菜单路由
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/16 14:41
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
}
