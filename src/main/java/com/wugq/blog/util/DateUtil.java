package com.wugq.blog.util;

import com.sun.xml.internal.ws.api.model.CheckedException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    /**
     * 缺省日期格式
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 缺省日期格式 中文
     */
    public static final String DEFAULT_DATE_FORMAT_CN = "yyyy年MM月dd日";
    /**
     * 缺省时间格式
     */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    /**
     * 缺省长日期格式
     */
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm";

    /**
     * 缺省长日期格式,精确到秒
     */
    public static final String DEFAULT_DATETIME_FORMAT_SEC = "yyyy-MM-dd HH:mm:ss";

    public static final String DEFAULT_DATETIME_FORMAT_SEC2 = "yyyy/MM/dd HH:mm:ss";

    public static final String DEFAULT_DATETIME_FORMAT_SEC3 = "yyyyMMddHHmm";

    public static final String DEFAULT_DATETIME_FORMAT_SEC4 = "yyyy-MM-dd 23:59:59";

    public static final String DEFAULT_DATETIME_FORMAT_SEC5 = "yyyy-MM-dd HH:mm:00";

    public static final String DEFAULT_DATETIME_FORMAT_SEC6 = "yyyy-MM-dd HH:00:00";

    public static final String DEFAULT_DATETIME_FORMAT_SEC7 = "yyyy-MM-dd 00:00:00";

    public static final String DEFAULT_DATETIME_FORMAT_SEC8 = "yyyy.MM.dd";


    /**
     * 系统默认永久时间，格式为String
     */
    public static final String PERMANENT = "2030-01-01 00:00:00";
    /**
     * 系统默认永久时间，格式为Date
     */
    public static final Date PERMANENTDATE = strToDate(PERMANENT,"yyyy-MM-dd hh:ss:mm");


    /**
     * 格式化时间，和时间的精度相关
     */
    public static Date formatDate(Date date, String formatStr) {
        Date ret = null;
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(formatStr);
        String dateStr = formatter.format(date);
        try {
            ret = formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 日期类型转换成字符串
     *
     * @param date
     * @param format
     * @return
     * @throws CheckedException
     */
    public static String dateToString(Date date, String format) {
        String ret = "";
        if(date == null){
            return ret;
        }
        SimpleDateFormat formatter = new java.text.SimpleDateFormat(format);
        ret = formatter.format(date);
        return ret;
    }

    /**
     * 传入一个时间的字符串，及其格式定义，返回一个Date
     * @param String dateStr
     * @return Date
     */
    public static Date strToDate(String dateStr) {
        return strToDate(dateStr, DEFAULT_DATE_FORMAT);
    }

    /**
     * 传入一个时间的字符串，及其格式定义，返回一个Date
     * @param String dateStr, String format
     * @return Date
     */
    public static Date strToDate(String dateStr, String format) {
        Date ret = null;
        try {
            SimpleDateFormat formatter = new java.text.SimpleDateFormat(format);
            ret  = formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 传入一个时间戳，及其格式定义  转换成Date
     * @param dateTime
     * @param format
     * @return
     */
    public static Date intToDate(long dateTime,String format){
        Date ret = null;
        try{
            SimpleDateFormat formatter = new java.text.SimpleDateFormat(format);
            String strDate = formatter.format(1000*dateTime);
            ret = formatter.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ret;
    }
    /**
     * 系统默认最早时间
     *
     */
    public static final String FIRSTTINME = "1970-01-01 08:00:01";
    public static final Date FIRSTDATE = strToDate(FIRSTTINME,"yyyy-MM-dd hh:ss:mm");
    /**
     * 增加天数
     *
     * @param date
     * @param day
     * @return
     */
    public static Date addDays(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE,day);
        Date newDate = c.getTime();
        return newDate;
    }

    public static String addDay(String dateString, int day) {
        Date date = strToDate(dateString,"yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, day);
        Date newDate = c.getTime();
        return dateToString(newDate,"yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 增加秒
     * @param date
     * @param ss
     * @return
     */
    public static Date addss(Date date, int ss) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, ss);
        Date newDate = c.getTime();
        return newDate;
    }

    /**
     * 获取当前时间的月份
     * @return
     */
    public static int getNowMonth() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH)+1;
    }

    /**
     * 获取当前时间的年份
     * @return
     */
    public static int getNowYear() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }

    /**
     * yyyy-MM-dd HH-mm，
     *
     * @param beginDate 开始时间
     * @return
     */
    public static String getSimpleTime(Date beginDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(beginDate);
    }



    /**
     * 计算两个日期间的天数
     * @param statrdate
     * @param enddate
     * @return
     * @throws ParseException
     */
    public static int daysBetween(Date statrdate,Date enddate)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(statrdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(enddate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 获取指定天数的日期的开始时间
     * @return
     */
    public static String getTimeBegin(int day,Date nowDate){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        //时间处理
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        calendar.add(Calendar.DATE, day);
        //格式化
        String result = format.format(calendar.getTime());
        return result;
    }

    /**
     * 获取指定天数的日期的结束时间
     * @return
     */
    public static String getTimeEnd(int day,Date nowDate){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        //时间处理
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        calendar.add(Calendar.DATE, day);
        //格式化
        String result = format.format(calendar.getTime());
        return result;
    }

    public static Date today() {
        return formatDate(new Date(), DEFAULT_DATE_FORMAT);
    }
}
