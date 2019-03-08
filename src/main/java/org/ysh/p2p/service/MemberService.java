package org.ysh.p2p.service;

import org.ysh.p2p.model.Member;
import org.ysh.p2p.vo.ResponseMsg;

public interface MemberService extends AbstractService<Member> {

	public ResponseMsg<Member> register(Member member)  throws Exception;
	
	public ResponseMsg<Member> login(Member member) throws Exception;
}
