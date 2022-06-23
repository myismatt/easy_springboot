package com.easy.facade.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easy.facade.beans.model.DictType;
import org.apache.ibatis.annotations.Mapper;

/**
 * 字典类型
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/16 14:35
 */
@Mapper
public interface DictTypeMapper extends BaseMapper<DictType> {
}
