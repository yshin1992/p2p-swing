package org.ysh.p2p.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	
	public static String defaultFormat(Date date){
		Objects.requireNonNull(date);
		return new SimpleDateFormat(DEFAULT_PATTERN).format(date);
	}
	
	public static String simpleFormat(Date date){
		Objects.requireNonNull(date);
		return new SimpleDateFormat(DEFAULT_DATE_PATTERN).format(date);
	}
	
}
