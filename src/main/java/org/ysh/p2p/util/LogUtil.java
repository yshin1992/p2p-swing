package org.ysh.p2p.util;

import java.util.logging.Logger;

/**
 * Log 工具类(基于jdk Logger)
 * @author yshin1992
 *
 */
public class LogUtil {
	
	static{
		String logFilePath = LogUtil.class.getClass().getResource("/").toString()+"logging.properties";
		System.setProperty("java.util.logging.config.file",logFilePath.replace("file:", ""));
	}
	
	public static Logger getLogger(Object obj){
		return Logger.getLogger(obj.getClass().getName());
	}
	
	public static Logger getLogger(Class<?> clazz){
		return Logger.getLogger(clazz.getName());
	}
	
	public static void main(String[] args) {
		Logger logger = LogUtil.getLogger("com.oo");
		logger.fine("Hello");
		logger.info("Hello");
		logger.warning("Hello");
		System.out.println(LogUtil.class.getClass().getResource("/").toString()+"logging.properties");
	}
}
