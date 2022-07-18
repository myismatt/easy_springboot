package com.easy.facade.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easy.facade.beans.model.DictData;
import com.easy.facade.beans.vo.DictDataVO;
import com.easy.facade.enums.YesOrNoEnum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 字典数据
 * </p>
 *
 * @Author Matt
 * @Date 2022/7/18 16:40
 */
@Mapper
public interface DictDataMapper extends BaseMapper<DictData> {
    /**
     * 获取所有字典数据
     *
     * @param enable 是否启用
     * @return List<DictDataVO>
     */
    List<DictDataVO> selectAllDictData(@Param("enable") YesOrNoEnum enable);
}