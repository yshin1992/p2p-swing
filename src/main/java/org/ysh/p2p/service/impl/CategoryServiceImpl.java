package org.ysh.p2p.service.impl;

import java.util.List;

import org.ysh.p2p.dao.AbstractDao;
import org.ysh.p2p.dao.CategoryDao;
import org.ysh.p2p.dao.impl.CategoryDaoImpl;
import org.ysh.p2p.model.Category;
import org.ysh.p2p.service.CategoryService;

public class CategoryServiceImpl extends AbstractServiceImpl<Category> implements CategoryService {

	private CategoryDao categoryDao = new CategoryDaoImpl();
	
	public AbstractDao<Category> getDao() {
		return categoryDao;
	}

	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	
}
