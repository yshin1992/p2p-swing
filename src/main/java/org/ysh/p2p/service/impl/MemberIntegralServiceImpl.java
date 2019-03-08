package org.ysh.p2p.service.impl;

import java.math.BigDecimal;

import org.ysh.p2p.dao.AbstractDao;
import org.ysh.p2p.dao.MemberDao;
import org.ysh.p2p.dao.MemberIntegralDao;
import org.ysh.p2p.dao.impl.MemberDaoImpl;
import org.ysh.p2p.dao.impl.MemberIntegralDaoImpl;
import org.ysh.p2p.model.Member;
import org.ysh.p2p.model.MemberIntegral;
import org.ysh.p2p.service.MemberIntegralService;
import org.ysh.p2p.util.StringUtil;

/**
 * 会员积分Service
 * @author yshin1992
 *
 */
public class MemberIntegralServiceImpl extends AbstractServiceImpl<MemberIntegral> implements MemberIntegralService{

	private MemberDao memberDao = new MemberDaoImpl();
	
	private MemberIntegralDao memberIntegralDao = new MemberIntegralDaoImpl();
	
	public AbstractDao<MemberIntegral> getDao() {
		// TODO Auto-generated method stub
		return memberIntegralDao;
	}

	private boolean checkIsUseIntegral(){
		return true;
	}
	
	public MemberIntegral initMemberIntegral(String memberId) {
		MemberIntegral mi=null;
		try {
			mi = new MemberIntegral();
			mi.setMemberId(memberId);
			mi = memberIntegralDao.query(mi, MemberIntegral.class);
			if(mi != null){
				return mi;
			}else{
				Member member = memberDao.findByUuid(memberId, Member.class);
				if(null == member){
					throw new RuntimeException("未查询到会员信息");
				}
				
				mi = new MemberIntegral();
				mi.setUuid(StringUtil.generateUuid());
				mi.setCreateTime(member.getRegistTime());
				mi.setUpdateTime(member.getRegistTime());
				mi.setMemberId(memberId);
				mi.setMember(member);
				mi.setTotal(0);
				mi.setMaxInvestAmount(BigDecimal.ZERO);
				mi.setIntegralVal(0);
				mi.setUsedAmount(BigDecimal.ZERO);
				mi.setUsedValue(0);
				
				memberIntegralDao.add(mi, MemberIntegral.class);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mi;
	}

	public void registerGiveIntegral(String memberId) {
		//未开启积分设置
		if(!checkIsUseIntegral()){
			return;
		}
		
		try{
			//TODO
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public void loginGiveIntegral(String memberId, boolean isFirstLoginInToday) {
		//未开启积分设置
		if(!checkIsUseIntegral()){
			return;
		}
		
		try{
			//TODO
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
