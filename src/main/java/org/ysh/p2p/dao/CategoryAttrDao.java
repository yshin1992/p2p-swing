package org.ysh.p2p.dao;

import java.util.List;

import org.ysh.p2p.model.CategoryAttr;

public interface CategoryAttrDao extends AbstractDao<CategoryAttr> {

	public abstract List<CategoryAttr> findByCategoryCd(String paramString);
	
	public abstract List<CategoryAttr> findByCategoryId(String paramString);
	
	public abstract CategoryAttr findByAttrCd(String paramString);
	
	public abstract void updateCategoryAttr(String paramString1, String paramString2, String paramString3);
	
}
