package org.ysh.p2p;

import java.util.Arrays;
import java.util.Random;

public class FinalTest {

	private final int count ;
	{
		count = getCount();
	}
	
	
	private int getCount(){
		return new Random(100).nextInt();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Arrays.hashCode(args);
	}


}

abstract class AbstTest{
	private int ss;
	
	private void setSs(int ss){
		this.ss = ss;
	}
}