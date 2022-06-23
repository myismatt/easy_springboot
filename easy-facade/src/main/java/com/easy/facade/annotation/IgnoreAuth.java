package com.easy.facade.annotation;

import java.lang.annotation.*;

/**
 * 权限放行注解
 *
 * @Author Matt
 * @Date 2021/12/27 11:50
 */
//注解放置的目标位置,METHOD是可注解在方法级别上
@Target({ElementType.METHOD})
//注解在哪个阶段执行
@Retention(RetentionPolicy.RUNTIME)
//生成文档
@Documented
public @interface IgnoreAuth {
}
