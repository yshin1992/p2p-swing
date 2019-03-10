package org.ysh.p2p.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.ysh.p2p.annotation.Column;
import org.ysh.p2p.annotation.Table;
import org.ysh.p2p.util.StringUtil;

/**
 * 系统操作日志对象
 * @author yanshuai
 *
 */
@Table(name="sysoperationlog")
public class SysOperationLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7147995331054609147L;
	/**
	 * 操作类型：1：增加/新建
	 */
	public static final int OPERATE_TYPE_ADD = 1;
	/**
	 * 操作类型：2：删除/废弃
	 */
	public static final int OPERATE_TYPE_DEL = 2;
	/**
	 * 操作类型：3：修改/编辑/状态迁移
	 */
	public static final int OPERATE_TYPE_UPDATE = 3;
	/**
	 * 操作类型：4：查询/导出
	 */
	public static final int OPERATE_TYPE_QUERY = 4;

	/**
	 * 操作对象类型：1：项目
	 */
	public static final String OBJ_TYPE_PRODUCT = "1";
	/**
	 * 操作对象类型：2：会员
	 */
	public static final String OBJ_TYPE_MEMBER = "2";
	/**
	 * 操作对象类型：3：订单
	 */
	public static final String OBJ_TYPE_ORDER = "3";
	/**
	 * 操作对象类型：4：企业客户
	 */
	public static final String OBJ_TYPE_COMPANY_MEMBER = "4";
	/**
	 * 操作对象类型：2.1：身份证
	 */
	public static final String OBJ_TYPE_IDCARD = "2.1";
	/**
	 * 操作对象类型：2.2：手机
	 */
	public static final String OBJ_TYPE_PHONE = "2.2";
	/**
	 * 操作对象类型：2.3：银行卡号
	 */
	public static final String OBJ_TYPE_BANKCARD = "2.3";
	/**
	 * 操作对象类型：9：其他
	 */
	public static final String OBJ_TYPE_OTHER = "9";

	@Column(name="logId")
	private String logId;
	
	@Column(name="createBy")
	private String createBy = "system";
	
	@Column(name="createTime")
	private Date createTime = new Date();

	/**
	 * 操作类型。1：增加/新建、2：删除/废弃、3：修改/编辑/状态迁移、4：查询/导出
	 */
	@Column(name="operateType")
	private Integer operateType;

	public String getOperateTypeNm() {
		if (StringUtil.isEmpty(operateType)) {
			return "";
		}
		switch (operateType) {
		case 1:
			return "新增";
		case 2:
			return "删除";
		case 3:
			return "编辑";
		case 4:
			return "导出";
		default:
			return "";
		}
	}

	/**
	 * 操作对象
	 */
	@Column(name = "operateObj")
	private String operateObj;

	/**
	 * 操作对象类型。1：项目、2：会员、3：订单、4:企业客户 2.1：身份证、2.2：手机、2.3：银行卡号 9：其他
	 */
	@Column(name = "operateObjType")
	private String operateObjType;

	public String getOperateObjTypeNm() {
		if (StringUtil.isEmpty(operateObjType)) {
			return "";
		}
		if (operateObjType.equals("1")) {
			return "项目数据";
		} else if (operateObjType.equals("2")) {
			return "会员数据";
		} else if (operateObjType.equals("3")) {
			return "订单数据";
		} else if (operateObjType.equals("2.1")) {
			return "会员-->身份证";
		} else if (operateObjType.equals("2.2")) {
			return "会员-->手机";
		} else if (operateObjType.equals("2.3")) {
			return "会员-->银行卡号";
		} else if (operateObjType.equals("4")) {
			return "企业客户";
		} else if (operateObjType.equals("9")) {
			return "其他";
		} else {
			return "";
		}
	}

	/**
	 * 操作对象名
	 */
	@Column(name="operateObjName")
	private String operateObjName;

	/**
	 * 操作内容
	 */
	@Column(name = "operateContent")
	private String operateContent;

	/**
	 * 操作人ID
	 */
	@Column(name = "operatorId")
	private String operatorId;

	/**
	 * 操作人账号
	 */
	@Column(name = "operatorAccount")
	private String operatorAccount;

	/**
	 * 操作人用户类型
	 */
	@Column(name="operatorType")
	private String operatorType;

	/**
	 * 操作时间
	 */
	@Column(name="operateTime")
	private Date operateTime;

	public String getOperateTimeStr() {
		if (StringUtil.isEmpty(getOperateTime()))
			return "";
		else
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(getOperateTime());
	}

	/**
	 * 备注
	 */
	@Column(name = "remark")
	private String remark;

	public Integer getOperateType() {
		return operateType;
	}

	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}

	public String getOperateObj() {
		return operateObj;
	}

	public void setOperateObj(String operateObj) {
		this.operateObj = operateObj;
	}

	public String getOperateObjName() {
		return operateObjName;
	}

	public void setOperateObjName(String operateObjName) {
		this.operateObjName = operateObjName;
	}

	public String getOperateContent() {
		return operateContent;
	}

	public void setOperateContent(String operateContent) {
		this.operateContent = operateContent;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorAccount() {
		return operatorAccount;
	}

	public void setOperatorAccount(String operatorAccount) {
		this.operatorAccount = operatorAccount;
	}

	public String getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOperateObjType() {
		return operateObjType;
	}

	public void setOperateObjType(String operateObjType) {
		this.operateObjType = operateObjType;
	}
	
	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public SysOperationLog(String objId, String objNm, String operateContent,
			Integer operateType, String operateObjType, String remark, SysUser user) {
		this.logId = StringUtil.generateUuid();
		this.operateObj = objId;
		this.operateObjName = objNm;
		this.operateType = operateType;
		this.operatorId = user.getUuid();
		this.operatorAccount = user.getUserCd();
		this.operatorType = "20";// 操作人类型 20:表示后台操作人员
		this.operateContent = operateContent;
		this.operateObjType = operateObjType;
		this.operateTime = new Date();
		this.remark = remark;
	}

	public SysOperationLog() {
	}
	
	
}
