package com.easy.facade.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easy.facade.beans.model.DictType;
import com.easy.facade.beans.vo.DictTypeVO;
import com.easy.facade.enums.YesOrNoEnum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 字典类型
 * </p>
 *
 * @Author Matt
 * @Date 2022/7/18 16:40
 */
@Mapper
public interface DictTypeMapper extends BaseMapper<DictType> {
    /**
     * 查询字典类型
     *
     * @param dictType 字典类型
     * @return DictTypeVO
     */
    DictTypeVO selectDictTypeInfo(String dictType);

    /**
     * 查询所有有效字典类型
     *
     * @param enable 是否启用
     * @return List
     */
    List<DictTypeVO> selectAllDictType(@Param("enable") YesOrNoEnum enable);
}