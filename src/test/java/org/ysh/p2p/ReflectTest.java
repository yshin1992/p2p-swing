package org.ysh.p2p;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.ysh.p2p.model.SysUser;
import org.ysh.p2p.util.ReflectionUtil;


public class ReflectTest extends TestCase {

	public void testReflect() throws Exception{
		
		List<Field> classFields = ReflectionUtil.getClassFields(SysUser.class);
		for(Field f : classFields){
			System.out.println(f.getName());
		}
		
		SysUser sysUser = new SysUser();
		Class<SysUser> clazz = SysUser.class;
		ReflectionUtil.setFieldValue("updateTime", new Date(), clazz, sysUser);
		
		System.out.println(sysUser.getUpdateTimeStr());
	}
	
	
}
