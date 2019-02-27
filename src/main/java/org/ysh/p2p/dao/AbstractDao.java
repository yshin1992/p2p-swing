package org.ysh.p2p.dao;

import java.sql.SQLException;

public abstract class AbstractDao<T> {
	
	public abstract void add(T t) throws SQLException;
	
	public abstract T delete(T t) throws SQLException;
	
	public abstract T update(T t) throws SQLException;
	
	public abstract T query(T t) throws SQLException;
}
