package org.ysh.p2p.support.listener;

import java.util.Date;
import java.util.logging.Logger;

import org.ysh.p2p.model.SysUser;
import org.ysh.p2p.service.SysUserService;
import org.ysh.p2p.service.impl.SysUserServiceImpl;
import org.ysh.p2p.support.SysUserLoginEvent;
import org.ysh.p2p.util.IPAddressUtil;
import org.ysh.p2p.util.LogUtil;

public class SysUserLoginInfoListener implements ApplicationListener<SysUserLoginEvent> {

	private Logger logger = LogUtil.getLogger(this);
	
	private SysUserService sysUserService = new SysUserServiceImpl();
	
	public void onApplicationEvent(SysUserLoginEvent event) {
		logger.warning("开始记录系统管理员登录信息。。。");
		SysUser user = (SysUser)event.getSource();
		user.setLastLoginIp(IPAddressUtil.getLocalAvailableIP());
		user.setLastLoginTime(new Date());
		user.setLoginCounts(user.getLoginCounts()+1);
		sysUserService.update(user, SysUser.class);
		logger.warning("记录系统管理员登录信息结束。。。");
		
	}

}
