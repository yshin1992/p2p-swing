package org.ysh.p2p.service;

import org.ysh.p2p.model.IntegralRecord;
import org.ysh.p2p.model.Member;

public interface IntegralRecordService extends AbstractService<IntegralRecord>{ 
	
	/**
	 * 注册获得积分
	 * 
	 * @param member
	 * @param integralVal
	 */
	void registerGiveIntegral(Member member, Integer integralVal) throws Exception;

	/**
	 * 推荐好友注册得积分
	 * 
	 * @param registerMember
	 * @param recommendMember
	 * @param integralVal
	 */
	void recommendedRegisterGiveIntegral(Member registerMember, Member recommendMember, Integer integralVal) throws Exception;

	/**
	 * 登录获得积分
	 * 
	 * @param member
	 * @param integralVal
	 */
	void loginGiveIntegral(Member member, Integer integralVal) throws Exception;

}
