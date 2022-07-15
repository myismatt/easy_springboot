package com.easy.facade.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easy.facade.beans.model.AreaInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 行政区划
 * </p>
 *
 * @Author Matt
 * @Date 2022/7/14 14:46
 */
@Mapper
public interface AreaInfoMapper extends BaseMapper<AreaInfo> {
}