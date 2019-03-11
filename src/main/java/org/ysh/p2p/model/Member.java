package org.ysh.p2p.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.ysh.p2p.annotation.Column;
import org.ysh.p2p.annotation.Table;
import org.ysh.p2p.annotation.Transient;
import org.ysh.p2p.util.DateUtil;

/**
 * 会员信息表
 * @author yshin1992
 *
 */
@Table(name="member")
public class Member extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1440915838778226670L;

	public static final Integer MEMBER_KIND_PERSONAL = 0;
	
	public static final Integer MEMBER_KIND_ENTERPRISE = 1;
	
	/***
	 * 0：限制登录
	 */
	public static final Integer MEMBER_STATUS_LIMIT = 0;
	/***
	 * 1：正常状态
	 */
	public static final Integer MEMBER_STATUS_NORMAL = 1;
	
	@Column(name="memberKind")
	private Integer memberKind;// 客户类型0:个人，1:企业

	@Column(name = "realCd")
	private String realCd;// 会员登录账号 规则:md5(md5(password)+realCd)

	@Column(name = "realNm")
	private String realNm;// 实际姓名

	@Column(name = "nickName")
	private String nickName;// 昵称

	@Column(name = "password")
	private String password;// 密码

	@Column(name = "idCard")
	private String idCard;// 身份证号码

	@Column(name = "bankNo")
	private String bankNo;// 验证银行卡

	@Column(name = "phone")
	private String phone;// 验证手机

	@Column(name = "email")
	private String email;// 验证邮箱

	@Column(name="level")
	private Integer level;// 会员等级1：普通会员，2：至尊会员（根据需要进行扩张）

	@Column(name = "registTime")
	private Date registTime;// 注册时间

	@Column(name = "lastLogin")
	private Date lastLogin;// 最近登录时间

	@Column(name="loginCount")
	private Integer loginCount;// 登录次数

	// 注册开始时间
	@Transient
	private Date registTimeFrom;

	// 注册结束时间
	@Transient
	private Date registTimeTo;

	// 登录开始时间
	@Transient
	private Date lastLoginFrom;

	// 登录结束时间
	@Transient
	private Date lastLoginTo;
	
	//浙商存管账户
	@Transient
	private List<ZSAccount> zsAccounts;

	/**
	 * 最后登录ip
	 */
	@Column(name="lastLoginIp")
	private String lastLoginIp;
	/*
	 * //认证方式1 ：身份证认证(实名认证) 2 ：银行卡认证 4 : 邮箱验证 8 : 手机验证 16 授权 32 浙商开户
	 * 当存在多种验证通过时把认证类型通过直接以整数的形式加起来即可 比如：1+2=3 表示身份认证审核通过、银行卡认证通过
	 */
	@Column(name = "auditType")
	private Integer auditType;
	
	@Column(name="promotionId")
	private String promotionId;// 推广id

	@Column(name = "memberIdZ")
	private String memberIdZ;// 推荐人id
	
	/**
	 * 渠道
	 */
	@Column(name="channel")
	private String channel;

	/**
	 * 活动
	 */
	@Column(name="activity")
	private String activity;

	public Integer getMemberKind() {
		return memberKind;
	}

	public void setMemberKind(Integer memberKind) {
		this.memberKind = memberKind;
	}

	public String getRealCd() {
		return realCd;
	}

	public void setRealCd(String realCd) {
		this.realCd = realCd;
	}

	public String getRealNm() {
		return realNm;
	}

	public void setRealNm(String realNm) {
		this.realNm = realNm;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public Date getRegistTimeFrom() {
		return registTimeFrom;
	}

	public void setRegistTimeFrom(Date registTimeFrom) {
		this.registTimeFrom = registTimeFrom;
	}

	public Date getRegistTimeTo() {
		return registTimeTo;
	}

	public void setRegistTimeTo(Date registTimeTo) {
		this.registTimeTo = registTimeTo;
	}

	public Date getLastLoginFrom() {
		return lastLoginFrom;
	}

	public void setLastLoginFrom(Date lastLoginFrom) {
		this.lastLoginFrom = lastLoginFrom;
	}

	public Date getLastLoginTo() {
		return lastLoginTo;
	}

	public void setLastLoginTo(Date lastLoginTo) {
		this.lastLoginTo = lastLoginTo;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Integer getAuditType() {
		return auditType;
	}

	public void setAuditType(Integer auditType) {
		this.auditType = auditType;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public List<ZSAccount> getZsAccounts() {
		return zsAccounts;
	}

	public void setZsAccounts(List<ZSAccount> zsAccounts) {
		this.zsAccounts = zsAccounts;
	}
	
	public String getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}

	public String getMemberIdZ() {
		return memberIdZ;
	}

	public void setMemberIdZ(String memberIdZ) {
		this.memberIdZ = memberIdZ;
	}
	
	public String getRegistTimeStr(){
		if(this.registTime == null){
			return "null";
		}
		return DateUtil.defaultFormat(this.registTime);
	}

	public String getLastLoginFormat() {
		if (lastLogin != null) {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(lastLogin);
		} else {
			return "";
		}
	}
	
	
	@Override
	public String toString() {
		return "Member [memberKind=" + memberKind + ", realCd=" + realCd
				+ ", realNm=" + realNm + ", nickName=" + nickName
				+ ", password=" + password + ", idCard=" + idCard + ", bankNo="
				+ bankNo + ", phone=" + phone + ", email=" + email + ", level="
				+ level + ", registTime=" + registTime + ", lastLogin="
				+ lastLogin + ", loginCount=" + loginCount
				+ ", registTimeFrom=" + registTimeFrom + ", registTimeTo="
				+ registTimeTo + ", lastLoginFrom=" + lastLoginFrom
				+ ", lastLoginTo=" + lastLoginTo + ", zsAccounts=" + zsAccounts
				+ ", lastLoginIp=" + lastLoginIp + ", auditType=" + auditType
				+ ", promotionId=" + promotionId + ", memberIdZ=" + memberIdZ
				+ ", channel=" + channel + ", activity=" + activity + "]"+super.toString();
	}
	
	
}
