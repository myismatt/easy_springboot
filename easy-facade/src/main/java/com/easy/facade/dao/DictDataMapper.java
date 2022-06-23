package com.easy.facade.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easy.facade.beans.model.DictData;
import org.apache.ibatis.annotations.Mapper;

/**
 * 字典数据
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/16 14:36
 */
@Mapper
public interface DictDataMapper extends BaseMapper<DictData> {
}
