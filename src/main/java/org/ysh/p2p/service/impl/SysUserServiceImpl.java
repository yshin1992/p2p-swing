package org.ysh.p2p.service.impl;

import org.ysh.p2p.dao.AbstractDao;
import org.ysh.p2p.dao.SysUserDao;
import org.ysh.p2p.dao.impl.SysUserDaoImpl;
import org.ysh.p2p.model.SysUser;
import org.ysh.p2p.service.SysUserService;

public class SysUserServiceImpl extends AbstractServiceImpl<SysUser> implements
		SysUserService {

	private SysUserDao sysUserDao = new SysUserDaoImpl();
	
	public AbstractDao<SysUser> getDao() {
		return sysUserDao;
	}

}
