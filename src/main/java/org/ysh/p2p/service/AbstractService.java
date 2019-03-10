package org.ysh.p2p.service;

import java.io.Serializable;

import org.ysh.p2p.dao.AbstractDao;

public interface AbstractService<T extends Serializable> {
	
	public void add(T t,Class<T> clazz);
	
	public void deleteByUuid(T t,Class<T> clazz);
	
	public void update(T t,Class<T> clazz);
	
	public T query(T t,Class<T> clazz);
	
	public T findByUuid(String uuid,Class<T> clazz);
	 
	public AbstractDao<T> getDao();
}
