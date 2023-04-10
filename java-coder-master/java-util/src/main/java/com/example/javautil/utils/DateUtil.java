package com.example.javautil.utils;


import org.apache.commons.lang3.time.DateFormatUtils;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 日期辅助类
 */
public class DateUtil {

    /**
     *
     */
    private static final ThreadLocal<SimpleDateFormat> SDF_YYYY_MM_DD_DATA_FORMATTER = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };
    public static final String DATE_PATTERN_HH_MM_SS = "HH:mm:ss";
    public static final String DATE_PATTERN_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DATE_PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN_YYYY_MM_DD_HH_MM_SS_1 = "yyyy/MM/dd HH:mm:ss";
    public static final String DATE_PATTERN_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String DATE_PATTERN_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String DATE_PATTERN_YYYYMMDDHHMMSS_SSS = "yyyyMMddHHmmssSSS";
    public static final String DATE_PATTERN_MONTH_DAY = "M月d日";
    public static final String DATE_PATTERN_HH_MM = "HH:mm";
    public static final String DATE_PATTERN_MONTH_DAY_HH_MM = "MM月dd日 HH:mm";
    public static final String DATE_PATTERN_YEAR_MONTH_DAY = "yyyy年MM月dd日";
    public static final String DATE_PATTERN_YEAR_MONTH_DAY_HH_MM = "yyyy年MM月dd日 HH:mm";

    public static String format(Date date, String format) {
        return DateFormatUtils.format(date, format);
    }

    /**
     *
     */
    private static final ThreadLocal<DateTimeFormatter> DTF_YYYY_MM_DD_DATA_FORMATTER = new ThreadLocal<DateTimeFormatter>() {
        @Override
        protected DateTimeFormatter initialValue() {
            return DateTimeFormatter.ofPattern("yyyy-MM-dd");
        }
    };

    private static final ThreadLocal<DateTimeFormatter> DTF_YYYYMMDD_DATA_FORMATTER = new ThreadLocal<DateTimeFormatter>() {
        @Override
        protected DateTimeFormatter initialValue() {
            return DateTimeFormatter.ofPattern("yyyyMMdd");
        }
    };

    private static final ThreadLocal<DateTimeFormatter> DTF_YYYY_MM_DD_HH_MM_SS_DATA_FORMATTER = new ThreadLocal<DateTimeFormatter>() {
        @Override
        protected DateTimeFormatter initialValue() {
            return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        }
    };

    private static final ThreadLocal<DateTimeFormatter> DTF_HH_MM_SS_DATA_FORMATTER = new ThreadLocal<DateTimeFormatter>() {
        @Override
        protected DateTimeFormatter initialValue() {
            return DateTimeFormatter.ofPattern("HH:mm:ss");
        }
    };

    private static final ThreadLocal<DateTimeFormatter> DATE_PATTERN_YYYYMMDDHHMMSS_SSS_FORMATTER = new ThreadLocal<DateTimeFormatter>() {
        @Override
        protected DateTimeFormatter initialValue() {
            return DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDDHHMMSS_SSS);
        }
    };

    /**
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String date) throws ParseException {
        SimpleDateFormat format = SDF_YYYY_MM_DD_DATA_FORMATTER.get();
        return format.parse(date);
    }

    /**
     * @return
     * @throws ParseException
     */
    public static String formatCurrentTimeToString() {

        DateTimeFormatter format = DATE_PATTERN_YYYYMMDDHHMMSS_SSS_FORMATTER.get();
        LocalDateTime ldt = LocalDateTime.now();
        return ldt.format(format);
    }

    /**
     * @param date 2019-05-31
     * @return 20190531
     */
    public static String transferDate(String date) {
        if (10 != date.length()) {
            return null;
        }
        String year = date.substring(0, 4);
        String month = date.substring(5, 7);
        String day = date.substring(8);
        StringBuilder sb = new StringBuilder();
        return sb.append(year).append(month).append(day).toString();
    }

    /**
     * localdatetime -> milli
     *
     * @param ldt
     * @return
     */
    public static Long formatLocalDateTimeToMilli(LocalDateTime ldt) {

        return ldt.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * localdate -> milli
     *
     * @param ldt
     * @return
     */
    public static Long formatLocalDateToMilli(LocalDate ldt) {

        return ldt.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * LocalDateTime -> string 2019-00-00 00:00:00
     *
     * @param ldt
     * @return
     */
    public static String formatLocalDateTimeToString(LocalDateTime ldt) {

        return ldt.format(DTF_YYYY_MM_DD_HH_MM_SS_DATA_FORMATTER.get());
    }

    /**
     * timestamp -> string 2019-00-00 00:00:00
     *
     * @param timestamp
     * @return
     */
    public static String formatTimestampToString(Timestamp timestamp) {

        return timestamp.toLocalDateTime().format(DTF_YYYY_MM_DD_HH_MM_SS_DATA_FORMATTER.get());
    }

    /**
     * LocalDateTime -> string 2019-00-00
     *
     * @param ldt
     * @return
     */
    public static String formatLocalDateTimeToShortString(LocalDateTime ldt) {

        return ldt.format(DTF_YYYY_MM_DD_DATA_FORMATTER.get());
    }

    /**
     * LocalDateTime -> string 2019-00-00 11:00:00
     *
     * @param ldt
     * @return
     */
    public static String formatLocalDateTimeToHourString(LocalDateTime ldt) {

        int hour = ldt.getHour();
        StringBuilder sb = new StringBuilder(ldt.toLocalDate().toString());
        sb.append(" ");
        if (hour < 10) {
            sb.append("0");
        }
        sb.append(hour).append(":00:00");
        return sb.toString();
    }

    /**
     * string 转成毫秒
     *
     * @param date
     * @return
     */
    public static Long formatStringToMilli(String date) {

        LocalDateTime ldt = LocalDateTime.parse(date, DTF_YYYY_MM_DD_HH_MM_SS_DATA_FORMATTER.get());
        return formatLocalDateTimeToMilli(ldt);
    }

    /**
     * 服务器时区
     **/
    public static final String TIME_ZONE_SHANGHAI = "Asia/Shanghai";

    /**
     * 生成日期字符串
     *
     * @param date    日期
     * @param pattern 模板
     * @return
     */
    public static String date2String(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }


    public static Date string2Date(String dateString, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        ParsePosition pos = new ParsePosition(0);
        return format.parse(dateString, pos);
    }

    /**
     * 锁对象
     */
    private static final Object lockObj = new Object();

    /**
     * 存放不同的日期模板格式的sdf的Map
     */
    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();

    /**
     * 返回一个ThreadLocal的sdf,每个线程只会new一次sdf
     *
     * @param pattern
     * @return
     */
    private static SimpleDateFormat getSdf(final String pattern) {
        ThreadLocal<SimpleDateFormat> tl = sdfMap.get(pattern);
        // 此处的双重判断和同步是为了防止sdfMap这个单例被多次put重复的sdf
        if (tl == null) {
            synchronized (lockObj) {
                tl = sdfMap.get(pattern);
                if (tl == null) {
                    // 这里是关键,使用ThreadLocal<SimpleDateFormat>替代原来直接new SimpleDateFormat
                    tl = new ThreadLocal<SimpleDateFormat>() {
                        @Override
                        protected SimpleDateFormat initialValue() {
                            return new SimpleDateFormat(pattern);
                        }
                    };
                    sdfMap.put(pattern, tl);
                }
            }
        }
        return tl.get();
    }

    /**
     * 是用ThreadLocal<SimpleDateFormat>来获取SimpleDateFormat,这样每个线程只会有一个SimpleDateFormat
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        return getSdf(pattern).format(date);
    }

    public static Date parse(String dateStr, String pattern) throws ParseException {
        return getSdf(pattern).parse(dateStr);
    }

    public static Date addSecond(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, amount);
        return cal.getTime();
    }

    public static Date addMinute(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, amount);
        return cal.getTime();
    }

    public static Date addHour(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, amount);
        return cal.getTime();
    }

    public static Date addDay(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, amount);
        return cal.getTime();
    }

    public static Date addMonth(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, amount);
        return cal.getTime();
    }

    /**
     * 获取两个日期之间的分钟数
     *
     * @param before
     * @param after
     * @return
     */
    public static double getDistanceMinsOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60);
    }

    /**
     * 获取两个日期之间的小时数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static double getDistanceHoursOfTwoDate(Date date1, Date date2) {
        DecimalFormat df = new DecimalFormat("###,###.##");
        String hours = df.format(((date2.getTime() - date1.getTime()) / (1000 * 3600f)));
        return Double.parseDouble(hours);
    }

    /**
     * 获取两个日期之间的秒数
     *
     * @param before
     * @param after
     * @return
     */
    public static double getDistanceSecondsOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000);
    }

    public static String getStrTime(String time, String oriPattern, String targetPattern) {
        SimpleDateFormat dateFormat = null;
        try {
            dateFormat = new SimpleDateFormat(oriPattern);
            Date dateTime = dateFormat.parse(time);
            dateFormat = new SimpleDateFormat(targetPattern);
            return dateFormat.format(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取两个日期相差的天数
     *
     * @param before
     * @param after
     * @return
     */
    public static int getDistanceDaysOfTwoDate(Date before, Date after) {
        Integer days = Integer.valueOf(0);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(date2String(before, "yyyy-MM-dd")));
            long time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(date2String(after, "yyyy-MM-dd")));
            long time2 = cal.getTimeInMillis();
            long between_days = (time2 - time1) / (1000 * 3600 * 24);
            days = Integer.parseInt(String.valueOf(between_days));
        } catch (Exception ex) {

        }
        return days;
    }


    /**
     * @param dateStr
     * @return
     * @throws ParseException
     * @Description:判断是否为今天
     */
    public static boolean isToday(String dateStr, String pattern) {
        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            Date date = format.parse(dateStr);
            cal.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (cal.get(Calendar.YEAR) == pre.get(Calendar.YEAR) && cal.get(Calendar.DAY_OF_YEAR) == pre.get(Calendar.DAY_OF_YEAR)) {
            return true;
        }

        return false;
    }

    /**
     * @param date
     * @return
     * @throws ParseException
     * @Description:判断是否为今天
     */
    public static boolean isToday(Date date) {
        Calendar pre = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        if (cal.get(Calendar.YEAR) == pre.get(Calendar.YEAR) && cal.get(Calendar.DAY_OF_YEAR) == pre.get(Calendar.DAY_OF_YEAR)) {
            return true;
        }

        return false;
    }


    private static Date add(final Date date, final int calendarField, final int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

    /**
     * 日期增加天数
     *
     * @param dateStr
     * @param pattern
     * @param amount
     * @return
     */
    public static String addDay(String dateStr, int amount, String pattern) {
        try {
            Date date = parse(dateStr, pattern);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DAY_OF_MONTH, amount);
            return date2String(cal.getTime(), pattern);
        } catch (Exception e) {

            return null;
        }

    }


    /**
     * 判断用车时间是否是明天及以后时间
     *
     * @param useCarTime
     * @return
     */
    public static boolean checkTime(String useCarTime) {
        try {
            Date useCarDate = parse(useCarTime, DATE_PATTERN_YYYY_MM_DD);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            Date date = calendar.getTime();
            // 判断用车时间是否是明天及以后时间
            if (useCarDate.before(date)) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 分钟取10的整数
     *
     * @param time
     * @return
     */
    public static String getRoundTime(String time) {
        try {
            Date date = parse(time, DATE_PATTERN_HH_MM);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int minute = calendar.get(Calendar.MINUTE);
            int space = 10 - minute % 10;
            calendar.add(Calendar.MINUTE, space);
            return date2String(calendar.getTime(), DATE_PATTERN_HH_MM);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询离当前时间最近的分钟为 5 的整数倍的时间: 05, 10, 15, 20
     *
     * @param time 需要向上取整的时间
     * @return 离当前时间最近的5的整数倍时间
     */
    public static LocalDateTime getClosedTime(LocalDateTime time) {
        final int timeSpace = 5;
        final int timeSpace10 = 10;

        if (time.getMinute() % timeSpace == 0) {
            return time;
        } else {
            int adjustedMinutes = timeSpace10 - time.getMinute() % timeSpace10;
            adjustedMinutes = adjustedMinutes > timeSpace ? adjustedMinutes - timeSpace : adjustedMinutes;

            return time.plusMinutes(adjustedMinutes);
        }
    }

    /**
     * 当前的北京时间 CST
     *
     * @return
     */
    public static LocalDateTime getCurrentCSTTime() {
        ZoneId shangHaiZoneId = TimeZone.getTimeZone(DateUtil.TIME_ZONE_SHANGHAI).toZoneId();
        return LocalDateTime.now(shangHaiZoneId);
    }

    /**
     * 格式化日期，格式: yyyy-MM-dd HH:mm
     *
     * @param dateTime
     * @return
     */
    public static String formatLocalDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * 将日期转换为北京时间的 LocalDateTime 格式
     *
     * @param date
     * @return
     */
    public static LocalDateTime convertFromDate(Date date) {
        ZoneId shangHaiZoneId = TimeZone.getTimeZone(DateUtil.TIME_ZONE_SHANGHAI).toZoneId();
        return date.toInstant().atZone(shangHaiZoneId).toLocalDateTime();
    }

    /**
     * 和当前时间比，是否是今天以后的日期
     *
     * @param dateTime
     * @return
     */
    public static boolean isFutureDate(LocalDateTime dateTime) {
        //当天的 23:59:59
        LocalDateTime todayEndTime = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        return dateTime.isAfter(todayEndTime);
    }

    /**
     * 时间比较
     *
     * @param time1
     * @param time2
     * @return
     */
    public static boolean compareTime(String time1, String time2) {
        try {
            Date date1 = parse(time1, DATE_PATTERN_HH_MM);
            Date date2 = parse(time2, DATE_PATTERN_HH_MM);
            if (date1.after(date2)) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 时间比较
     *
     * @param firstTime
     * @param secondTime
     * @param pattern
     * @return
     */
    public static boolean compareTime(String firstTime, String secondTime, String pattern) {
        try {
            Date date1 = parse(firstTime, pattern);
            Date date2 = parse(secondTime, pattern);
            // firstTime 在后，返回true
            if (date1.after(date2)) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 增加时间中的小时
     *
     * @param date
     * @param hours
     * @return
     */
    public static Date addHours(Date date, long hours) {
        LocalDateTime localDateTime = convertFromDate(date);
        localDateTime = localDateTime.plusHours(hours);
        // 转换回 date
        return convertToDate(localDateTime);
    }

    /**
     * 返回指定日期的当天的午夜时间: 23:59:59
     *
     * @param date
     * @return
     */
    public static Date getMaxTimeOfTheDay(Date date) {
        LocalDateTime localDateTime = convertFromDate(date);
        localDateTime = LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MAX);
        return convertToDate(localDateTime);
    }

    /**
     * 返回指定日期的当天的凌晨时间: 0:00:00
     *
     * @param date
     * @return
     */
    public static Date getMinTimeOfTheDay(Date date) {
        LocalDateTime localDateTime = convertFromDate(date);
        localDateTime = LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MIN);
        return convertToDate(localDateTime);
    }

    /**
     * 将 LocalDateTime 转换为 Date
     *
     * @param localDateTime
     * @return
     */
    public static Date convertToDate(LocalDateTime localDateTime) {
        ZoneId shangHaiZoneId = TimeZone.getTimeZone(DateUtil.TIME_ZONE_SHANGHAI).toZoneId();
        return Date.from(localDateTime.atZone(shangHaiZoneId).toInstant());
    }


    public static Long getUnixTimestamp(Date date) {
        long unixTimestamp = 0;
        try {
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = sd.format(new Date());
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
            unixTimestamp = date.getTime() / 1000;
        } catch (ParseException ex) {

        }
        return unixTimestamp;

    }

    /**
     * 判断两个时间是否在同一天内
     *
     * @param firstDateTime
     * @param secondDateTime
     * @return
     */
    public static boolean isSameDay(Date firstDateTime, Date secondDateTime) {
        Calendar pre = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        pre.setTime(firstDateTime);
        cal.setTime(secondDateTime);

        if (cal.get(Calendar.YEAR) == pre.get(Calendar.YEAR) && cal.get(Calendar.DAY_OF_YEAR) == pre.get(Calendar.DAY_OF_YEAR)) {
            return true;
        }

        return false;
    }


    /**
     * @param time
     * @return
     */
    public static LocalTime formatStringToLocalTime(String time) {

        return LocalTime.parse(time, DTF_HH_MM_SS_DATA_FORMATTER.get());
    }

    /**
     * @param date
     * @return
     */
    public static LocalDate parseDateByLocalDateHasLine(String date) {

        return LocalDate.parse(date, DTF_YYYY_MM_DD_DATA_FORMATTER.get());
    }

    /**
     * @param date
     * @return
     */
    public static String formateChineseDate(LocalDate date) {

        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        return String.format("%s年%s月%s日", year, month, day);
    }

    /**
     * @param timestamp
     * @return
     */
    public static LocalDateTime getDateTimeOfTimestamp(Long timestamp) {

        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * @param timestamp
     * @return
     */
    public static LocalDate formatTimestampToLocalDate(Long timestamp) {

        LocalDateTime ldt = getDateTimeOfTimestamp(timestamp);
        return ldt.toLocalDate();
    }

    /**
     * @param date
     * @return
     */
    public static LocalDate formatDateToLocalDate(Date date) {

        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, zone);
        return ldt.toLocalDate();
    }

    /**
     * @param date
     * @return
     */
    public static LocalDate parseDateByLocalDateNoLine(String date) {

        return LocalDate.parse(date, DTF_YYYYMMDD_DATA_FORMATTER.get());
    }

    /**
     * @param date
     * @return
     */
    public static Date parseLocalDateToDate(String date) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = parseDateByLocalDateHasLine(date).atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * @param localDate
     * @return
     */
    public static Date formatLocalDateToDate(LocalDate localDate) {

        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    public static Date convertLocalDateToDate(LocalDate localDate) {

        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }


    /**
     * 获取当前日期月有多少天
     *
     * @param date
     * @return
     */
    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取日期是周几
     *
     * @param date
     * @return
     */

    public static String getDaysWeekName(Date date) {
        Calendar cal = Calendar.getInstance();
        String week = "";
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int weekday = cal.get(Calendar.DAY_OF_WEEK);
        if (weekday == 1) {
            week = "周日";
        } else if (weekday == 2) {
            week = "周一";
        } else if (weekday == 3) {
            week = "周二";
        } else if (weekday == 4) {
            week = "周三";
        } else if (weekday == 5) {
            week = "周四";
        } else if (weekday == 6) {
            week = "周五";
        } else if (weekday == 7) {
            week = "周六";
        }
        return week;
    }

}
