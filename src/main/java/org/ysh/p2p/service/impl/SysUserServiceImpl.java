package org.ysh.p2p.service.impl;

import org.ysh.p2p.dao.AbstractDao;
import org.ysh.p2p.dao.SysUserDao;
import org.ysh.p2p.dao.impl.SysUserDaoImpl;
import org.ysh.p2p.model.SysUser;
import org.ysh.p2p.service.SysUserService;
import org.ysh.p2p.util.StringUtil;

public class SysUserServiceImpl extends AbstractServiceImpl<SysUser> implements
		SysUserService {

	private SysUserDao sysUserDao = new SysUserDaoImpl();
	
	public AbstractDao<SysUser> getDao() {
		return sysUserDao;
	}

	public SysUser validateUser(String userCd, String passwd) {
		SysUser user = new SysUser();
		user.setUserCd(userCd);
		user.setPassword(StringUtil.getMd5(StringUtil.getMd5(passwd) + userCd));
		user.setIsAdmin(null);//屏蔽这个参数，因为用户在登录时不需要选择
		return this.query(user, SysUser.class);
	}

}
