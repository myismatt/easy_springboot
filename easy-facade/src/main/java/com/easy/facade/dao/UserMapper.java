package com.easy.facade.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easy.facade.beans.dto.UserUpdateDTO;
import com.easy.facade.beans.model.User;
import com.easy.facade.beans.vo.UserInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/16 14:28
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查询用户信息
     *
     * @param username username
     * @return User
     */
    UserInfoVO loadUserByUsername(@Param("username") String username);

    /**
     * 校验userKey
     *
     * @param userKeyList userKey集合
     * @return 存在的值
     */
    List<String> checkUserKey(@Param("userKeyList") List<String> userKeyList);

    /**
     * 根据userId查询菜单权限
     *
     * @param userId 用户id
     * @return List<String>
     */
    List<String> selectMenuByUserId(@Param("userId") String userId);

    /**
     * 根据用户id更新用户数据
     *
     * @param dto 入参
     */
    void updateUserInfoById(@Param("dto") UserUpdateDTO dto);
}