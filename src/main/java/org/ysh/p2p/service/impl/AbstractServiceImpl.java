package org.ysh.p2p.service.impl;

import java.io.Serializable;

import org.ysh.p2p.service.AbstractService;

public abstract class AbstractServiceImpl<T extends Serializable> implements AbstractService<T> {

	public void add(T t, Class<T> clazz) {
		try {
			getDao().add(t, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteByUuid(T t, Class<T> clazz) {
		try {
			getDao().deleteByUuid(t, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(T t, Class<T> clazz) {
		try {
			getDao().update(t, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public T query(T t, Class<T> clazz) {
		try {
			return getDao().query(t, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public T findByUuid(String uuid,Class<T> clazz){
		try {
			return getDao().findByUuid(uuid	, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
