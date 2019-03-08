package org.ysh.p2p.service.impl;

import org.ysh.p2p.dao.AbstractDao;
import org.ysh.p2p.dao.CategoryAttrDao;
import org.ysh.p2p.dao.impl.CategoryAttrDaoImpl;
import org.ysh.p2p.model.CategoryAttr;
import org.ysh.p2p.service.CategoryAttrService;

public class CategoryAttrServiceImpl extends AbstractServiceImpl<CategoryAttr> implements CategoryAttrService{

	private CategoryAttrDao attrDao = new CategoryAttrDaoImpl();
	
	public AbstractDao<CategoryAttr> getDao() {
		return attrDao;
	}

}
