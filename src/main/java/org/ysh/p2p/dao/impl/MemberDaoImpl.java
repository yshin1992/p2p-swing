package org.ysh.p2p.dao.impl;

import org.ysh.p2p.model.Member;
import org.ysh.p2p.dao.MemberDao;

public class MemberDaoImpl extends AbstractDaoImpl<Member> implements MemberDao{

	public void query(Member member) throws Exception {
		this.query(member, Member.class);
	}

}
