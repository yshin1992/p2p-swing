package org.ysh.p2p.dao.impl;

import org.ysh.p2p.dao.AbstractDao;
import org.ysh.p2p.util.DaoUtil;

public abstract class AbstractDaoImpl<T> implements AbstractDao<T> {

	public void add(T t, Class<T> clazz) throws Exception {
		DaoUtil.getInstance().addObject(t, clazz);
	}

	public void deleteByUuid(T t, Class<T> clazz) throws Exception {
		DaoUtil.getInstance().deleteByUuid(t, clazz);
	}

	public void update(T t, Class<T> clazz) throws Exception {
		DaoUtil.getInstance().update(t, clazz);
	}

	public T query(T t, Class<T> clazz) throws Exception {
		return DaoUtil.getInstance().queryForObject(t, clazz);
	}

	
	
}
