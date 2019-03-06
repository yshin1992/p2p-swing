package org.ysh.p2p.model;

import org.ysh.p2p.annotation.Column;
import org.ysh.p2p.annotation.Table;

@Table(name= "safeguard")
public class Safeguard extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="productId")
	private String productId;
	
	@Column(name="companyId")
	private String companyId;
	
	/**
	 * 担保人编号
	 */
	@Column(name="backletterCd")
	private String backletterCd;
	
	/**
	 * 担保意见
	 */
	@Column(name="guaranteeDesc")
	private String guaranteeDesc;
	
	/**
	 * 保障措施说明
	 */
	@Column(name="safeguardDesc")
	private String safeguardDesc;
	/**
	 * 抵押物详情
	 */
	@Column(name="pawnDesc")
	private String pawnDesc;
	/**
	 * 借款人详情(企业涉诉状况)
	 */
	@Column(name="debtDesc")
	private String debtDesc;
	
	/**
	 * 风控详细(企业征信记录)
	 */
	@Column(name="riskDesc")
	private String riskDesc;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getBackletterCd() {
		return backletterCd;
	}

	public void setBackletterCd(String backletterCd) {
		this.backletterCd = backletterCd;
	}

	public String getGuaranteeDesc() {
		return guaranteeDesc;
	}

	public void setGuaranteeDesc(String guaranteeDesc) {
		this.guaranteeDesc = guaranteeDesc;
	}

	public String getSafeguardDesc() {
		return safeguardDesc;
	}

	public void setSafeguardDesc(String safeguardDesc) {
		this.safeguardDesc = safeguardDesc;
	}

	public String getPawnDesc() {
		return pawnDesc;
	}

	public void setPawnDesc(String pawnDesc) {
		this.pawnDesc = pawnDesc;
	}

	public String getDebtDesc() {
		return debtDesc;
	}

	public void setDebtDesc(String debtDesc) {
		this.debtDesc = debtDesc;
	}

	public String getRiskDesc() {
		return riskDesc;
	}

	public void setRiskDesc(String riskDesc) {
		this.riskDesc = riskDesc;
	}
	
	
}
