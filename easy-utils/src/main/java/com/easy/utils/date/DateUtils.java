package com.easy.utils.date;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期处理
 * </p>
 *
 * @Author Matt
 * @Date 2021/4/7 10:32
 */
public final class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    public static String YYYY = "yyyy";
    public static String YYYY_MM = "yyyy-MM";
    public static String YYYY_MM_DD = "yyyy-MM-dd";
    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    private static String[] parsePatterns =
            {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss",
                    "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    private DateUtils() {
        throw new IllegalAccessError("DateUtils.class");
    }

    /**
     * 获取当前年份
     *
     * @return 例如:2021
     */
    public static String getThisYear() {
        return parseDateToStr(YYYY, new Date());
    }

    /**
     * 获取当前Date型日期
     *
     * @return Date() 当前日期
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     *
     * @return String
     */
    public static String getDate() {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static String getAllDate() {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS_SSS);
    }

    public static String getTime() {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static String dateTimeNow() {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static String dateAllTimeNow() {
        return dateTimeNow(YYYYMMDDHHMMSSSSS);
    }

    public static String dateTimeNow(final String format) {
        return parseDateToStr(format, new Date());
    }

    public static String dateTime(final Date date) {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static String parseDateToStr(final String format, final Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    public static Date dateTime(final String format, final String ts) {
        try {
            return new SimpleDateFormat(format).parse(ts);
        }
        catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static String datePath() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static String dateTime() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return DateUtils.parseDate(str.toString(), parsePatterns);
        }
        catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    public static String getNowTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(calendar.getTime());
    }

    /**
     * 获取当前年份
     */
    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        return Integer.parseInt(formatter.format(calendar.getTime()));
    }

    /**
     * 获取最近几年年份
     *
     * @param someYear 0 为今年。1 为去年 + 今年，以此类推
     */
    public static List<Integer> getLastSomeYear(int someYear) {

        return getLastSomeYear(someYear, getCurrentYear());
    }

    /**
     * 获取最近几年年份，根据指定年份倒推
     *
     * @param someYear 0 为今年。1 为去年 + 今年，以此类推
     */
    public static List<Integer> getLastSomeYear(int someYear, int targetYear) {

        if (someYear < 0) {
            return new ArrayList<>();
        }
        List<Integer> yearList = new ArrayList<>();
        for (int i = 0; i <= someYear; i++) {
            yearList.add(targetYear - i);
        }
        return yearList;
    }
}
