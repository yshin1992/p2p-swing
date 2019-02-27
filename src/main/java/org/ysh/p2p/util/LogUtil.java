package org.ysh.p2p.util;

import java.util.logging.Logger;

/**
 * Log 工具类(基于jdk Logger)
 * @author yshin1992
 *
 */
public class LogUtil {
	
	public static Logger getLogger(Object obj){
		return Logger.getLogger(obj.getClass().getName());
	}
	
}
