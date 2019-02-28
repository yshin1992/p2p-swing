package org.ysh.p2p.service;

import org.ysh.p2p.model.SysUser;

public interface SysUserService extends AbstractService<SysUser> {
	
	public SysUser validateUser(String userCd , String passwd);
}
