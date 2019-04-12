package org.ysh.p2p.dao;

import org.ysh.p2p.model.Member;

public interface MemberDao extends AbstractDao<Member> {

	public void query(Member member)  throws Exception ;
	
}
