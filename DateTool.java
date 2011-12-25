package com.yige.opt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2011-8-29
 * Time: 17:18:42
 * To change this template use File | Settings | File Templates.
 */
public class DateTool {
    /**
     *the string format must yyyy-MM-dd
     *
     * @author zhaolei
     *
     * 2011-4-21
     *
     * @param str
     * @return
     */
    public static Date string2DateDay(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        str = StringTool.null2Trim(str);
        try {
            return formatter.parse(str);
        } catch (ParseException e) {
            Calendar cal=Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            return cal.getTime();
        }
    }           

    /**
     *the string format must yyyy-MM-dd HH:mm:ss
     *
     * @author zhaolei
     *
     * 2011-4-21
     *
     * @param str
     * @return
     */
    public static Date string2DateSecond24(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        str = StringTool.null2Trim(str);
        String matchstr = "[0-2]\\d\\d\\d-\\d\\d-\\d\\d [0-2]\\d:[0-6]\\d";
        if (StringTool.regexMatch(str, matchstr)) {
            str = str + ":00";
        }
        try {
            return formatter.parse(str);
        } catch (ParseException e) {
            return new Date();
        }
    }

    /**
     *the string format must yyyy-MM-dd HH:mm
     *
     * @author zhaolei
     *
     * 2011-4-21
     *
     * @param str
     * @return
     */
    public static Date stirng2DateMinute(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        str = StringTool.null2Trim(str);
        try {
            return formatter.parse(str);
        } catch (ParseException e) {
            return new Date();
        }
    }

    /**
     *must yyyy MM dd HH mm ss
     *
     * @author zhaolei
     *
     * 2011-4-21
     *
     * @param str
     * @param formatString
     * @return
     */
    public static Date stirng2Date(String str, String formatString) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatString);
        str = StringTool.null2Trim(str);
        try {
            return formatter.parse(str);
        } catch (ParseException e) {
            return new Date();
        } catch (IllegalArgumentException e) {
            System.out.println("format string Illegal:" + formatString);
            return null;
        }
    }

    /**
     *yyyy-MM-dd
     *
     * @author zhaolei
     *
     * 2011-4-21
     *
     * @param date
     * @return
     */
    public static String Date2String(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    /**
     * must yyyy MM dd HH mm ss
     *
     * @author zhaolei
     *
     * 2011-4-21
     *
     * @param date
     * @param formatString
     * @return
     */
    public static String Date2String(Date date, String formatString) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(formatString);
            return formatter.format(date);
        } catch (IllegalArgumentException e) {
            System.out.println("format string Illegal:" + formatString);
            return "";
        }
    }

    /**
     * @return
     */
    public static int unixTime() {
        return (int)(System.currentTimeMillis()/1000);
    }
    
    /**
     *
     * @author weinianjie
     *
     * 2011-10-05
     *
     * @param date
     * @return
     */
    public static Date Date2DateDay(Date date) {        
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();        
    }    
    
    /**
     * 获取明天
     * @author weinianjie
     * 2011-10-05
     * @param date
     * @return
     */
    public static Date tomorrow(Date date){
    	Calendar cal=Calendar.getInstance();
        cal.setTime(date);        
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();          	
    }
    
    /**
     * 获取昨天
     * @author weinianjie
     * 2011-10-05
     * @param date
     * @return
     */
    public static Date yesterday(Date date){
    	Calendar cal=Calendar.getInstance();
        cal.setTime(date);        
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();          	
    }
    
    /**
     * 获取一段时期
     * @author weinianjie
     * 2011-10-05
     * @param begin
     * @param end
     * @return
     */
    public static List<Date> period(Date begin, Date end){
    	List<Date> list = new ArrayList<Date>();
    	Date _begin = Date2DateDay(begin);
    	Date _end = Date2DateDay(end);
    	
    	while(_end.compareTo(_begin) >= 0){
    		list.add(_begin);
    		_begin = DateTool.tomorrow(_begin);
    	}
    	return list;
    }
    
}
