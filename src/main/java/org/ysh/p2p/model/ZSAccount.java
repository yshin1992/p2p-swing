package org.ysh.p2p.model;

import java.util.Date;

import org.ysh.p2p.annotation.Column;

/**
 * 浙商账户
 * @author yshin1992
 *
 */
public class ZSAccount extends BaseModel {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@Column(name="memberId")
	private String memberId;
	
	/**
	 * 开户的手机号
	 */
	@Column(name = "openTel")
	private String openTel;

	/**
	 * 开户时间
	 */
	@Column(name="openTime")
	private Date openTime = new Date();

	/**
	 * 绑定协议号
	 */
	@Column(name = "bindSerialNo")
	private String bindSerialNo;

	/**
	 * 存管账号
	 */
	@Column(name = "ecardNo")
	private String ecardNo;

	/**
	 * 存管账户类型
	 * 0存管e户  1商卡
	 */
	@Column(name = "type")
	private String type;

	/**
	 * 证件类型
	 * 00 其他 01 身份证 02 护照 03 军官证 04 组织机构代码号
	 */
	@Column(name="certType")
	private String certType;

	/**
	 * 证件号码
	 */
	@Column(name="certNo")
	private String certNo;

	/**
	 * 主账号
	 */
	@Column(name="openBankCard")
	private String openBankCard;

	/**
	 * 人行联行行号
	 */
	@Column(name="unionNumber")
	private String unionNumber;

	/**
	 * 用户平台编码
	 */
	@Column(name="platformUid")
	private String platformUid;

	/**
	 * 其他平台账户保留前六后四，中间以*代替
	 */
	@Column(name="otherAccno")
	private String otherAccno;

	@Column(name="bankName")
	private String bankName;
	/**
	 * 主账户他行人行联行行号
	 */
	@Column(name="branchNo")
	private String branchNo;

	/**
	 * 0失败1成功
	 */
	@Column(name="updateStatus")
	private Integer updateStatus;

	/**
	 * 变更失败原因
	 */
	@Column(name="failReason")
	private String failReason;

	/**
	 * 默认为”空”是直销银行变更，1为平台变更
	 */
	@Column(name="extension")
	private String extension;
	
	/**
	 * 客户类型 1 投资户 2 融资户
	 * 默认为投资户
	 */
	@Column(name="businessProperty")
	private Integer businessProperty;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getOpenTel() {
		return openTel;
	}

	public void setOpenTel(String openTel) {
		this.openTel = openTel;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public String getBindSerialNo() {
		return bindSerialNo;
	}

	public void setBindSerialNo(String bindSerialNo) {
		this.bindSerialNo = bindSerialNo;
	}

	public String getEcardNo() {
		return ecardNo;
	}

	public void setEcardNo(String ecardNo) {
		this.ecardNo = ecardNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getOpenBankCard() {
		return openBankCard;
	}

	public void setOpenBankCard(String openBankCard) {
		this.openBankCard = openBankCard;
	}

	public String getUnionNumber() {
		return unionNumber;
	}

	public void setUnionNumber(String unionNumber) {
		this.unionNumber = unionNumber;
	}

	public String getPlatformUid() {
		return platformUid;
	}

	public void setPlatformUid(String platformUid) {
		this.platformUid = platformUid;
	}

	public String getOtherAccno() {
		return otherAccno;
	}

	public void setOtherAccno(String otherAccno) {
		this.otherAccno = otherAccno;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchNo() {
		return branchNo;
	}

	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}

	public Integer getUpdateStatus() {
		return updateStatus;
	}

	public void setUpdateStatus(Integer updateStatus) {
		this.updateStatus = updateStatus;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public Integer getBusinessProperty() {
		return businessProperty;
	}

	public void setBusinessProperty(Integer businessProperty) {
		this.businessProperty = businessProperty;
	}

	@Override
	public String toString() {
		return "ZSAccount [memberId=" + memberId + ", openTel=" + openTel
				+ ", openTime=" + openTime + ", bindSerialNo=" + bindSerialNo
				+ ", ecardNo=" + ecardNo + ", type=" + type + ", certType="
				+ certType + ", certNo=" + certNo + ", openBankCard="
				+ openBankCard + ", unionNumber=" + unionNumber
				+ ", platformUid=" + platformUid + ", otherAccno=" + otherAccno
				+ ", bankName=" + bankName + ", branchNo=" + branchNo
				+ ", updateStatus=" + updateStatus + ", failReason="
				+ failReason + ", extension=" + extension
				+ ", businessProperty=" + businessProperty + "]"+super.toString();
	}
	
	
}
