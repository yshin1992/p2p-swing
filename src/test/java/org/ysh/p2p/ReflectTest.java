package org.ysh.p2p;

import java.lang.reflect.Field;
import java.util.List;

import junit.framework.TestCase;

import org.ysh.p2p.model.SysUser;
import org.ysh.p2p.util.ReflectionUtil;

public class ReflectTest extends TestCase {

	public void testReflect(){
		
		List<Field> classFields = ReflectionUtil.getClassFields(SysUser.class);
		for(Field f : classFields){
			System.out.println(f.getName());
		}
	}
	
	
}
