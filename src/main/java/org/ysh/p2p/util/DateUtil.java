package org.ysh.p2p.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class DateUtil {

	private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
	
	public static Date defaultParse(String dateStr) throws ParseException{
		if(StringUtil.isNotEmpty(dateStr)){
			return new SimpleDateFormat(DEFAULT_PATTERN).parse(dateStr);
		}
		else{
			throw new RuntimeException("参数为空");
		}
	}
	
	public static Date simpleParse(String dateStr) throws ParseException{
		if(StringUtil.isNotEmpty(dateStr)){
			return new SimpleDateFormat(DEFAULT_DATE_PATTERN).parse(dateStr);
		}
		else{
			throw new RuntimeException("参数为空");
		}
	}
	
	public static Date parse(String dateStr,String pattern) throws ParseException{
		if(StringUtil.isNotEmpty(dateStr) && StringUtil.isNotEmpty(pattern)){
			return new SimpleDateFormat(pattern).parse(dateStr);
		}
		else{
			throw new RuntimeException("参数为空");
		}
	}
	
	public static String defaultFormat(Date date){
		Objects.requireNonNull(date);
		return new SimpleDateFormat(DEFAULT_PATTERN).format(date);
	}
	
	public static String simpleFormat(Date date){
		Objects.requireNonNull(date);
		return new SimpleDateFormat(DEFAULT_DATE_PATTERN).format(date);
	}
	
	
	/**
	 * 根据上次登录和本次登录的时间判定是不是今日首次登录
	 * 如果之前未登陆过，直接返回true
	 * 如果之前登陆过，则截取日期部分的字符串，若相同则返回false，否则返回true
	 * @param lastLoginTime
	 * @param currentLoginTime
	 * @return
	 */
	public static boolean isFirstLoginInToday(Date lastLoginTime,Date currentLoginTime){
		if(null == lastLoginTime){
			return true;
		}
		String lastTimeStr = simpleFormat(lastLoginTime);
		String currTimeStr = simpleFormat(currentLoginTime);
		
		if(! lastTimeStr.equals(currTimeStr)){
			return true;
		}else{
			return false;
		}
	}
	
	public static Date addYears(final Date date, final int amount) {
        return add(date, Calendar.YEAR, amount);
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
	
	public static Date addMonths(final Date date, final int amount) {
        return add(date, Calendar.MONTH, amount);
    }
	
	public static Date addWeeks(final Date date, final int amount) {
        return add(date, Calendar.WEEK_OF_YEAR, amount);
    }
	
	public static Date addDays(final Date date, final int amount) {
        return add(date, Calendar.DAY_OF_MONTH, amount);
    }
	
	public static Date addHours(final Date date, final int amount) {
	    return add(date, Calendar.HOUR_OF_DAY, amount);
	}
	
	public static Date addMinutes(final Date date, final int amount) {
        return add(date, Calendar.MINUTE, amount);
    }
	
	public static Date addSeconds(final Date date, final int amount) {
        return add(date, Calendar.SECOND, amount);
    }
	
	public static Date addMilliseconds(final Date date, final int amount) {
        return add(date, Calendar.MILLISECOND, amount);
    }
}
