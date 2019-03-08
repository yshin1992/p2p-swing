package org.ysh.p2p.dao;

import java.util.List;

import org.ysh.p2p.model.Category;

public interface CategoryDao extends AbstractDao<Category> {
	public List<Category> findAll();
}
