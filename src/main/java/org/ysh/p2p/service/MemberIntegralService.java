package org.ysh.p2p.service;

import org.ysh.p2p.model.MemberIntegral;
import org.ysh.p2p.vo.MemberIntegralTitleDto;
import org.ysh.p2p.vo.PageRequest;
import org.ysh.p2p.vo.PageResponse;

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
	
	/**
	 * 查询积分管理的统计项
	 * @param keyword
	 * @param queryStart
	 * @param queryEnd
	 * @return
	 */
	public MemberIntegralTitleDto queryMemberIntegralTitleDto(String keyword,String queryStart,String queryEnd);
	
	/**
	 * 积分管理页面分页查询
	 * @param pageRequeset
	 * @param keyword
	 * @param queryStart
	 * @param queryEnd
	 * @return
	 */
	public PageResponse<MemberIntegral> queryByPage(PageRequest pageRequest,String keyword,String queryStart,String queryEnd);
}
