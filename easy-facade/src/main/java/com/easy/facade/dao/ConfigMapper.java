package com.easy.facade.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easy.facade.beans.model.Config;
import org.apache.ibatis.annotations.Mapper;

/**
 * 参数配置
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/16 14:30
 */
@Mapper
public interface ConfigMapper extends BaseMapper<Config> {
}
