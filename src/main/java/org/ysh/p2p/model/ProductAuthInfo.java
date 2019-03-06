package org.ysh.p2p.model;

import java.util.Date;

import org.ysh.p2p.annotation.Column;
import org.ysh.p2p.annotation.Table;

/**
 * 认证信息实体类
 * 
 * @author yshin1992
 *
 */
@Table(name = "product_authinfo")
public class ProductAuthInfo extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7385467626211821581L;

	@Column(name = "productId")
	private String productId;

	@Column(name = "infoType")
	private Integer infoType;

	@Column(name = "auditProject")
	private String auditProject;

	@Column(name = "auditProjectNm")
	private String auditProjectNm;

	@Column(name = "passTime")
	private Date passTime;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getInfoType() {
		return infoType;
	}

	public void setInfoType(Integer infoType) {
		this.infoType = infoType;
	}

	public String getAuditProject() {
		return auditProject;
	}

	public void setAuditProject(String auditProject) {
		this.auditProject = auditProject;
	}

	public String getAuditProjectNm() {
		return auditProjectNm;
	}

	public void setAuditProjectNm(String auditProjectNm) {
		this.auditProjectNm = auditProjectNm;
	}

	public Date getPassTime() {
		return passTime;
	}

	public void setPassTime(Date passTime) {
		this.passTime = passTime;
	}

	@Override
	public String toString() {
		return "ProductAuthInfo [productId=" + productId + ", infoType="
				+ infoType + ", auditProject=" + auditProject
				+ ", auditProjectNm=" + auditProjectNm + ", passTime="
				+ passTime + "]" + super.toString();
	}
	
}
