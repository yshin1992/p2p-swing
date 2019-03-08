package org.ysh.p2p;

import junit.framework.TestCase;

import org.ysh.p2p.model.Member;
import org.ysh.p2p.service.MemberService;
import org.ysh.p2p.service.impl.MemberServiceImpl;
import org.ysh.p2p.vo.ResponseMsg;

public class MemberServiceTest extends TestCase{

	private MemberService service = new MemberServiceImpl();
	
	public void testLogin(){
		Member member = new Member();
		member.setPhone("15852087981");
		member.setPassword("123456");
		member.setStatus(null);
		try {
			ResponseMsg<Member> login = service.login(member);
			System.out.println(login.getCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
