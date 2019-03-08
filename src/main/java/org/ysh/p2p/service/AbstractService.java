package org.ysh.p2p.service;

import org.ysh.p2p.dao.AbstractDao;
import org.ysh.p2p.model.BaseModel;

public interface AbstractService<T extends BaseModel> {
	
	public void add(T t,Class<T> clazz);
	
	public void deleteByUuid(T t,Class<T> clazz);
	
	public void update(T t,Class<T> clazz);
	
	public T query(T t,Class<T> clazz);
	
	public T findByUuid(String uuid,Class<T> clazz);
	 
	public AbstractDao<T> getDao();
}
