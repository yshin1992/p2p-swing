package org.ysh.p2p.service;

import java.util.List;

import org.ysh.p2p.model.Category;

public interface CategoryService extends AbstractService<Category> {

	public List<Category> findAll();
	
}
