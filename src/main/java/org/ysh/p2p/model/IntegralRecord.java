package org.ysh.p2p.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.ysh.p2p.annotation.Column;
import org.ysh.p2p.annotation.Table;
import org.ysh.p2p.annotation.Transient;

/**
 * 积分记录表实体 用于保存会员的积分增减记录
 * @author yshin1992
 *
 */
@Table(name="integralrecord")
public class IntegralRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8858877691303120965L;

	@Column(name="recordId")
	private String recordId;
	
	@Column(name="memberId")
	private String memberId;
	
	@Column(name="createBy")
	private String createBy="system";
	
	@Column(name="createTime")
	private Date createTime;
	
	@Transient
	private Member member;
	
	/**
	 * 积分值 用于页面显示，便于查询
	 */
	@Column(name="integralVal")
	private Integer integralVal;
	
	/**
	 * 增减标志 1增加 0减少
	 */
	@Column(name="isAddFlag")
	private Integer isAddFlag;

	/**
	 * 状态 0未生效/失效 1有效
	 */
	@Column(name="status")
	private Integer status;

	/**
	 * 说明
	 */
	@Column(name="remark")
	private String remark;

	/**
	 * 失效时间
	 */
	@Column(name="failureTime")
	private Date failureTime;

	/**
	 * 是否永久有效 0不是 1永久有效
	 */
	@Column(name="isPermanent")
	private Integer isPermanent;

	/**
	 * 0注册:增加:关联memberId 1登录:增加:关联memberId 2推荐好友注册:增加:关联memberId 3投资成功:增加:关联投资订单
	 * 4推荐好友投资成功:增加:关联投资订单 5新投资额:增加:关联投资订单 6投资消耗:减少:关联投资订单
	 */
	@Column(name="objType")
	private Integer objType;
	/**
	 * 关联数据主键 一般是memberId或者orderId
	 */
	@Column(name = "objId")
	private String objId;

	/**
	 * 对应面值 不是消耗积分的场合,可空
	 */
	@Column(name="amount")
	private BigDecimal amount;

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Integer getIntegralVal() {
		return integralVal;
	}

	public void setIntegralVal(Integer integralVal) {
		this.integralVal = integralVal;
	}

	public Integer getIsAddFlag() {
		return isAddFlag;
	}

	public void setIsAddFlag(Integer isAddFlag) {
		this.isAddFlag = isAddFlag;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getFailureTime() {
		return failureTime;
	}

	public void setFailureTime(Date failureTime) {
		this.failureTime = failureTime;
	}

	public Integer getIsPermanent() {
		return isPermanent;
	}

	public void setIsPermanent(Integer isPermanent) {
		this.isPermanent = isPermanent;
	}

	public Integer getObjType() {
		return objType;
	}

	public void setObjType(Integer objType) {
		this.objType = objType;
	}

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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

	@Override
	public String toString() {
		return "IntegralRecord [recordId=" + recordId + ", memberId="
				+ memberId + ", createBy=" + createBy + ", createTime="
				+ createTime + ", member=" + member + ", integralVal="
				+ integralVal + ", isAddFlag=" + isAddFlag + ", status="
				+ status + ", remark=" + remark + ", failureTime="
				+ failureTime + ", isPermanent=" + isPermanent + ", objType="
				+ objType + ", objId=" + objId + ", amount=" + amount + "]";
	}

	
}
