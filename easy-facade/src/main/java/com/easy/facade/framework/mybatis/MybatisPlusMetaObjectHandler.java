package com.easy.facade.framework.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.easy.utils.date.DateUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * mybatis plus字段自动填充拦截
 * </p>
 *
 * @Author Matt
 * @Date 2021/4/19 13:45
 */
@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

    /**
     * 创建
     *
     * @param metaObject 拦截
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName("createTime", DateUtils.dateTimeNow(), metaObject);
        setFieldValByName("updateTime", DateUtils.dateTimeNow(), metaObject);
    }

    /**
     * 最后一次更新
     *
     * @param metaObject 拦截
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateTime", DateUtils.dateTimeNow(), metaObject);
    }
}
