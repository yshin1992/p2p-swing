package org.ysh.p2p.support.listener;

import java.util.logging.Logger;

import org.ysh.p2p.service.MemberIntegralService;
import org.ysh.p2p.service.impl.MemberIntegralServiceImpl;
import org.ysh.p2p.support.LoginSuccessEvent;
import org.ysh.p2p.util.LogUtil;

/**
 * 登录成功事件监听器
 * @author yshin1992
 *
 */
public class LoginInfoListener implements ApplicationListener<LoginSuccessEvent> {

	private final Logger  logger = LogUtil.getLogger(this);
	
	private MemberIntegralService memberIntegralService = new MemberIntegralServiceImpl(); 
	
	public void onApplicationEvent(LoginSuccessEvent event) {
		logger.warning("会员登录成功后获得积分=====开始,memberId:"+event.getSource());
		
		memberIntegralService.loginGiveIntegral((String)event.getSource(),event.isFirstLoginInToday());
		
		logger.warning("会员登录成功后获得积分=====结束");
	}

}
