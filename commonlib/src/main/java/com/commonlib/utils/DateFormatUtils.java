package com.commonlib.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期格式化工具类
 */
public class DateFormatUtils {

    /**
     * 返回 MM-dd HH:mm 格式;
     *
     * @return
     */
    public static SimpleDateFormat getMMddHHmmFormat() {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm",
                Locale.CHINA);
        return format;
    }

    /**
     * 返回 HH:mm:ss 格式(24小时制);
     *
     * @return
     */
    public static SimpleDateFormat getHHmmssSSSChinaFormat() {
        SimpleDateFormat format = new SimpleDateFormat("HH时mm分ss.SSS秒",
                Locale.CHINA);
        return format;
    }

    /**
     * 返回 年-月-日 格式
     *
     * @return
     */
    public static SimpleDateFormat getyyyyMMddFormat() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd",
                Locale.CHINA);
        return format;
    }

    /**
     * 返回 时-分 格式(24小时制)
     *
     * @return
     */
    public static SimpleDateFormat getHHmmFormat() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.CHINA);
        return format;
    }

    /**
     * 返回 时-分 格式(12小时制)
     *
     * @return
     */
    public static SimpleDateFormat gethhmmFormat() {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm", Locale.CHINA);
        return format;
    }

    /**
     * 返回 yy-MM-dd HH:mm 格式时间(24小时制)
     *
     * @return
     */
    public static SimpleDateFormat getyyMMddHHmmFormat() {
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm",
                Locale.CHINA);
        return format;
    }

    /**
     * 返回 yyyy-MM-dd HH:mm格式时间(24小时制)
     *
     * @return
     */
    public static SimpleDateFormat getyyyyMMddHHmmFormat() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm",
                Locale.CHINA);
        return format;
    }

    /**
     * 返回 yyyy-MM-dd HH:mm:ss 格式(24小时制)
     *
     * @return
     */
    public static SimpleDateFormat getyyyyMMddHHmmssFormat() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.CHINA);
        return format;
    }

    /**
     * 返回 yyyy年M月d日 格式(汉字)
     *
     * @return
     */
    public static SimpleDateFormat getyyyyMdChinaFormat() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年M月d日",
                Locale.CHINA);
        return format;
    }

    /**
     * 返回 yyyy年M月d日 E 格式(加星期)
     *
     * @return
     */
    public static SimpleDateFormat getyyyyMdEChinaFormat() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年M月d日 E",
                Locale.CHINA);
        return format;
    }

    /**
     * 按照给定的格式将long类型时间戳格式化为String类型;
     *
     * @param sdf
     * @param time
     * @return
     */
    public static String long2StringByFormat(long time, SimpleDateFormat sdf) {
        Date dt = new Date(time);
        String sDateTime = sdf.format(dt);
        return sDateTime;
    }

    /**
     * 按照 yyyy-M-d 格式将long类型时间戳格式化为String类型;
     *
     * @param time
     * @return
     */
    public static String long2StringByYYYYMD(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-M-d", Locale.CHINA);
        Date dt = new Date(time);
        String sDateTime = format.format(dt);
        return sDateTime;
    }

    /**
     * 按照 yyyy年M月 格式将long类型时间戳格式化为String类型;
     *
     * @param time
     * @return
     */
    public static String long2StringByYYYYM(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年M月", Locale.CHINA);
        Date dt = new Date(time);
        String sDateTime = format.format(dt);
        return sDateTime;
    }

    /**
     * 按照 HH:mm 格式将long类型时间戳格式化为String类型;(24小时制)
     *
     * @param time
     * @return
     */
    public static String long2StringByhhmm(long time) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.CHINA);
        Date dt = new Date(time);
        String sDateTime = format.format(dt);
        return sDateTime;
    }

    /**
     * 按照所给格式将String类型时间戳格式化为data类型;
     *
     * @param sdf
     * @param time
     * @return
     */
    public static Date stringToDate(SimpleDateFormat sdf, String time) {
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            LogUtils.e("DateFormatUtils", e.getMessage());
        }
        return date;
    }


    /**
     * 2个时间是否相差1年
     *
     * @param startTime
     * @param endTime
     * @return true是
     */
    public static boolean isDifferOneYear(String startTime, String endTime,
                                          SimpleDateFormat format) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        try {
            cal1.setTime(format.parse(startTime));
            cal1.add(Calendar.YEAR, 1);
            cal2.setTime(format.parse(endTime));
            long diff = cal1.getTime().getTime() - cal2.getTime().getTime();
            return diff < 0 ? true : false;
        } catch (ParseException e) {
            LogUtils.e("DateFormatUtils", e.getMessage());
        }
        return true;
    }

    /**
     * 判断时间是否大于3天
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean isDiffer3Days(Date startTime, Date endTime) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(startTime);
        cal1.add(Calendar.DATE, 3);
        cal2.setTime(endTime);
        long diff = cal1.getTime().getTime() - cal2.getTime().getTime();
        return diff < 0 ? true : false;
    }

    /**
     * 按照所给格式将string类型格式化为long类型
     *
     * @param time
     * @param sdf
     * @return
     */
    public static long stringToLong(String time, SimpleDateFormat sdf) {
        return stringToDate(sdf, time).getTime();
    }

    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;
    private static final long ONE_WEEK = 604800000L;

    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";
    private static final String ONE_MONTH_AGO = "月前";
    private static final String ONE_YEAR_AGO = "年前";

    /**
     * 当前时间和所给时间的时间差
     *
     * @param date
     * @return
     */
    public static String formatTimeBefore(Long date) {
//			long delta = new Date().getTime() - date.getTime();
        long delta = System.currentTimeMillis() - date;
        if (delta < 1L * ONE_MINUTE) {
            long seconds = toSeconds(delta);
            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
        }
        if (delta < 60L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
        }
        if (delta < 24L * ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
        }
        if (delta < 48L * ONE_HOUR) {
            return "昨天";
        }
        if (delta < 30L * ONE_DAY) {
            long days = toDays(delta);
            return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
        }
        if (delta < 12L * 4L * ONE_WEEK) {
            long months = toMonths(delta);
            return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;
        } else {
            long years = toYears(delta);
            return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;
        }
    }

    /**
     * 毫秒转成秒
     *
     * @param date
     * @return
     */
    private static long toSeconds(long date) {
        return date / 1000L;
    }

    /**
     * 毫秒转成分钟
     *
     * @param date
     * @return
     */
    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    /**
     * 毫秒转成小时
     *
     * @param date
     * @return
     */
    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    /**
     * 毫秒转成天
     *
     * @param date
     * @return
     */
    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    /**
     * 毫秒转成月
     *
     * @param date
     * @return
     */
    private static long toMonths(long date) {
        return toDays(date) / 30L;
    }

    /**
     * 毫秒转成年
     *
     * @param date
     * @return
     */
    private static long toYears(long date) {
        return toMonths(date) / 365L;
    }


}
