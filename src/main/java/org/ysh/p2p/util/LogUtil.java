package org.ysh.p2p.util;

import java.util.logging.Logger;

/**
 * Log 工具类(基于jdk Logger)
 * @author yshin1992
 *
 */
public class LogUtil {
	
	static{
		System.setProperty("java.util.logging.config.file",LogUtil.class.getClass().getResource("/")+"logging.properties");
	}
	
	public static Logger getLogger(Object obj){
		return Logger.getLogger(obj.getClass().getName());
	}
	
}
