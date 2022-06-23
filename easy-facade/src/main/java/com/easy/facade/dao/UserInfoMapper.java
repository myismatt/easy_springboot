package com.easy.facade.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easy.facade.beans.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户详细数据
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/16 14:29
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
