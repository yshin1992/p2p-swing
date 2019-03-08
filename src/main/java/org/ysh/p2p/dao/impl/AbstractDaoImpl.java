package org.ysh.p2p.dao.impl;

import org.ysh.p2p.dao.AbstractDao;
import org.ysh.p2p.model.BaseModel;
import org.ysh.p2p.util.DaoUtil;
import org.ysh.p2p.util.ReflectionUtil;

public abstract class AbstractDaoImpl<T extends BaseModel> implements AbstractDao<T> {

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

	public T findByUuid(String uuid, Class<T> clazz) throws Exception{
		T instance = clazz.newInstance();
		ReflectionUtil.setFieldValue("uuid", uuid, clazz, instance);
		return this.query(instance, clazz);
	}
	
}
