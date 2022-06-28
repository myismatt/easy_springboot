package com.easy.facade.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easy.facade.beans.dto.ConfigSearchDTO;
import com.easy.facade.beans.model.Config;
import com.easy.facade.beans.vo.ConfigVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 参数配置
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/16 14:30
 */
@Mapper
public interface ConfigMapper extends BaseMapper<Config> {

    /**
     * 查询config数据
     *
     * @param dto 查询参数
     * @return List<ConfigVO>
     */
    List<ConfigVO> selectConfigVO(@Param("dto") ConfigSearchDTO dto);
}
