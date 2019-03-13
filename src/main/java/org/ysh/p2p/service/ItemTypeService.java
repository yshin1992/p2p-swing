package org.ysh.p2p.service;

import org.ysh.p2p.model.ItemType;
import org.ysh.p2p.vo.PageRequest;
import org.ysh.p2p.vo.PageResponse;

public interface ItemTypeService extends AbstractService<ItemType> {

	public PageResponse<ItemType> queryByPage(PageRequest request,String keyword);
	
	public void add(ItemType itemType);
}
