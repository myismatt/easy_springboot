package com.easy.facade.services;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easy.facade.beans.model.UserInfo;
import com.easy.facade.dao.UserInfoMapper;
import org.springframework.stereotype.Service;

/**
 * 账号详情信息
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/13 18:06
 */
@Service
public class UserInfoService extends ServiceImpl<UserInfoMapper, UserInfo> {

}


