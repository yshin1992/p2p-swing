package org.ysh.p2p.support;

import java.util.EventObject;

import org.ysh.p2p.model.SysOperationLog;

/**
 * 后台操作日志EventObject
 * @author yanshuai
 *
 * @param <SysOperationLog>
 */
public class BackstageOperationSuccessEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8570336749030169387L;

	public BackstageOperationSuccessEvent(SysOperationLog source) {
		super(source);
	}

}
