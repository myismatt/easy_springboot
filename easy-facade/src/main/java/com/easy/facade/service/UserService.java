package com.easy.facade.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easy.facade.beans.dto.UserInfoDTO;
import com.easy.facade.beans.dto.UserSearchDTO;
import com.easy.facade.beans.dto.UserUpdateDTO;
import com.easy.facade.beans.entity.EmailActivationCodeMessage;
import com.easy.facade.beans.model.User;
import com.easy.facade.beans.model.UserInfo;
import com.easy.facade.beans.model.UserRole;
import com.easy.facade.beans.vo.UserInfoVO;
import com.easy.facade.constant.RedisListenerTopicConsts;
import com.easy.facade.dao.UserMapper;
import com.easy.facade.enums.AccountStatusEnum;
import com.easy.facade.framework.redis.RedisUtils;
import com.easy.utils.idUtils.IdUtils;
import com.easy.utils.lang.StringUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 账号数据
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/13 18:02
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    private final UserInfoService userInfoService;
    private final UserRoleService userRoleService;
    private final RedisUtils redisUtils;

    public UserService(UserInfoService userInfoService, UserRoleService userRoleService, RedisUtils redisUtils) {
        this.userInfoService = userInfoService;
        this.userRoleService = userRoleService;
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
        newUser.setAccountStatus(AccountStatusEnum.INACTIVATED);
        // 保存用户
        save(newUser);
        UserInfo newUserInfo = new UserInfo();
        newUserInfo.setUserId(newUser.getId());
        // 随机码生成昵称
        newUserInfo.setNickname(username + IdUtils.generate8Code());
        // 保存用户详情
        userInfoService.save(newUserInfo);
        // 发送redis消息, 发送激活码邮件 @link EmailActivationCodeListener.class
        redisUtils.pushListenerMessage(RedisListenerTopicConsts.EMAIL_ACTIVATION_CODE_TOPIC, new EmailActivationCodeMessage(newUserInfo.getUserId(), newUser.getUsername(), new String[]{newUser.getEmail()}));
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
        return perms.stream().filter(StringUtils::isNotEmpty).flatMap(perm -> Arrays.stream(perm.trim().split(","))).collect(Collectors.toSet());
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateUserInfo(UserUpdateDTO dto) {
        this.baseMapper.updateUserInfoById(dto);
        // 处理角色
        if (CollectionUtils.isNotEmpty(dto.getRoleIdList())) {
            List<UserRole> userRoleList = dto.getRoleIdList().parallelStream().map(roleId -> new UserRole(dto.getUserId(), roleId)).collect(Collectors.toList());
            userRoleService.saveBatch(userRoleList);
        }
    }

    public void sendActivationCode(User user) {
        // 发送redis消息, 发送激活码邮件 @link EmailActivationCodeListener.class
        redisUtils.pushListenerMessage(RedisListenerTopicConsts.EMAIL_ACTIVATION_CODE_TOPIC, new EmailActivationCodeMessage(user.getId(), user.getUsername(), new String[]{user.getEmail()}));
    }

    public void addUserInfo(UserInfoDTO dto) {
    }

    public List<UserInfoVO> getUserList(UserSearchDTO dto) {
        return getBaseMapper().getUserInfoList(dto);
    }

    public IPage<UserInfoVO> getUserPage(UserSearchDTO dto) {
        Page<UserInfoVO> page = new Page<>();
        page.setSize(dto.getSize());
        page.setCurrent(dto.getCurrent());
        page.setOptimizeCountSql(false);
        page.setRecords(getBaseMapper().getUserInfoPage(page, dto));
        return page;
    }
}