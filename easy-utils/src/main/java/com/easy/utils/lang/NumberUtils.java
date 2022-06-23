package com.easy.utils.lang;

import java.util.List;

/**
 * 数字工具类
 * <p>
 * 2022/3/11 10:12
 *
 * @Author Matt
 */
public class NumberUtils extends org.apache.commons.lang3.math.NumberUtils {

    private NumberUtils() {
        throw new IllegalAccessError();
    }

    /**
     * 判断字符串数字是否是升序
     *
     * @param numbers 数字集合
     * @return true-是;false-否;
     */
    public static boolean isAscOrderNumber(List<Integer> numbers) {
        boolean flag = true;
        for (int i = 0; i < numbers.size(); i++) {
            if (i > 0) {
                int previousNumber = numbers.get(i);
                int lastNumber = numbers.get(i - 1) + 1;
                if (previousNumber != lastNumber) {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    /**
     * 判断字符串数字是否是降序
     *
     * @param numbers 数字集合
     * @return true-是;false-否;
     */
    public static boolean isDescOrderNumber(List<Integer> numbers) {
        boolean flag = true;
        for (int i = 0; i < numbers.size(); i++) {
            if (i > 0) {
                int previousNumber = numbers.get(i);
                int lastNumber = numbers.get(i - 1) - 1;
                if (previousNumber != lastNumber) {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }
}
