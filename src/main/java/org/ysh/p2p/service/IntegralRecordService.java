package org.ysh.p2p.service;

import org.ysh.p2p.model.IntegralRecord;
import org.ysh.p2p.model.Member;
import org.ysh.p2p.vo.PageRequest;
import org.ysh.p2p.vo.PageResponse;

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
	
	/**
	 * 分页查询积分记录明细
	 * @param pageRequest
	 * @param memberId
	 * @param queryStart
	 * @param queryEnd
	 * @return
	 */
	public PageResponse<IntegralRecord> queryByPage(PageRequest pageRequest,
			String memberId,String flag,String queryStart, String queryEnd);

}
