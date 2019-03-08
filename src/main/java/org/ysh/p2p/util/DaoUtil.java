package org.ysh.p2p.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import org.ysh.p2p.annotation.Column;
import org.ysh.p2p.annotation.Table;

/**
 * Dao工具类
 * 包括获取数据库连接和关闭连接
 * @author yshin1992
 *
 */
public class DaoUtil {

	private static final Logger Log = Logger.getLogger(DaoUtil.class.getName());
	
	private static final ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
	/**
	 * 连接Mysql的驱动类
	 */
	private static final String DRIVER_CLASS;
	
	/**
	 * 连接的数据库地址
	 */
	private static final String DATABASE_URL;
	
	/**
	 * 数据库的用户名和密码
	 */
	private static final String DATABASE_USERNAME;
	private static final String DATABASE_PASSWORD ;
	
	static{
		DRIVER_CLASS = bundle.getString("db.driverClass");
		DATABASE_URL = bundle.getString("db.connectUrl");
		DATABASE_USERNAME = bundle.getString("db.username");
		DATABASE_PASSWORD = bundle.getString("db.password");
	}
	
	/**
	 * 使用单例模式
	 */
	private static final DaoUtil instance = new DaoUtil();
	
	/**
	 * DaoUtil默认构造函数
	 * 用于加载数据库连接驱动类
	 */
	private DaoUtil(){
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			Log.warning("加载数据库驱动失败!");
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取DaoUitl的实例化对象
	 * @return
	 */
	public static DaoUtil getInstance(){
		return instance;
	}
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException
	{
		Log.info("获取数据库连接");
		return DriverManager.getConnection(DATABASE_URL,DATABASE_USERNAME,DATABASE_PASSWORD);
	}
	
	/**
	 * 关闭数据库连接
	 */
	public void closeConnection(Connection conn){
		if(null != conn){
			try {
				conn.close();
			} catch (SQLException e) {
				Log.warning("关闭数据库连接异常!");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 关闭结果集
	 * @param result
	 */
	public void closeResultSet(ResultSet result){
		if(null != result){
			try {
				result.close();
			} catch (SQLException e) {
				Log.warning("关闭结果集异常!");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 关闭statement
	 * @param statement
	 */
	public void closeStatement(Statement statement){
		if(null != statement)
		{
			try {
				statement.close();
			} catch (SQLException e) {
				Log.warning("关闭statement异常!");
				e.printStackTrace();
			}
		}
	}
	
	public <T> T queryForObject(T t,Class<T> clazz) throws Exception{
		Table tab = clazz.getAnnotation(Table.class);
		StringBuilder sql = new StringBuilder("select * from ").append(tab.name()).append(" where 1=1 ");
		
		List<Object> paramList = new ArrayList<Object>();
		List<Field> fields = ReflectionUtil.getClassFields(clazz);
		
		for(Field f : fields){
			Object val = ReflectionUtil.getFieldValue(f.getName(), clazz, t);
			if(null != val){
				//从注解中获取数据库中对应的字段名
				Column col = f.getAnnotation(Column.class);
				sql.append("and ").append(col.name()).append("=? ");
				paramList.add(val);
			}
		}
		return queryForObject(sql.toString(),paramList.toArray(), clazz);
				
	}
	
	public <T> List<T> queryForList(T t,Class<T> clazz) throws Exception{
		Table tab = clazz.getAnnotation(Table.class);
		StringBuilder sql = new StringBuilder("select * from ").append(tab.name()).append(" where 1=1 ");
		
		List<Object> paramList = new ArrayList<Object>();
		List<Field> fields = ReflectionUtil.getClassFields(clazz);
		
		for(Field f : fields){
			Object val = ReflectionUtil.getFieldValue(f.getName(), clazz, t);
			if(null != val){
				//从注解中获取数据库中对应的字段名
				Column col = f.getAnnotation(Column.class);
				sql.append("and ").append(col.name()).append("=? ");
				paramList.add(val);
			}
		}
		return queryForList(sql.toString(),paramList.toArray(), clazz);
				
	}
	
	public <T> List<T> queryForList(String sql,Object[] params,Class<T> clazz) throws Exception{
		Log.warning("SQL-->" + sql.toString());
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		List<T> resultList = new ArrayList<T>();
		try {
			conn=getConnection();
			
			pstm =conn.prepareStatement(sql);
			
			if(null != params && params.length > 0){
				for(int i=0;i<params.length;i++){
					pstm.setObject(i+1, params[i]);
				}
			}
			
			rs = pstm.executeQuery();
			
			while(rs.next()){
				T t = clazz.newInstance();
				List<Field> fields = ReflectionUtil.getClassFields(clazz);
				
				for(Field f : fields){
					//从注解中获取数据库中对应的字段名
					Column col = f.getAnnotation(Column.class);
					if(null != col){
						ReflectionUtil.setFieldValue(f.getName(), rs.getObject(col.name()), clazz, t);
					}
				}
				
				resultList.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			throw e;
		} finally{
			closeResultSet(rs);
			closeStatement(pstm);
			closeConnection(conn);
		}
		
		return resultList;
	}
	
	
	public <T> T queryForObject(String sql,Object[] params,Class<T> clazz) throws Exception{
		Log.warning("SQL-->" + sql.toString());
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		T t = null;
		try {
			conn=getConnection();
			
			pstm =conn.prepareStatement(sql);
			
			if(null != params && params.length > 0){
				for(int i=0;i<params.length;i++){
					pstm.setObject(i+1, params[i]);
				}
			}
			
			rs = pstm.executeQuery();
			
			while(rs.next()){
				t = clazz.newInstance();
				List<Field> fields = ReflectionUtil.getClassFields(clazz);
				
				for(Field f : fields){
					//从注解中获取数据库中对应的字段名
					Column col = f.getAnnotation(Column.class);
					if(null != col){
						ReflectionUtil.setFieldValue(f.getName(), rs.getObject(col.name()), clazz, t);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			throw e;
		} finally{
			closeResultSet(rs);
			closeStatement(pstm);
			closeConnection(conn);
		}
		
		return t;
	}
	
	public <T> void addObject(T t,Class<T> clazz) throws Exception{
		Table tab = clazz.getAnnotation(Table.class);
		StringBuilder sql = new StringBuilder("insert into ").append(tab.name()).append("(");
		List<Object> paramList = new ArrayList<Object>();
		
		List<Field> classFields = ReflectionUtil.getClassFields(clazz);
		
		for(Field f : classFields){
			Column col = f.getAnnotation(Column.class);
			if(null != col){
				sql.append(col.name()).append(",");
				paramList.add(ReflectionUtil.getFieldValue(f.getName(), clazz, t));
			}
		}
		
		sql.deleteCharAt(sql.length()-1).append(") ").append("values(");
		for(int i=0;i<paramList.size();i++){
			sql.append("?,");
		}
		sql.deleteCharAt(sql.length()-1).append(") ");
		
		Log.warning("SQL-->" + sql.toString());
		
		this.update(sql.toString(), paramList.toArray());
	}
	
	public <T> void update(T t,Class<T> clazz) throws Exception{
		Table tab = clazz.getAnnotation(Table.class);
		StringBuilder sql = new StringBuilder("update ").append(tab.name()).append(" set ");
		List<Object> paramList = new ArrayList<Object>();
		
		List<Field> classFields = ReflectionUtil.getClassFields(clazz);
		
		for(Field f : classFields){
			Column col = f.getAnnotation(Column.class);
			if(null != col){
				sql.append(col.name()).append("=?,");
				paramList.add(ReflectionUtil.getFieldValue(f.getName(), clazz, t));
			}
		}
		
		sql.deleteCharAt(sql.length()-1);
		
		sql.append(" where uuid=?");
		
		paramList.add(ReflectionUtil.getFieldValue("uuid", clazz, t));
		
		Log.warning("SQL-->" + sql.toString());
		
		this.update(sql.toString(), paramList.toArray());
	}
	
	public <T> void deleteByUuid(T t,Class<T> clazz) throws Exception{
		Table tab = clazz.getAnnotation(Table.class);
		StringBuilder sql = new StringBuilder("update ").append(tab.name()).append(" set status=? where uuid=?");
		
		Log.warning("SQL-->" + sql.toString());
		
		this.update(sql.toString(), 
				new Object[]{ReflectionUtil.getFieldValue("status", clazz, t),ReflectionUtil.getFieldValue("uuid", clazz, t)});
	}
	
	public void update(String sql,Object[] params) throws Exception{
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn= getConnection();
			
			pstm =conn.prepareStatement(sql);
			
			if(null != params && params.length > 0){
				for(int i=0;i<params.length;i++){
					pstm.setObject(i+1, params[i]);
				}
			}
			
			pstm.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally{
			closeStatement(pstm);
			closeConnection(conn);
		}
	}
	
	
}
