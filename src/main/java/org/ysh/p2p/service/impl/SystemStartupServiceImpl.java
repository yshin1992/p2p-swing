package org.ysh.p2p.service.impl;

import java.util.Map;

import org.ysh.p2p.service.SystemStartupService;
import org.ysh.p2p.support.ApplicationContext;
import org.ysh.p2p.support.LoginSuccessEvent;
import org.ysh.p2p.support.RegisterSuccessEvent;
import org.ysh.p2p.support.listener.LoginInfoListener;
import org.ysh.p2p.support.listener.RegisterInfoListener;

/**
 * 系统启动服务Service
 * @author yshin1992
 *
 */
public class SystemStartupServiceImpl implements SystemStartupService {

	public Map<String, Object> start() {
		//注册事件EventObject和Listener
		ApplicationContext context = ApplicationContext.getApplicationContext();
		context.registerListener(new RegisterSuccessEvent<String>(""), new RegisterInfoListener());
		context.registerListener(new LoginSuccessEvent("", false),new LoginInfoListener());
		
		return null;
	}

	
	public void shutdown() {

	}

}
