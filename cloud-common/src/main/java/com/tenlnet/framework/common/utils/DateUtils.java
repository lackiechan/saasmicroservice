package com.tenlnet.framework.common.utils;

import com.google.common.base.Strings;
import org.apache.commons.lang.time.DateFormatUtils;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: lackiechan
 * @Email: 2734858720@qq.com
 * @Date: 2019-08-14 00:33
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils {
    public final static String FORMAT_YMDHMS="yyyy-MM-dd HH:mm:ss";
    public final static String FORMAT_YMD="yyyy-MM-dd";

    // 获取本周的开始时间
    public static Date getBeginDayOfWeek() {
        Date date = new Date();
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return getDayStartTime(cal.getTime());
    }

    /**
     * 获取前几周第一天
     * @param lastweek
     * @return
     */
    public static Date getBeginDayOfLastWeeks(int lastweek) {
        Date date = new Date();
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek - lastweek*7);
        return getDayStartTime(cal.getTime());
    }


    /**
     * 获取后几周的第一天
     * @param afterweek
     * @return
     */
    public static Date getBeginDayOfAfterWeeks(int afterweek) {
        Date date = new Date();
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek + afterweek*7);
        return getDayStartTime(cal.getTime());
    }


    // 获取某个日期的开始时间
    public static Timestamp getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d){
            calendar.setTime(d);
        }

        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }


    public static Timestamp getDayEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d){
            calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }

    public static String formatYMDHMS(Date date) {
       return DateFormatUtils.format(date,FORMAT_YMDHMS);
    }

    public static String formatYMD(Date date) {
        if(date==null){
            return "";
        }
        return DateFormatUtils.format(date,FORMAT_YMD);
    }



    public static void main(Strings[] args){
        System.out.println(DateUtils.formatYMDHMS(getBeginDayOfWeek()));
    }

}
