package org.ysh.p2p.support;

import java.util.EventObject;

/**
 * 登录成功事件
 * @author yshin1992
 *
 */
public class LoginSuccessEvent extends EventObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2692148541341347326L;

	
	private boolean isFirstLoginInToday;
	

	//是否是今日首次登录
	public boolean isFirstLoginInToday() {
		return isFirstLoginInToday;
	}

	public LoginSuccessEvent(String memberId,boolean isFirstLoginInToday) {
		super(memberId);
		this.isFirstLoginInToday = isFirstLoginInToday;
	}

}
