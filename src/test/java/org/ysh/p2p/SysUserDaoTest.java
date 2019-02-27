package org.ysh.p2p;

import java.util.Date;

import junit.framework.TestCase;

import org.ysh.p2p.dao.SysUserDao;
import org.ysh.p2p.dao.impl.SysUserDaoImpl;
import org.ysh.p2p.model.SysUser;
import org.ysh.p2p.util.LogUtil;
import org.ysh.p2p.util.StringUtil;

public class SysUserDaoTest extends TestCase {

	SysUserDao dao = new SysUserDaoImpl();
	
	public void testAdd(){
		SysUser user = new SysUser();
		user.setUuid(StringUtil.generateUuid());
		Date date = new Date();
		user.setCreateTime(date);
		user.setUpdateTime(date);
		user.setUserCd("admin");
		user.setEmail("yshin1992@163.com");
		user.setPassword(StringUtil.getMd5(StringUtil.getMd5("admin") + "admin"));
		
		try {
			dao.add(user, SysUser.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testQuery(){
		Date start = new Date();
		SysUser user = new SysUser();
		user.setUserCd("admin");
		user.setPassword(StringUtil.getMd5(StringUtil.getMd5("admin") + "admin"));
		user.setIsAdmin(1);
		try {
			user = dao.query(user, SysUser.class);
			System.out.println(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date end = new Date();
		LogUtil.getLogger(this).warning("execute time ->" + (end.getTime()-start.getTime()));
	}
	
	public void testUpdate(){
		Date start = new Date();
		SysUser user = new SysUser();
		user.setUserCd("admin");
		user.setPassword(StringUtil.getMd5(StringUtil.getMd5("admin") + "admin"));
		try {
			user = dao.query(user, SysUser.class);
			user.setIsAdmin(1);
			dao.update(user, SysUser.class);
			System.out.println(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date end = new Date();
		LogUtil.getLogger(this).warning("execute time ->" + (end.getTime()-start.getTime()));
	}
	
}
