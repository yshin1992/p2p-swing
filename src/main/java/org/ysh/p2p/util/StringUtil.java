package org.ysh.p2p.util;

import java.util.UUID;

public final class StringUtil {

	/**
	 * 生成UUID
	 * @return
	 */
	public static final String generateUuid(){
		return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase(); 
	}
	
	
	/**
	 * 判断字符串是否为空，不为空时返回true，否则返回false
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		if(null != str && str.trim().length() > 0){
			return true;
		}
		return false;
	}
}
