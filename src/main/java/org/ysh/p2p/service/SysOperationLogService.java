package org.ysh.p2p.service;

import org.ysh.p2p.model.SysOperationLog;
import org.ysh.p2p.model.SysUser;

public interface SysOperationLogService extends AbstractService<SysOperationLog> {

	/**
	 * 修改配置记录操作日志
	 * 
	 * @param operator
	 *            操作人员对象
	 * @param modifyContent
	 *            配置修改前的值组合 不能超过200字符
	 * @param oldContent
	 *            配置修改后的值组合 不能超过200字符
	 */
	SysOperationLog getSysLogByModifySystemConfig(SysUser operator, String modifyContent, String oldContent);
}
