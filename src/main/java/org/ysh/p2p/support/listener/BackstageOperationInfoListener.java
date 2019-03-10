package org.ysh.p2p.support.listener;

import java.util.logging.Logger;

import org.ysh.p2p.model.SysOperationLog;
import org.ysh.p2p.service.SysOperationLogService;
import org.ysh.p2p.service.impl.SysOperationLogServiceImpl;
import org.ysh.p2p.support.BackstageOperationSuccessEvent;
import org.ysh.p2p.util.LogUtil;

/**
 * 后台操作日志Listener
 * @author yanshuai
 *
 */
public class BackstageOperationInfoListener implements ApplicationListener<BackstageOperationSuccessEvent> {

	private Logger logger = LogUtil.getLogger(this);
	
	private SysOperationLogService sysOperationLogService = new SysOperationLogServiceImpl();
	
	public void onApplicationEvent(BackstageOperationSuccessEvent event) {
		logger.warning("开始存储操作日志信息。。。。");
		SysOperationLog log = (SysOperationLog)event.getSource();
		sysOperationLogService.add(log,SysOperationLog.class);
		logger.warning("存储操作日志信息完成。。。。");
	}


}
