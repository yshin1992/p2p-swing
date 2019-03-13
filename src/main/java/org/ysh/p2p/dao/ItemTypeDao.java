package org.ysh.p2p.dao;

import org.ysh.p2p.model.ItemType;
import org.ysh.p2p.vo.PageRequest;
import org.ysh.p2p.vo.PageResponse;

public interface ItemTypeDao extends AbstractDao<ItemType> {

	public PageResponse<ItemType> queryByPage(PageRequest request,String keyword);
	
	public String getCategoryMaxCd(String feeType);
}
