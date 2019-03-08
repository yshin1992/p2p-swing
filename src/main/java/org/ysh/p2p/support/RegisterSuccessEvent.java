package org.ysh.p2p.support;

import java.util.EventObject;

/**
 * 注册成功事件
 * @author yshin1992
 *
 */
public class RegisterSuccessEvent<T> extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3982177648695397624L;

	
	public RegisterSuccessEvent(T source) {
		super(source);
	}
	
	@SuppressWarnings("unchecked")
	public T getSource(){
		return (T) super.getSource();
	}

}
