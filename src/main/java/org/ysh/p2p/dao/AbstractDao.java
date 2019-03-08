package org.ysh.p2p.dao;

import org.ysh.p2p.model.BaseModel;


public interface AbstractDao<T extends BaseModel> {
	
	public void add(T t,Class<T> clazz) throws Exception;
	
	public void deleteByUuid(T t,Class<T> clazz) throws Exception;
	
	public void update(T t,Class<T> clazz) throws Exception;
	
	public T query(T t,Class<T> clazz) throws Exception;
	
	public T findByUuid(String uuid, Class<T> clazz) throws Exception;
}
