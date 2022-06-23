package com.easy.utils.bean;

import java.lang.reflect.Method;
import java.util.List;

/**
 * bean工具
 * </p>
 *
 * @Author Matt
 * @Date 2021/9/3 21:42
 */
public final class BeanUtils {

    private BeanUtils() {
        throw new IllegalAccessError("BeanUtils.class");
    }

    /**
     * 将dto和entity之间的属性互相转换,dto中属性一般为String等基本类型,
     * <p>
     * 但是entity中可能有复合主键等复杂类型,需要注意同名问题
     *
     * @param src    转换前对象
     * @param target 转换后对象
     */
    public static void populate(Object src, Object target) {
        Method[] srcMethods = src.getClass().getMethods();
        Method[] targetMethods = target.getClass().getMethods();
        for (Method srcMethod : srcMethods) {
            String srcName = srcMethod.getName();
            if (srcName.startsWith("get")) {
                try {
                    for (Method targetMethod : targetMethods) {
                        String targetName = targetMethod.getName();
                        if (targetName.startsWith("set") && targetName.substring(3).equals(srcName.substring(3))) {
                            Object result = srcMethod.invoke(src);
                            targetMethod.invoke(target, result);
                        }
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    // 某个方法反射异常
                    throw new RuntimeException("populate fail");
                }
            }
        }

    }

    /**
     * dto集合和实体类集合间的互相属性映射
     *
     * @param src         转换前对象
     * @param target      目标对象
     * @param targetClass 目标对象Class
     * @return 目标对象
     */
    @SuppressWarnings("unchecked")
    public static <S, T> List<T> populateList(List<S> src, List<T> target, Class<?> targetClass) {
        for (S s : src) {
            try {
                Object object = targetClass.getDeclaredConstructor().newInstance();
                target.add((T) object);
                populate(s, object);
            }
            catch (Exception e) {
                // 某个方法反射异常
                throw new RuntimeException("populateList fail");
            }
        }
        return target;
    }

    /**
     * 根据属性名获取属性值
     *
     * @param fieldName 属性名字
     * @param o         对象
     * @return Object
     */
    public static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter);
            return method.invoke(o);
        }
        catch (Exception e) {
            return null;
        }
    }
}
