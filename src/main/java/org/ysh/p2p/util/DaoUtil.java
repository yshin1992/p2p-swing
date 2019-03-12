package org.ysh.p2p.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.ysh.p2p.annotation.Column;
import org.ysh.p2p.annotation.Table;

import com.mchange.v2.c3p0.DataSources;
import com.mchange.v2.c3p0.PooledDataSource;

/**
 * Dao工具类
 * 包括获取数据库连接和关闭连接
 * @author yshin1992
 *
 */
public class DaoUtil {

	private static final Logger Log = Logger.getLogger(DaoUtil.class.getName());
	
	private static DataSource ds;
	
	private static final String JDBC_DRIVER = "driverClass";

	private static final String JDBC_URL = "jdbcUrl";
	
	static{
		
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
	}

	static{
		initDBSource();
	}
	
	/**
	 * 初始化c3p0连接池
	 */
	private static final void initDBSource() {
		Properties c3p0Pro = new Properties();
		try {
			// 加载配置文件
			c3p0Pro.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		String drverClass = c3p0Pro.getProperty(JDBC_DRIVER);
		if (drverClass != null) {
			try {
				// 加载驱动类
				Class.forName(drverClass);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}

		Properties jdbcpropes = new Properties();
		Properties c3propes = new Properties();
		for (Object key : c3p0Pro.keySet()) {
			String skey = (String) key;
			if (skey.startsWith("c3p0.")) {
				c3propes.put(skey, c3p0Pro.getProperty(skey));
			} else {
				jdbcpropes.put(skey, c3p0Pro.getProperty(skey));
			}
		}

		try {
			// 建立连接池
			DataSource unPooled = DataSources.unpooledDataSource(c3p0Pro.getProperty(JDBC_URL), jdbcpropes);
			ds = DataSources.pooledDataSource(unPooled, c3propes);
			Log.warning("空闲的连接: " + ((PooledDataSource) ds).getNumIdleConnectionsDefaultUser());
			Log.warning("使用中的连接: " + ((PooledDataSource) ds).getNumBusyConnectionsDefaultUser());
		} catch (SQLException e) {
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
	 * 获取池化的dataSource对象
	 * 
	 * @return
	 */
	public static DataSource getDataSource() {
		return ds;
	}
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException
	{
		Log.info("获取数据库连接");
		final Connection conn = ds.getConnection();
		conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		return conn;
	}
	
	public static void closeDataSource(DataSource ds) {
		try {
			((PooledDataSource) ds).close();
			Log.info("释放连接池");
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	
	/**
	 * 查询记录的总数，用于分页查询
	 * @param SQL
	 * @param params
	 * @return
	 */
	public Long queryRecordCount(String SQL,Object[] params){
		Log.warning("SQL-->" + SQL);
		Log.warning("Params-->" + Arrays.toString(params));
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Long recordCount = 0L;
		try {
			conn=getConnection();
			
			pstm =conn.prepareStatement(SQL);
			
			if(null != params && params.length > 0){
				for(int i=0;i<params.length;i++){
					pstm.setObject(i+1, params[i]);
				}
			}
			
			rs = pstm.executeQuery();
			
			while(rs.next()){
				recordCount = rs.getLong(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			closeResultSet(rs);
			closeStatement(pstm);
			closeConnection(conn);
		}
		return recordCount;
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
		Log.warning("Params-->" + Arrays.toString(params));
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
		Object[] params = new Object[]{ReflectionUtil.getFieldValue("status", clazz, t),ReflectionUtil.getFieldValue("uuid", clazz, t)};
		Log.warning("Params-->" + Arrays.toString(params));
		this.update(sql.toString(), params);
	}
	
	public void update(String sql,Object[] params) throws Exception{
		Log.warning("SQL-->" + sql);
		Log.warning("Params-->" + Arrays.toString(params));
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
