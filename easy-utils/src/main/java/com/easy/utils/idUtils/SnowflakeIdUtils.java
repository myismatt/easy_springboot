package com.easy.utils.idUtils;

/**
 * 雪花算法ID生成
 *
 * @Author Matt
 * @Date 2022年06月28日
 */
public class SnowflakeIdUtils {

    private static final SnowflakeUtils ID_WORKER = new SnowflakeUtils();

    private SnowflakeIdUtils() {
    }

    public static SnowflakeIdUtils getInstance() {
        return InnerClass.INS;
    }

    public String getNextId() {
        return String.valueOf(ID_WORKER.nextId());
    }

    private static class InnerClass {
        private static final SnowflakeIdUtils INS = new SnowflakeIdUtils();

    }

}
