package org.ysh.p2p.support;

import java.util.EventObject;

import org.ysh.p2p.model.SysUser;

public class SysUserLoginEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7147164191062822367L;

	public SysUserLoginEvent(SysUser source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

}
