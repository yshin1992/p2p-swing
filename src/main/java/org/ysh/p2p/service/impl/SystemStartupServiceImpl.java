package org.ysh.p2p.service.impl;

import java.util.Map;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.ysh.p2p.service.SystemStartupService;
import org.ysh.p2p.support.ApplicationContext;
import org.ysh.p2p.support.LoginSuccessEvent;
import org.ysh.p2p.support.RegisterSuccessEvent;
import org.ysh.p2p.support.listener.LoginInfoListener;
import org.ysh.p2p.support.listener.RegisterInfoListener;
import org.ysh.p2p.util.DaoUtil;
import org.ysh.p2p.util.LogUtil;


/**
 * 系统启动服务Service
 * @author yshin1992
 *
 */
public class SystemStartupServiceImpl implements SystemStartupService {
	
	private Logger logger = LogUtil.getLogger(this);
	
	private DataSource ds;
	
	public Map<String, Object> start() {
		//注册事件EventObject和Listener
		ApplicationContext context = ApplicationContext.getApplicationContext();
		context.registerListener(new RegisterSuccessEvent<String>(""), new RegisterInfoListener());
		context.registerListener(new LoginSuccessEvent("", false),new LoginInfoListener());
		
		logger.warning("初始化连接池");
		ds = DaoUtil.getDataSource();
		logger.warning("初始化连接池成功");
		
		
		return null;
	}

	
	public void shutdown() {
		logger.warning("关闭数据库连接池");
		DaoUtil.closeDataSource(ds);	
		logger.warning("关闭数据库连接池成功");
	}

}
