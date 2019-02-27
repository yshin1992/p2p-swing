package org.ysh.p2p.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * Dao工具类
 * 包括获取数据库连接和关闭连接
 * @author yshin1992
 *
 */
public class DaoUtil {

	private static final ResourceBundle bundle = ResourceBundle.getBundle("cfg");
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
			LogUtil.getLogger(this).warning("加载数据库驱动失败!");
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
		LogUtil.getLogger(this).info("获取数据库连接");
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
				LogUtil.getLogger(this).warning("关闭数据库连接异常!");
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
				LogUtil.getLogger(this).warning("关闭结果集异常!");
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
				LogUtil.getLogger(this).warning("关闭statement异常!");
				e.printStackTrace();
			}
		}
	}
	
	public <T> T queryForObject(String sql,Object[] params,Class<T> clazz){
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
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			
		}
		
		return t;
	}
	
	
}
