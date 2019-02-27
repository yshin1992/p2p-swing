package org.ysh.p2p.dao;


public interface AbstractDao<T> {
	
	public void add(T t,Class<T> clazz) throws Exception;
	
	public void deleteByUuid(T t,Class<T> clazz) throws Exception;
	
	public void update(T t,Class<T> clazz) throws Exception;
	
	public T query(T t,Class<T> clazz) throws Exception;
}
