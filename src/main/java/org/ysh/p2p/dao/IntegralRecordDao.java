package org.ysh.p2p.dao;

import org.ysh.p2p.model.IntegralRecord;
import org.ysh.p2p.vo.PageRequest;
import org.ysh.p2p.vo.PageResponse;

public interface IntegralRecordDao extends AbstractDao<IntegralRecord> {

	public PageResponse<IntegralRecord> queryByPage(PageRequest pageRequest,String memberId,Integer isAddFlag,String queryStart,String queryEnd); 
	
}
