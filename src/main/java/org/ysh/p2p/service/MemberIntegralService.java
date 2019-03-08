package org.ysh.p2p.service;

import org.ysh.p2p.model.MemberIntegral;

public interface MemberIntegralService extends AbstractService<MemberIntegral> {

	/**
	 * 初始化会员积分
	 * @param memberId
	 * @return
	 */
	public MemberIntegral initMemberIntegral(String memberId);
	
	/**
	 * 注册赠积分
	 * @param memberId
	 */
	public void registerGiveIntegral(String memberId);
	
	/**
	 * 每日首次登录获得积分
	 * @param memberId
	 * @param isFirstLoginInToday
	 */
	public void loginGiveIntegral(String memberId, boolean isFirstLoginInToday);
}
