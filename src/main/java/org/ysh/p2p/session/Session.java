package org.ysh.p2p.session;

import org.ysh.p2p.model.Member;
import org.ysh.p2p.model.SysUser;

public class Session {
	
	private static final Session session = new Session();
	
	private SysUser loginUser;
	
	private Member loginMember;
	
	private Session(){
	}

	public static Session getInstance(){
		return session;
	}

	public SysUser getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(SysUser loginUser) {
		this.loginUser = loginUser;
	}

	public Member getLoginMember() {
		return loginMember;
	}

	public void setLoginMember(Member loginMember) {
		this.loginMember = loginMember;
	}
	
	
}
