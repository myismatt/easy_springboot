package com.easy.utils.idUtils;

import java.util.Random;

/**
 * ID生成器工具类
 * </p>
 *
 * @Author Matt
 * @Date 2021/5/19 10:09
 */
public class IdUtils {
    /**
     * 自定义进制(0,1没有加入,容易与o,l混淆)
     */
    private static final char[] CUSTOM_BASE =
            new char[]{'a', 'd', 'f', 'h', 'j', '2', 'l', 'm', 'n', 'p', 'i', 'r', 's', 't', '7', 'u', 'v', 'b', 'w', 'x',
                    'r', 'y', 'z', 'Q', 'W', 'e', 'E', '8', 'A', 'S', 'D', 'Z', 'X', '9', 'C', 'P', '5', 'I', 'K', '3', 'M',
                    'J', 'U', 'g', 'F', 'k', 'R', '4', 'V', 'Y', 'c', 'l', 'T', 'N', '6', 'q', 'B', 'G', 'H'};
    /**
     * 不能与自定义进制有重复
     */
    private static final char SPECIAL = 'O';
    /**
     * 进制长度
     */
    private static final int BIN_LEN = CUSTOM_BASE.length;
    /**
     * 序列最小长度
     */
    private static final int SMALLEST = 12;
    public static String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
            "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    private IdUtils() {
        throw new IllegalAccessError("IdUtils.class");
    }

    /**
     * 获取随机UUID
     *
     * @return 随机UUID
     */
    public static String randomUuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线
     *
     * @return 简化的UUID，去掉了横线
     */
    public static String simpleUuid() {
        return UUID.randomUUID().toString(true);
    }

    /**
     * 获取随机UUID，使用性能更好的ThreadLocalRandom生成UUID
     *
     * @return 随机UUID
     */
    public static String fastUuid() {
        return UUID.fastUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线，使用性能更好的ThreadLocalRandom生成UUID
     *
     * @return 简化的UUID，去掉了横线
     */
    public static String fastSimpleUuid() {
        return UUID.fastUUID().toString(true);
    }


    /**
     * 生成十二位随机码
     *
     * @return 随机码
     */
    public static String generate12Code() {
        // 生成id
        long id = codeToId(generate8Code());
        char[] buf = new char[32];
        int charPos = 32;

        while ((id / BIN_LEN) > 0) {
            int ind = (int) (id % BIN_LEN);
            buf[--charPos] = CUSTOM_BASE[ind];
            id /= BIN_LEN;
        }
        buf[--charPos] = CUSTOM_BASE[(int) (id % BIN_LEN)];
        String str = new String(buf, charPos, (32 - charPos));
        // 不够长度的自动随机补全
        if (str.length() < SMALLEST) {
            StringBuilder sb = new StringBuilder();
            sb.append(SPECIAL);
            Random rnd = new Random();
            for (int i = 1; i < SMALLEST - str.length(); i++) {
                sb.append(CUSTOM_BASE[rnd.nextInt(BIN_LEN)]);
            }
            str += sb.toString();
        }
        return str;
    }

    public static long codeToId(String code) {
        char[] chs = code.toCharArray();
        long res = 0L;
        for (int i = 0; i < chs.length; i++) {
            int ind = 0;
            for (int j = 0; j < BIN_LEN; j++) {
                if (chs[i] == CUSTOM_BASE[j]) {
                    ind = j;
                    break;
                }
            }
            if (chs[i] == SPECIAL) {
                break;
            }
            if (i > 0) {
                res = res * BIN_LEN + ind;
            }
            else {
                res = ind;
            }
        }
        return res;
    }

    /**
     * 生成8位随机密码
     *
     * @return 8位随机密码
     */
    public static String generate8Code() {
        StringBuilder shortBuffer = new StringBuilder();
        String uuid = java.util.UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }

    /**
     * 获取五以内随机数(包含5)
     *
     * @return int
     */
    public static int randomFive() {
        return (int) (1 + Math.random() * (6 - 1 + 1));
    }
}
