package org.ysh.p2p.dao;

import java.io.Serializable;
import java.util.List;


public interface AbstractDao<T extends Serializable> {
	
	public void add(T t,Class<T> clazz) throws Exception;
	
	public void deleteByUuid(T t,Class<T> clazz) throws Exception;
	
	public void update(T t,Class<T> clazz) throws Exception;
	
	public T query(T t,Class<T> clazz) throws Exception;
	
	public T findByUuid(String uuid, Class<T> clazz) throws Exception;
	
	public T nativeQuery(String sql,Object[] params, Class<T> clazz) throws Exception;
	
	public void nativeUpdate(String sql,Object[] params) throws Exception;
	
	public List<T> queryList(T t,Class<T> clazz) throws Exception;
	
	public List<T> nativeQueryList(String sql,Object[] params,Class<T> clazz) throws Exception;
}
