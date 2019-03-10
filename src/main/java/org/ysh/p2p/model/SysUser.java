package org.ysh.p2p.model;

import java.util.Date;

import org.ysh.p2p.annotation.Column;
import org.ysh.p2p.annotation.Table;

/**
 * 使用管理系统的操作人员
 * @author yshin1992
 *
 */
@Table(name="sys_user")
public class SysUser extends BaseModel {

	private static final long serialVersionUID = -2807814650096629711L;

	/**
	 * 用户账户（登录名）
	 */
	@Column(name="userCd")
	private String userCd;
	
	/**
	 * 经过加密算法后的密码
	 */
	@Column(name="password")
	private String password;
	
	/**
	 * 电子邮件地址
	 */
	@Column(name="email")
	private String email;
	
	/**
	 * 上次登录的IP
	 */
	@Column(name="lastLoginIp")
	private String lastLoginIp;
	
	/**
	 * 上次登录的时间
	 */
	@Column(name="lastLoginTime")
	private Date lastLoginTime;
	
	/**
	 * 登录次数
	 */
	@Column(name="loginCounts")
	private Integer loginCounts = 0;
	
	/**
	 * 是否为管理员
	 * 0 非管理员
	 * 1 管理员
	 */
	@Column(name="isAdmin")
	private Integer isAdmin = 0;

	public String getUserCd() {
		return userCd;
	}

	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getLoginCounts() {
		return loginCounts;
	}

	public void setLoginCounts(Integer loginCounts) {
		this.loginCounts = loginCounts;
	}

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "SysUser [userCd=" + userCd + ", password=" + password
				+ ", email=" + email + ", lastLoginIp=" + lastLoginIp
				+ ", lastLoginTime=" + lastLoginTime + ", loginCounts="
				+ loginCounts + ", isAdmin=" + isAdmin + "]" + super.toString();
	}

	/**
	 * 重写hashCode方法
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + getUuid().hashCode();
		return result;
	}

	/**
	 * 重写equals方法
	 */
	@Override
	public boolean equals(Object obj) {
		//判断是否是同一引用
		if(this == obj) return true;
		//判断obj是否为null
		if(null == obj) return false;
		//判断this和obj是否是同一类
		if(this.getClass() != obj.getClass()) return false;
		
		//将obj转换为相应的类，并比较对应的域
		SysUser other = (SysUser)obj;
		if(this.getUuid() == other.getUuid()){
			return true;
		}
		return false;
	}
	
	
}
