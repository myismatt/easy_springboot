package com.easy.facade.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easy.facade.beans.entity.EmailActivationCodeMessage;
import com.easy.facade.beans.model.User;
import com.easy.facade.beans.model.UserInfo;
import com.easy.facade.constants.RedisListenerTopic;
import com.easy.facade.dao.UserMapper;
import com.easy.facade.enums.AccountStatusEnum;
import com.easy.facade.framework.redis.RedisUtils;
import com.easy.utils.idUtils.IdUtils;
import com.easy.utils.lang.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 账号数据
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/13 18:02
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    private UserInfoService userInfoService;
    private RedisUtils redisUtils;

    public UserService(UserInfoService userInfoService, RedisUtils redisUtils) {
        this.userInfoService = userInfoService;
        this.redisUtils = redisUtils;
    }

    /**
     * 账号是否存在;true不存在,false存在
     *
     * @param username 账号
     * @return boolean
     */
    public boolean accountIsNotExist(String username) {
        Long verifyAccount = this.lambdaQuery().eq(User::getUsername, username).count();
        return verifyAccount == 0;
    }

    /**
     * 注册账号
     *
     * @param username 账号
     * @param password 密码
     * @param email    邮箱
     */
    @Transactional(rollbackFor = Exception.class)
    public void registerNewUser(String username, String password, String email) {
        User newUser = new User();
        newUser.setUsername(username);
        // getUserKey() 获取一个不存在的userKey
        newUser.setUserKey(getUserKey());
        // 密码加密
        newUser.setPassword(new BCryptPasswordEncoder().encode(password));
        newUser.setEmail(email);
        newUser.setAccountStatus(AccountStatusEnum.NORMAL);
        // 保存用户
        save(newUser);
        UserInfo newUserInfo = new UserInfo();
        newUserInfo.setUserId(newUser.getId());
        // 随机码生成昵称
        newUserInfo.setNickname(username + IdUtils.generate8Code());
        // 保存用户详情
        userInfoService.save(newUserInfo);
        // 发送redis消息, 发送激活码邮件 @link EmailActivationCodeListener.class
        redisUtils.pushListenerMessage(RedisListenerTopic.EMAIL_ACTIVATION_CODE_TOPIC, new EmailActivationCodeMessage(newUserInfo.getUserId(), newUser.getUsername(), new String[]{newUser.getEmail()}));
    }

    /**
     * 获取一个不存在的userKey
     *
     * @return userKey
     */
    public String getUserKey() {
        List<String> userKeyList = new ArrayList<>();
        // 随机生成一百个码
        for (int i = 0; i < 100; i++) {
            userKeyList.add(IdUtils.generate8Code());
        }
        // 对100个码进行查重
        List<String> existingKey = this.baseMapper.checkUserKey(userKeyList);
        // 移除重复的码
        userKeyList.removeAll(existingKey);
        if (userKeyList.size() <= 0) {
            getUserKey();
        }
        return userKeyList.get(0);
    }

    /**
     * 根据用户id获取用户菜单权限
     *
     * @param userId 用户id
     * @return Set<String>
     */
    public Set<String> getUserMenu(String userId) {
        List<String> perms = this.baseMapper.selectMenuByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotEmpty(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }
}


