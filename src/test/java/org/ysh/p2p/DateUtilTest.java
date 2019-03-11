package org.ysh.p2p;

import java.text.ParseException;
import java.util.Date;

import org.ysh.p2p.util.DateUtil;

public class DateUtilTest {

	public static void main(String[] args) throws ParseException {
		Date lastLoginTime = DateUtil.defaultParse("2019-03-11 12:10:11");
		boolean firstLoginInToday = DateUtil.isFirstLoginInToday(lastLoginTime, new Date());
		System.out.println(firstLoginInToday);
	}
}
