package org.ysh.p2p.support;

import java.util.EventObject;

/**
 * 注册成功事件
 * @author yshin1992
 *
 */
public class RegisterSuccessEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3982177648695397624L;

	
	public RegisterSuccessEvent(String source) {
		super(source);
	}

}
