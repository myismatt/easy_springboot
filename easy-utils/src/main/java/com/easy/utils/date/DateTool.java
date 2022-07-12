package com.easy.utils.date;import cn.hutool.core.date.DateUtil;import org.apache.commons.lang3.time.DateUtils;import java.text.ParseException;import java.time.DayOfWeek;import java.time.LocalDate;import java.time.Period;import java.time.ZonedDateTime;import java.time.format.DateTimeFormatter;import java.util.Date;import java.util.Locale;/** * 日期工具 * </p> * * @Author Matt * @Date 2022/7/12 10:06 */public class DateTool extends DateUtil {    static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";    static final String DEFAULT_DATE_FORMAT_2 = "yyyy/MM/dd HH:mm:ss";    static final String DATE_FORMAT = "yyyy-MM-dd";    static final String DATE_PATH = "yyyy/MM/dd";    static final String DATE_NUM = "yyyyMMdd";    static final String MONTH_FORMAT = "yyyy-MM";    static final String MONTH_FORMAT_2 = "yyyy/MM";    static final String HOUR_MINUTE = "HH:mm";    static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";    static final String[] PARSE_PATTERNS =            {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss",                    "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};    /**     * 默认时间为 yyyy-MM-dd HH:mm:ss     */    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT, Locale.CHINA);    static ZonedDateTime zonedDateTime = ZonedDateTime.now();    static LocalDate localDate = LocalDate.now();    private DateTool() {        throw new IllegalAccessError("DateTool.class");    }    /**     * 返回现在的日期和时间     *     * @return yyyy-MM-dd HH:mm:ss     */    public static String now() {        return zonedDateTime.toLocalDateTime().format(formatter);    }    /**     * 获取当前年份     *     * @return int     */    public static int year() {        return zonedDateTime.getYear();    }    /**     * 获取当前月份     *     * @return int     */    public static int month() {        return zonedDateTime.getMonthValue();    }    /**     * 获取当前时间     *     * @return String HH:mm:ss     */    public static String time() {        return zonedDateTime.toLocalDateTime().format(formatter(DEFAULT_TIME_FORMAT));    }    /**     * 获取当前星期几     *     * @return DayOfWeek     */    public static DayOfWeek dayOfWeek() {        return zonedDateTime.getDayOfWeek();    }    /**     * 获取当前月份剩余天数     *     * @return int     */    public static int remainingDaysOfMonth() {        return Period.between(localDate, LocalDate.MAX).getDays();    }    /**     * 获取当前年月日     *     * @return 日期路径 即年/月/日 如2018/08/08     */    public static String datePath() {        return zonedDateTime.toLocalDateTime().format(formatter(DATE_PATH));    }    /**     * 获取当前年月日     *     * @return 日期数字  如20180808     */    public static String dateNum() {        return zonedDateTime.toLocalDateTime().format(formatter(DATE_NUM));    }    /**     * 日期型字符串转化为日期 格式     */    public static Date parseDate(Object str) throws ParseException {        if (str == null) {            return null;        }        return DateUtils.parseDate(str.toString(), PARSE_PATTERNS);    }    public static void main(String[] args) {        System.out.println(ZonedDateTime.now().getYear());        System.out.println(ZonedDateTime.now().getMonthValue());        System.out.println(ZonedDateTime.now().getDayOfWeek());        System.out.println(Period.between(localDate, LocalDate.MAX).getDays());        System.out.println(time());        System.out.println(datePath());        System.out.println(dateNum());    }    private static DateTimeFormatter formatter(String format) {        return DateTimeFormatter.ofPattern(format, Locale.CHINA);    }}