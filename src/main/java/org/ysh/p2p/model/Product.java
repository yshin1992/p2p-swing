package org.ysh.p2p.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ysh.p2p.annotation.Column;
import org.ysh.p2p.annotation.Table;
import org.ysh.p2p.annotation.Transient;
import org.ysh.p2p.util.StringUtil;


@Table(name = "product")
public class Product extends BaseModel{

	private static final long serialVersionUID = 1L;

	public static final Integer STATUS_DRAFT = Integer.valueOf(10);

	public static final Integer STATUS_ONAUDIT = Integer.valueOf(11);

	public static final Integer STATUS_DISCARD = Integer.valueOf(12);

	public static final Integer STATUS_REVOKE = Integer.valueOf(13);

	public static final Integer STATUS_AUDIT_REJECT = Integer.valueOf(14);

	public static final Integer STATUS_AUDIT_FAILED = Integer.valueOf(15);
	
	public static final Integer STATUS_AUDITING = Integer.valueOf(16);//项目审核中

	public static final Integer STATUS_SCALE_ON = Integer.valueOf(31);

	public static final Integer STATUS_SCALE__REVOKE = Integer.valueOf(40);

	public static final Integer STATUS_ONLINE = Integer.valueOf(32);

	public static final Integer STATUS_SCALE_ONWAY = Integer.valueOf(30);

	public static final Integer STATUS_SCALEFULL_AUDITON = Integer.valueOf(60);

	public static final Integer STATUS_SCALEFAILED_ONDEAL = Integer.valueOf(61);

	public static final Integer STATUS_LOAN_ON = Integer.valueOf(80);
	
	public static final Integer STATUS_LOANING = Integer.valueOf(81); //放款中

	public static final Integer STATUS_REPAYMENT_ON = Integer.valueOf(90);
	
	public static final Integer STATUS_REPAYING = Integer.valueOf(91);//还款请求中

	public static final Integer STATUS_REPAYMENT_DONE = Integer.valueOf(100);

	public static final Integer STATUS_SCALEDISCARD_REFOUND_ON = Integer.valueOf(70);

	public static final Integer STATUS_SCALEDISCARD_REFOUND_DONE = Integer.valueOf(101);

	public static final Integer STATUS_SCALEFAILED_REFOUND_ON = Integer.valueOf(71);

	public static final Integer STATUS_SCALEREVOKE_REFOUND_ON = Integer.valueOf(73);

	public static final Integer STATUS_SCALEREVOKE_REFOUND_DONE = Integer.valueOf(103);

	public static final Integer STATUS_SCALEFAILED_REFOUND_DONE = Integer.valueOf(102);

	public static final Integer STATUS_SCALEFAILED = Integer.valueOf(50);
	
	/**
	 * 产品编号
	 */
	@Column(name="productCd")
	private String productCd;
	
	/**
	 * 产品名称
	 */
	@Column(name="productNm")
	private String productNm;
	
	/**
	 * 借款利率
	 */
	@Column(name="rate")
	private BigDecimal rate;
	
	/**
	 * 期数
	 */
	@Column(name="period")
	private Integer period;
	
	/**
	 * 期限类型
	 */
	@Column(name="periodType")
	private Integer periodType;
	
	/**
	 * 单份价格
	 */
	@Column(name="unitPrice")
	private BigDecimal unitPrice;
	
	@Column(name="份数")
	private Long quantity;
	
	@Column(name="已投份数")
	private Long castQuantity;
	
	/**
	 * 最小投资数量
	 */
	@Column(name="minTenderQuantity")
	private Long minTenderQuantity;
	
	/**
	 * 最大投资数量
	 */
	@Column(name="maxTenderQuantity")
	private Long maxTenderQuantity;
	
	/**
	 * 最小满标数量
	 */
	@Column(name="minFullQuantity")
	private Long minFullQuantity;
	
	/**
	 * 上线时间
	 */
	@Column(name="groundTime")
	private Date groundTime;
	
	/**
	 * 募集开始时间
	 */
	@Column(name="tenderStart")
	private Date tenderStart;
	
	/**
	 * 募集结束时间
	 */
	@Column(name="tenderEnd")
	private Date tenderEnd;
	
	/**
	 * 合同生效时间
	 */
	@Column(name="contractEffTime")
	private Date contractEffTime;
	
	/**
	 * 满标时间
	 */
	@Column(name="fullScaleTime")
	private Date fullScaleTime;
	
	/**
	 * 项目起息时间
	 */
	@Column(name="interestStartTime")
	private Date interestStartTime;
	
	/**
	 * 借款用途
	 */
	@Column(name="borrowUse")
	private Integer borrowUse;

	/**
	 * 借款用途名称
	 */
	@Column(name="borrowUseName")
	private String borrowUseName;

	/**
	 * 还款方式
	 */
	@Column(name="repayMethod")
	private Integer repayMethod;

	/**
	 * 标的类型
	 */
	@Column(name="tenderKind")
	private Integer tenderKind;

	/**
	 * 标的类型名称
	 */
	@Column(name="tenderKindName")
	private String tenderKindName;

	/**
	 * 业务类型
	 */
	@Column(name="businessType")
	private Integer businessType;

	/**
	 * 业务类型名称
	 */
	@Column(name="businessTypeNm")
	private String businessTypeNm;
	
	/**
	 * 会员ID
	 */
	@Column(name="memberId")
	private String memberId;
	
	/**
	 * 会员
	 * 这里留作保存或读取信息时使用
	 */
	@Transient
	private Member member;
	
	/**
	 * 担保信息
	 */
	@Transient
	private Safeguard safeguard;
	
	
	@Column(name = "productContent")
	private String productContent;

	/**
	 * 还款来源
	 */
	@Column(name = "repaySource")
	private String repaySource;

	/**
	 * 资金用途
	 */
	@Column(name = "fundUse")
	private String fundUse;

	/**
	 * 最小持有天数
	 */
	@Column(name = "minHoldDay")
	private Integer minHoldDay;

	/**
	 * 是否自动续投
	 */
	@Column(name="canAutobid")
	private Integer canAutobid;
	
	/**
	 * 奖励比例
	 */
	@Column(name="awardRate")
	private BigDecimal awardRate;
	
	/**
	 * 奖励金额
	 */
	@Column(name="awardAmount")
	private BigDecimal awardAmount;
	
	@Column(name="awardTotal")
	private BigDecimal awardTotal;
	
	@Column(name="awardFail")
	private Integer awardFail;
	
	@Column(name="amount")
	private BigDecimal amount;
	
	/**
	 * 项目是否代偿（默认为否）
	 * 0 未申请 1 已申请 2 代偿成功 3 代偿失败
	 */
	@Column(name="compensatory")
	private Integer compensatory = 0;
	
	@Column(name="compenInterest")
	private BigDecimal compenInterest;

	@Column(name="interestAlgorithm")
	private Integer interestAlgorithm;
	
	@Transient
	private String loanStatus;

	@Transient
	private boolean isGenerationRepayItemPlan = false;

	@Transient
	private Boolean homeRecomment;
	
	/**
	 * 产品认证信息
	 */
	@Transient
	private List<ProductAuthInfo> productAuthInfos = new ArrayList<ProductAuthInfo>();
	
	/**
	 * 附件列表
	 */
	@Transient
	private List<Attachment> attachments;
	
	/**
	 * 拍卖纪录列表
	 */
	@Transient
	private List<ProductPledge> productPledges = new ArrayList<ProductPledge>();
	

	public String getInterestAlgorithmName() {
		switch (this.interestAlgorithm.intValue()) {
		case 1:
			return "算头不算尾";
		case 2:
			return "算尾不算头";
		case 3:
			return "算头又算尾";
		}
		return "";
	}


	public String getProductCd() {
		return productCd;
	}


	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}


	public String getProductNm() {
		return productNm;
	}


	public void setProductNm(String productNm) {
		this.productNm = productNm;
	}


	public BigDecimal getRate() {
		return rate;
	}


	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}


	public Integer getPeriod() {
		return period;
	}


	public void setPeriod(Integer period) {
		this.period = period;
	}


	public Integer getPeriodType() {
		return periodType;
	}


	public void setPeriodType(Integer periodType) {
		this.periodType = periodType;
	}


	public BigDecimal getUnitPrice() {
		return unitPrice;
	}


	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}


	public Long getQuantity() {
		return quantity;
	}


	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}


	public Long getCastQuantity() {
		return castQuantity;
	}


	public void setCastQuantity(Long castQuantity) {
		this.castQuantity = castQuantity;
	}


	public Long getMinTenderQuantity() {
		return minTenderQuantity;
	}


	public void setMinTenderQuantity(Long minTenderQuantity) {
		this.minTenderQuantity = minTenderQuantity;
	}


	public Long getMaxTenderQuantity() {
		return maxTenderQuantity;
	}


	public void setMaxTenderQuantity(Long maxTenderQuantity) {
		this.maxTenderQuantity = maxTenderQuantity;
	}


	public Long getMinFullQuantity() {
		return minFullQuantity;
	}


	public void setMinFullQuantity(Long minFullQuantity) {
		this.minFullQuantity = minFullQuantity;
	}


	public Date getGroundTime() {
		return groundTime;
	}


	public void setGroundTime(Date groundTime) {
		this.groundTime = groundTime;
	}


	public Date getTenderStart() {
		return tenderStart;
	}


	public void setTenderStart(Date tenderStart) {
		this.tenderStart = tenderStart;
	}


	public Date getTenderEnd() {
		return tenderEnd;
	}


	public void setTenderEnd(Date tenderEnd) {
		this.tenderEnd = tenderEnd;
	}


	public Date getContractEffTime() {
		return contractEffTime;
	}


	public void setContractEffTime(Date contractEffTime) {
		this.contractEffTime = contractEffTime;
	}


	public Date getFullScaleTime() {
		return fullScaleTime;
	}


	public void setFullScaleTime(Date fullScaleTime) {
		this.fullScaleTime = fullScaleTime;
	}


	public Date getInterestStartTime() {
		return interestStartTime;
	}


	public void setInterestStartTime(Date interestStartTime) {
		this.interestStartTime = interestStartTime;
	}


	public Integer getBorrowUse() {
		return borrowUse;
	}


	public void setBorrowUse(Integer borrowUse) {
		this.borrowUse = borrowUse;
	}


	public String getBorrowUseName() {
		return borrowUseName;
	}


	public void setBorrowUseName(String borrowUseName) {
		this.borrowUseName = borrowUseName;
	}


	public Integer getRepayMethod() {
		return repayMethod;
	}


	public void setRepayMethod(Integer repayMethod) {
		this.repayMethod = repayMethod;
	}


	public Integer getTenderKind() {
		return tenderKind;
	}


	public void setTenderKind(Integer tenderKind) {
		this.tenderKind = tenderKind;
	}


	public String getTenderKindName() {
		return tenderKindName;
	}


	public void setTenderKindName(String tenderKindName) {
		this.tenderKindName = tenderKindName;
	}


	public Integer getBusinessType() {
		return businessType;
	}


	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}


	public String getBusinessTypeNm() {
		return businessTypeNm;
	}


	public void setBusinessTypeNm(String businessTypeNm) {
		this.businessTypeNm = businessTypeNm;
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


	public Safeguard getSafeguard() {
		return safeguard;
	}


	public void setSafeguard(Safeguard safeguard) {
		this.safeguard = safeguard;
	}


	public String getProductContent() {
		return productContent;
	}


	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}


	public String getRepaySource() {
		return repaySource;
	}


	public void setRepaySource(String repaySource) {
		this.repaySource = repaySource;
	}


	public String getFundUse() {
		return fundUse;
	}


	public void setFundUse(String fundUse) {
		this.fundUse = fundUse;
	}


	public Integer getMinHoldDay() {
		return minHoldDay;
	}


	public void setMinHoldDay(Integer minHoldDay) {
		this.minHoldDay = minHoldDay;
	}


	public Integer getCanAutobid() {
		return canAutobid;
	}


	public void setCanAutobid(Integer canAutobid) {
		this.canAutobid = canAutobid;
	}


	public BigDecimal getAwardRate() {
		return awardRate;
	}


	public void setAwardRate(BigDecimal awardRate) {
		this.awardRate = awardRate;
	}


	public BigDecimal getAwardAmount() {
		return awardAmount;
	}


	public void setAwardAmount(BigDecimal awardAmount) {
		this.awardAmount = awardAmount;
	}


	public BigDecimal getAwardTotal() {
		return awardTotal;
	}


	public void setAwardTotal(BigDecimal awardTotal) {
		this.awardTotal = awardTotal;
	}


	public Integer getAwardFail() {
		return awardFail;
	}


	public void setAwardFail(Integer awardFail) {
		this.awardFail = awardFail;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	public Integer getCompensatory() {
		return compensatory;
	}


	public void setCompensatory(Integer compensatory) {
		this.compensatory = compensatory;
	}


	public BigDecimal getCompenInterest() {
		return compenInterest;
	}


	public void setCompenInterest(BigDecimal compenInterest) {
		this.compenInterest = compenInterest;
	}


	public Integer getInterestAlgorithm() {
		return interestAlgorithm;
	}


	public void setInterestAlgorithm(Integer interestAlgorithm) {
		this.interestAlgorithm = interestAlgorithm;
	}


	public String getLoanStatus() {
		return loanStatus;
	}


	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}


	public boolean isGenerationRepayItemPlan() {
		return isGenerationRepayItemPlan;
	}


	public void setGenerationRepayItemPlan(boolean isGenerationRepayItemPlan) {
		this.isGenerationRepayItemPlan = isGenerationRepayItemPlan;
	}


	public Boolean getHomeRecomment() {
		return homeRecomment;
	}


	public void setHomeRecomment(Boolean homeRecomment) {
		this.homeRecomment = homeRecomment;
	}


	public List<ProductAuthInfo> getProductAuthInfos() {
		return productAuthInfos;
	}


	public void setProductAuthInfos(List<ProductAuthInfo> productAuthInfos) {
		this.productAuthInfos = productAuthInfos;
	}


	public List<Attachment> getAttachments() {
		return attachments;
	}


	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}


	public List<ProductPledge> getProductPledges() {
		return productPledges;
	}


	public void setProductPledges(List<ProductPledge> productPledges) {
		this.productPledges = productPledges;
	}
	
	public String getAmountF() {
		if (getAmount().equals("0.00")) {
			return "0.00";
		}
		return new DecimalFormat("#,##0.00").format(getAmount());
	}
	
	public BigDecimal getMinTenderAmount() {
		if ((StringUtil.isEmpty(this.unitPrice)) || (StringUtil.isEmpty(this.minTenderQuantity))) {
			return BigDecimal.ZERO;
		}
		return this.unitPrice.multiply(new BigDecimal(this.minTenderQuantity.longValue()));
	}
	
	public String getMinTenderAmountF() {
		if (getMinTenderAmount().equals("0.00")) {
			return "0.00";
		}
		return new DecimalFormat("#,##0.00").format(getMinTenderAmount());
	}
	
	
	public String getStatusName() {
		if (StringUtil.isNotEmpty(getStatus())) {
			switch (getStatus().intValue()) {
			case 10:
				return "草稿";
			case 11:
				return "待审核";
			case 12:
				return "已废弃";
			case 13:
				return "已撤回";
			case 14:
				return "审核驳回";
			case 15:
				return "审核失败";
			case 31:
				return "待招标(待上线)";
			case 40:
				return "已撤标";
			case 32:
				return "已上线";
			case 30:
				return "招标中";
			case 60:
				return "满标待审";
			case 61:
				return "流标待处理";
			case 80:
				return "待放款";
			case 90:
				return "还款中";
			case 91:
				return "还款处理中";
			case 100:
				return "已还完";
			case 70:
				return "废标待退款";
			case 101:
				return "废标已退款";
			case 71:
				return "流标待退款";
			case 73:
				return "撤标待退款";
			case 102:
				return "流标已退款";
			case 50:
				return "已流标";
			case 103:
				return "撤标已退款";
			}
			return "";
		}

		return "";
	}
}
