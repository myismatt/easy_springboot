package com.easy.facade.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easy.facade.beans.model.Config;
import com.easy.facade.dao.ConfigMapper;
import org.springframework.stereotype.Service;

/**
 * 参数配置
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/16 14:30
 */
@Service
public class ConfigService extends ServiceImpl<ConfigMapper, Config> {

}
