package org.ysh.p2p.support.listener;

import java.util.logging.Logger;

import org.ysh.p2p.annotation.EventListener;
import org.ysh.p2p.service.MemberIntegralService;
import org.ysh.p2p.service.impl.MemberIntegralServiceImpl;
import org.ysh.p2p.support.RegisterSuccessEvent;
import org.ysh.p2p.util.LogUtil;

/**
 * 注册成功事件listener
 * @author yshin1992
 *
 */
@EventListener
public class RegisterInfoListener implements ApplicationListener<RegisterSuccessEvent> {

	private MemberIntegralService memberIntegralService = new MemberIntegralServiceImpl();
	
	private Logger logger = LogUtil.getLogger(this);
	
	public void onApplicationEvent(RegisterSuccessEvent event) {
		logger.warning("会员注册成功后获得积分=====开始,memberId:"+ event.getSource());
		try {
			// 初始化会员积分总表记录
			memberIntegralService.initMemberIntegral((String)event.getSource());
			memberIntegralService.registerGiveIntegral((String)event.getSource());
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.warning("会员注册成功后获得积分=====结束");
		
	}


}
