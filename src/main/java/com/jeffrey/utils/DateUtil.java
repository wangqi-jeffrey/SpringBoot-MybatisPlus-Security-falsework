package com.jeffrey.utils;

import com.google.gson.JsonElement;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Description: 日期处理工具类
 *
 * @author 滕国栋
 * @date 2020/08/17 下午 21:49
 */
public class DateUtil extends DateUtils {

    public static final long DAY_MILLI = 24 * 60 * 60 * 1000; // 一天的MilliSecond

    public static final String PATTERN_DATETIME_TZ = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_DATETIME_RANDOM = "yyyyMMddHHmmssSSS";
    public static final String PATTERN_DATETIME_FORMAT = "yyyyMMddHHmmss";
    public static final String PATTERN_DATE = "yyyy-MM-dd";
    public static final String PATTERN_DATE_DOT = "yyyy.M.d";
    public static final String PATTERN_DATE_NO_HYPHEN = "yyyyMMdd";
    public static final String PATTERN_MONTH = "yyyy-MM";
    public static final String PATTERN_YEAR = "yyyy";
    public static final String YYYY_MM_DD = "yyyy年MM月dd日 HH:mm:ss";
    public static final String PATTERN_CHINESE = "yyyy年MM月dd日";

    public static String formatCurrentDateTime() {
        return formatDateTime(new Date());
    }

    public static String formatCurrentDate() {
        return formatDate(new Date());
    }

    public static String formatDateTime(Date date) {
        return format(PATTERN_DATETIME, date);
    }

    public static String formatDateMonth(Date date) {
        return format(PATTERN_MONTH, date);
    }

    public static String formatDate(Date date) {
        return format(PATTERN_DATE, date);
    }

    public static String formatDateYYYYMMDD(Date date) {
        return format(PATTERN_DATE_NO_HYPHEN, date);
    }

    public static String formatDateYYYYMMDDHHMMSS() {
        return format(PATTERN_DATETIME_FORMAT, new Date());
    }

    public static String formatYear(Date date) {
        return format(PATTERN_YEAR, date);
    }

    public static String formatDateTimeTZ(Date date) {
        return format(PATTERN_DATETIME_TZ, date);
    }

    public static Date parseDateTime(String str) {
        return parse(PATTERN_DATETIME, str);
    }

    public static Date parseDate(String str) {
        return parse(PATTERN_DATE, str);
    }

    public static Date parseYear(String str) {
        return parse(PATTERN_YEAR, str);
    }

    public static Date parseDateTimeTZ(String str) {
        return parse(PATTERN_DATETIME_TZ, str);
    }

    public static String formatCurrentSystemDate() {
        return format(PATTERN_DATETIME_RANDOM, new Date());
    }

    public static String formatTodaySystemDate() {
        return format(PATTERN_DATE_NO_HYPHEN, new Date());
    }

    public static String formatChineseDate(Date date) {
        return format(PATTERN_CHINESE, date);
    }

    public static String format(String pattern, Date date) {
        if (null == date) {
            return null;
        }

        SimpleDateFormat FORMATTER = new SimpleDateFormat();
        FORMATTER.applyPattern(pattern);
        return FORMATTER.format(date);
    }

    public static Date parse(String pattern, String str) {
        if (StringUtil.isBlank(str)) {
            return null;
        }

        SimpleDateFormat FORMATTER = new SimpleDateFormat();

        FORMATTER.applyPattern(pattern);

        try {
            return FORMATTER.parse(str);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取当月起止日期时间
     * <p>
     * 以2015年10月份为例，返回的是2015-10-01 00:00:00 和 2015-11-01 00:00:00
     *
     * @param now
     * @return
     */
    public static Pair<Date, Date> getMonthRange(Date now) {
        Calendar tmp = Calendar.getInstance();
        tmp.setTime(now);

        tmp.set(Calendar.DAY_OF_MONTH, 1);
        tmp.set(Calendar.HOUR_OF_DAY, 0);
        tmp.set(Calendar.MINUTE, 0);
        tmp.set(Calendar.SECOND, 0);
        Date startDate = tmp.getTime();

        tmp.add(Calendar.MONTH, 1);
        Date endDate = tmp.getTime();

        return Pair.of(startDate, endDate);
    }

    /**
     * 获取目标时间与当前时间间隔的小时数
     *
     * @param targetDate
     * @return
     */
    public static int getDiffHourFromCurrent(Date targetDate) {
        long diff = System.currentTimeMillis() - targetDate.getTime();
        return (int) (diff / 1000 / 60 / 60);
    }

    /**
     * 获取目标时间与当前时间间隔的小时数
     *
     * @param targetDate
     * @return
     */
    public static int getDiffMinuteFromCurrent(Date targetDate) {
        long diff = System.currentTimeMillis() - targetDate.getTime();
        return (int) (diff / 1000 / 60);
    }

    /**
     * 获取某个日期与当前时间距离的天数
     *
     * @param targetDate
     * @return
     */
    public static int getDiffDays(String targetDate) {
        Date tarDate = DateUtil.parse(PATTERN_DATE, targetDate);
        Date nowDate = DateUtil.parse(PATTERN_DATE, DateUtil.format(PATTERN_DATE, new Date()));
        Calendar cal = Calendar.getInstance();
        cal.setTime(tarDate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(nowDate);
        long time2 = cal.getTimeInMillis();
        long betweenDays = (time1 - time2) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(betweenDays));
    }

    /**
     * 获取某个日期与当前时间距离多少年
     *
     * @param targetDate
     * @return
     */
    public static int getDiffYear(String targetDate) {
        String targetYear = DateUtil.formatYear(DateUtil.parseDate(targetDate));
        String nowYear = DateUtil.formatYear(new Date());
        return Integer.parseInt(nowYear) - Integer.parseInt(targetYear);
    }

    /**
     * 在线缴租  格式化时间 "-"改为"."
     *
     * @param targetDate
     * @return
     */
    public static String formatPayDate(JsonElement targetDate) {
        String retDate = targetDate.isJsonNull() ? "" : targetDate.getAsString().replace("-", ".");
        return retDate;
    }

    /**
     * 推送消息列表 格式化时间 "-"改为"."
     *
     * @param targetDate
     * @return
     */
    public static String replaceSpliter(String targetDate) {
        String retDate = StringUtil.isEmpty(targetDate) ? "" : targetDate.replace("-", ".");
        return retDate;
    }

    /**
     * 在java.util.DateObject上增加/减少几天
     *
     * @param date java.util.Date instance
     * @param days 增加/减少的天数
     * @return java.util.Date Object
     * @history
     * @since 1.0
     */
    public static Date addDays(Date date, int days) {
        long temp = date.getTime();
        return new Date(temp + DateUtil.DAY_MILLI * days);
    }

    /**
     * 在传入日期上增加/减少几年
     *
     * @param date
     * @param years
     * @return
     */
    public static Date addYears(Date date, int years) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, years);
        Date newDate = calendar.getTime();
        return newDate;
    }

    /**
     * 以天为单位计算日期1是否晚于日期2 (不含相等情况)
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean afterInDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        if (isSameDay(date1, date2)) {
            return false;
        } else {
            return date1.after(date2);
        }
    }

    /**
     * 判断传入两个日期大小
     * date1 > date2 return true
     * date1 < date2 return false
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean compareDate(Date date1, Date date2) {
        if (date1 == null && date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        if (date1 == null && date2 != null) {
            return false;
        }
        if (date1 != null && date2 == null) {
            return true;
        }
        return date1.getTime() - date2.getTime() > 0 ? true : false;

    }

    /**
     * 获取当天指定前后的日期
     *
     * @return
     */
    public static Date getAppointDay(int n) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, n);
        Date time = cal.getTime();
        return time;
    }

    /**
     * 添加分
     * Description:
     *
     * @param date
     * @param minutes
     * @return Author:kejianwang
     * Date: 2018年5月8日 上午11:49:12
     * Version: 1.0
     */
    public static Date addMinute(Date date, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

    /**
     * 添加秒
     * Description:
     *
     * @param date
     * @param second
     * @return Author:kejianwang
     * Date: 2018年5月8日 上午11:49:12
     * Version: 1.0
     */
    public static Date addSecond(Date date, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 获取两个日期之间包含天数
     *
     * @param start
     * @param end
     * @return
     */
    public static int getIncludeDay(Date start, Date end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        // 用于计算的起止日期
        Date calStart = start;
        Date calEnd = end;

        boolean isSameDay = isSameDay(start, end);
        if (isSameDay) {
            return 1;
        } else if (start.after(end)) {
            calStart = end;
            calEnd = start;
        }

        int days = getDiffDays(calStart, calEnd) + 1;

        return days;
    }

    /**
     * 获取两个日期相差天数
     *
     * @param start
     * @param end
     * @return
     */
    public static int getDiffDays(Date start, Date end) {

        if (start == null || end == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(start);
        c2.setTime(end);

        return getDiffDays(c1, c2);
    }

    /**
     * 获取相差天数
     *
     * @param c1
     * @param c2
     * @return
     */
    private static int getDiffDays(Calendar c1, Calendar c2) {
        if (c1 == null || c2 == null) {
            throw new IllegalArgumentException("The calendar must not be null");
        }
        long time1 = c1.getTime().getTime();
        long time2 = c2.getTime().getTime();
        return (int) ((time2 - time1) / 1000 / 3600 / 24);
    }

    /**
     * 获取两个时间之间相差的分钟数
     *
     * @param start
     * @param end
     * @return 取整
     */
    public static long getDiffMinutes(Date start, Date end) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(start);
        c2.setTime(end);

        long time1 = c1.getTime().getTime();
        long time2 = c2.getTime().getTime();
        return (time2 - time1) / 1000 / 60;
    }


    /**
     * 获取某月固定日的日期(超出返回当月最后一天)
     *
     * @param date 日期
     * @param day  第几天
     * @return
     */
    public static Date getDateByDay(Date date, int day) {
        int monthLastDay = getMonthLastDay(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (day <= monthLastDay) {
            calendar.set(Calendar.DATE, day);
        } else {
            calendar.set(Calendar.DATE, monthLastDay);
        }
        return calendar.getTime();
    }

    /**
     * 得到指定日期所在月的天数
     *
     * @param date 日期
     * @return
     */
    public static int getMonthLastDay(Date date) {
        Calendar a = Calendar.getInstance();
        a.setTime(date);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 获取两个日期之间包含几年几月几日
     *
     * @param start
     * @param end
     * @return
     */
    public static Triple<Integer, Integer, Integer> getIncludeYearMonthDay(Date start, Date end) {

        if (start == null || end == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        // 用于计算的起止日期
        Date calStart = start;
        Date calEnd = end;

        boolean isSameDay = isSameDay(start, end);
        if (!isSameDay && start.after(end)) {
            calStart = end;
            calEnd = start;
        }

        int years = 0;
        int months = 0;
        int days = 0;

        Pair<Integer, Integer> diffMonthDay = getIncludeMonthDay(calStart, calEnd);

        int totalMonths = diffMonthDay.getLeft();

        years = totalMonths / 12;
        months = totalMonths % 12;
        days = diffMonthDay.getRight();

        Triple<Integer, Integer, Integer> triple = Triple.of(years, months, days);
        return triple;
    }

    /**
     * 获取两个日期之间包含几月几日
     *
     * @param start
     * @param end
     * @return
     */
    public static Pair<Integer, Integer> getIncludeMonthDay(Date start, Date end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        // 用于计算的起止日期
        Date calStart = start;
        Date calEnd = end;

        boolean isSameDay = isSameDay(start, end);
        if (!isSameDay && start.after(end)) {
            calStart = end;
            calEnd = start;
        }

        int months = 0;
        int days = 0;

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(calStart);
        c2.setTime(calEnd);

        int c1Year = c1.get(Calendar.YEAR);
        int c2Year = c2.get(Calendar.YEAR);

        int c1Month = c1.get(Calendar.MONTH) + 1;
        int c2Month = c2.get(Calendar.MONTH) + 1;

        int diffYears = c2Year - c1Year;
        int diffMonths = c2Month - c1Month;

        int totalDiffMonths = diffYears * 12 + diffMonths;

        if (totalDiffMonths == 0) { // 同一月份
            Date cursor = addNaturalMonths(calStart, 1);
            cursor = addDays(cursor, -1);
            if (isSameDay(cursor, calEnd)) {
                months = 1;
            } else {
                days = getIncludeDay(calStart, calEnd);
            }
        } else { // 不同月份
            Date cursor = addNaturalMonths(calStart, totalDiffMonths);
            cursor = addDays(cursor, -1);

            if (isSameDay(cursor, calEnd)) {
                months = totalDiffMonths;
            } else {
                if (cursor.before(calEnd)) {
                    months = totalDiffMonths;
                    cursor = addDays(cursor, 1);

                    Date cursor2 = addNaturalMonths(cursor, 1);
                    cursor2 = addDays(cursor2, -1);
                    if (isSameDay(cursor2, calEnd)) {
                        months++;
                    } else {
                        days = getIncludeDay(cursor, calEnd);
                    }
                } else {
                    months = totalDiffMonths - 1;
                    cursor = addNaturalMonths(calStart, months);
                    days = getIncludeDay(cursor, calEnd);
                }
            }

        }

        Pair<Integer, Integer> pair = Pair.of(months, days);
        return pair;

    }

    /**
     * 添加自然月数，对月末几天进行特殊处理
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addNaturalMonths(Date date, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        Date naturalMonth = addMonths(date, amount);

        return naturalMonth;
    }

    public static String getYesterdayDate(String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取两个日期之间完整月份和剩余天数
     *
     * @param start
     * @param end
     * @return
     */
    public static Pair<Integer, Integer> getIncludeMonthAndDay(Date start, Date end) {
        if (null == start || null == end) {
            throw new IllegalArgumentException("The date must not be null");
        }

        int diffMonth = 0, diffDay = 0;

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);

        int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
        if (year == 0 && month == 0) {
            diffDay = endCalendar.get(Calendar.DAY_OF_MONTH) - startCalendar.get(Calendar.DAY_OF_MONTH) + 1;
        } else {
            diffMonth = year * 12 + month - 1;

            if (startCalendar.get(Calendar.DAY_OF_MONTH) == 1) {
                diffMonth++;
            } else {
                diffDay += startCalendar.getActualMaximum(Calendar.DAY_OF_MONTH) - startCalendar.get(Calendar.DAY_OF_MONTH) + 1;
            }

            if (endCalendar.get(Calendar.DAY_OF_MONTH) == endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                diffMonth++;
            } else {
                diffDay += endCalendar.get(Calendar.DAY_OF_MONTH);
            }
        }

        return Pair.of(diffMonth, diffDay);
    }
}
