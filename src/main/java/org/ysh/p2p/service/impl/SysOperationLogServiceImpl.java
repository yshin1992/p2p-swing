package org.ysh.p2p.service.impl;

import java.util.Date;

import org.ysh.p2p.dao.AbstractDao;
import org.ysh.p2p.dao.SysOperationLogDao;
import org.ysh.p2p.dao.impl.SysOperationLogDaoImpl;
import org.ysh.p2p.model.SysOperationLog;
import org.ysh.p2p.model.SysUser;
import org.ysh.p2p.service.SysOperationLogService;
import org.ysh.p2p.util.StringUtil;

public class SysOperationLogServiceImpl extends AbstractServiceImpl<SysOperationLog> implements SysOperationLogService{

	private SysOperationLogDao sysOperationLogDao = new SysOperationLogDaoImpl();
	
	public AbstractDao<SysOperationLog> getDao() {
		return sysOperationLogDao;
	}

	public SysOperationLog getSysLogByModifySystemConfig(SysUser operator,
			String modifyContent, String oldContent) {
		SysOperationLog sysOperationLog = new SysOperationLog();
		sysOperationLog.setLogId(StringUtil.generateUuid());
		sysOperationLog.setOperateObj("OperateObj");
		sysOperationLog.setOperateObjName("系统配置");// 产品名称
		sysOperationLog.setOperateType(SysOperationLog.OPERATE_TYPE_UPDATE);
		sysOperationLog.setOperatorId(operator.getUuid());// 操作人ID
		sysOperationLog.setOperatorAccount(operator.getUserCd());// 操作人账号
		sysOperationLog.setOperatorType("20");// 操作人类型 20:表示后台操作人员
		sysOperationLog.setOperateContent(modifyContent);// 操作内容
		sysOperationLog.setOperateObjType(SysOperationLog.OBJ_TYPE_OTHER);// 操作对象类型
		sysOperationLog.setOperateTime(new Date());// 操作时间
		sysOperationLog.setRemark(oldContent.length() <= 200 ? oldContent : oldContent.substring(0, 199));// 备注
		return sysOperationLog;
	}

}
