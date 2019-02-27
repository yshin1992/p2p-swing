package org.ysh.p2p;


import org.ysh.p2p.enums.Status;

import junit.framework.TestCase;

public class StatusTest extends TestCase{

	public void testValueOf(){
		Status s = Status.valueOf("ABNORMAL");
		System.out.println(s);
	}
	
}
