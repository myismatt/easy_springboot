package com.easy.utils.lang;

import com.easy.utils.exception.ExceptionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;

/**
 * 对象操作工具类，继承 org.apache.commons.lang3.ObjectUtils 类
 * </p>
 *
 * @Author Matt
 * @Date 2021/5/7 17:20
 */
public class ObjectUtils extends org.apache.commons.lang3.ObjectUtils {
    private ObjectUtils() {
        throw new IllegalAccessError("ObjectUtils.class");
    }

    /**
     * 转换为 Double 类型
     */
    public static Double toDouble(final Object val) {
        if (val == null) {
            return 0D;
        }
        try {
            return NumberUtils.toDouble(StringUtils.trim(val.toString()));
        }
        catch (Exception e) {
            return 0D;
        }
    }

    /**
     * 转换为 Float 类型
     */
    public static Float toFloat(final Object val) {
        return toDouble(val).floatValue();
    }

    /**
     * 转换为 Long 类型
     */
    public static Long toLong(final Object val) {
        return toDouble(val).longValue();
    }

    /**
     * 转换为 Integer 类型
     */
    public static Integer toInteger(final Object val) {
        return toLong(val).intValue();
    }

    /**
     * 转换为 Boolean 类型 'true', 'on', 'y', 't', 'yes' or '1'
     * (case insensitive) will return true. Otherwise, false is returned.
     */
    public static Boolean toBoolean(final Object val) {
        if (val == null) {
            return false;
        }
        return BooleanUtils.toBoolean(val.toString()) || "1".equals(val.toString());
    }

    /**
     * 转换为字符串
     */
    public static String toString(final Object obj) {
        return toString(obj, StringUtils.EMPTY);
    }

    /**
     * 转换为字符串，如果对象为空，则使用 defaultVal 值
     */
    public static String toString(final Object obj, final String defaultVal) {
        return obj == null ? defaultVal : obj.toString();
    }

    /**
     * 转换为字符串，忽略空值。如 null 字符串，则被认为空值，如下：
     * "" to "" ; null to "" ; "null" to "" ; "NULL" to "" ; "Null" to ""
     */
    public static String toStringIgnoreNull(final Object val) {
        return ObjectUtils.toStringIgnoreNull(val, StringUtils.EMPTY);
    }

    /**
     * 转换为字符串，忽略空值。如 null 字符串，则被认为空值，如下：
     * "" to defaultVal ; null to defaultVal ; "null" to defaultVal ; "NULL" to defaultVal ; "Null" to defaultVal
     */
    public static String toStringIgnoreNull(final Object val, String defaultVal) {
        String str = ObjectUtils.toString(val);
        return !"".equals(str) && !"null".equals(str.trim().toLowerCase()) ? str : defaultVal;
    }

    /**
     * 拷贝一个对象（但是子对象无法拷贝）
     *
     * @param source
     * @param ignoreProperties
     */
    public static Object copyBean(Object source, String... ignoreProperties) {
        if (source == null) {
            return null;
        }
        try {
            Object target = source.getClass().getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, target, ignoreProperties);
            return target;
        }
        catch (InstantiationException | IllegalAccessException | IllegalArgumentException
               | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            throw ExceptionUtils.unchecked(e);
        }
    }

    /**
     * 序列化对象
     *
     * @param object
     * @return
     */
    public static byte[] serializeJava(Object object) {
        if (object == null) {
            return null;
        }
        long beginTime = System.currentTimeMillis();
        byte[] bytes = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos);) {
            oos.writeObject(object);
            bytes = baos.toByteArray();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        long totalTime = System.currentTimeMillis() - beginTime;
        if (totalTime > 3000) {
            throw new RuntimeException(object.getClass() + " serialize time: " + totalTime);
        }
        return bytes;
    }

    /**
     * 反序列化对象
     *
     * @param bytes
     * @return
     */
    public static Object unserializeJava(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        long beginTime = System.currentTimeMillis();
        Object object = null;
        if (bytes.length > 0) {
            try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                 ObjectInputStream ois = new ObjectInputStream(bais);) {
                object = ois.readObject();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        long totalTime = System.currentTimeMillis() - beginTime;
        if (totalTime > 3000) {
            throw new RuntimeException(object.getClass() + " unSerialize time: " + totalTime);
        }
        return object;
    }
}
