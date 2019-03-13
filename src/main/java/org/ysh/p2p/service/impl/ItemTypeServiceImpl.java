package org.ysh.p2p.service.impl;

import org.ysh.p2p.dao.AbstractDao;
import org.ysh.p2p.dao.ItemTypeDao;
import org.ysh.p2p.dao.impl.ItemTypeDaoImpl;
import org.ysh.p2p.model.ItemType;
import org.ysh.p2p.service.ItemTypeService;
import org.ysh.p2p.util.StringUtil;
import org.ysh.p2p.vo.PageRequest;
import org.ysh.p2p.vo.PageResponse;

public class ItemTypeServiceImpl extends AbstractServiceImpl<ItemType> implements ItemTypeService {

	private ItemTypeDao itemTypeDao = new ItemTypeDaoImpl();
	
	public AbstractDao<ItemType> getDao() {
		return itemTypeDao;
	}

	public PageResponse<ItemType> queryByPage(PageRequest request,
			String keyword) {
		return itemTypeDao.queryByPage(request, keyword);
	}

	public void add(ItemType itemType) {
		if(StringUtil.isEmpty(itemType.getItemTypeCd())){
			String maxCd = itemTypeDao.getCategoryMaxCd(itemType.getFeeType());
			String itemTypeCd = String.valueOf(Integer.valueOf(maxCd) + 1);
			itemType.setItemTypeCd(itemTypeCd);
		}
		super.add(itemType,ItemType.class);
	}

	
}
