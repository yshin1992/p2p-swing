package org.ysh.p2p.dao;

import org.ysh.p2p.model.MemberIntegral;
import org.ysh.p2p.vo.MemberIntegralTitleDto;
import org.ysh.p2p.vo.PageRequest;
import org.ysh.p2p.vo.PageResponse;

/**
 * 会员积分
 * @author yshin1992
 *
 */
public interface MemberIntegralDao extends AbstractDao<MemberIntegral> {

	/**
	 * 会员积分管理页面的统计数据
	 * @param keyword
	 * @param queryStart
	 * @param queryEnd
	 * @return
	 */
	public MemberIntegralTitleDto queryMemberIntegralTitle(String keyword,String queryStart,String queryEnd);
	
	/**
	 * 分页查询会员积分信息
	 * @param pageRequest
	 * @param keyword
	 * @param queryStart
	 * @param queryEnd
	 * @return
	 */
	public PageResponse<MemberIntegral> queryByPage(PageRequest pageRequest,String keyword,String queryStart,String queryEnd);
	
}
