package org.ysh.p2p.model;

import org.ysh.p2p.annotation.Column;
import org.ysh.p2p.annotation.Transient;


public class CategoryAttr extends StaticModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6282942245703314128L;

	@Column(name="attrId")
	private String attrId;
	
	@Column(name="attrCd")
	private String attrCd;
	
	@Column(name="attrNm")
	private String attrNm;
	
	@Column(name="actualval")
	private String actualval;
	
	@Column(name="defaultVal")
	private String defaultVal;
	
	@Column(name="categoryId")
	private String categoryId;
	
	@Transient
	private Category category;
	
	@Column(name="attrRequired")
	private Integer attrRequired;
	
	@Column(name="remark")
	private String remark;

	public String getAttrCd() {
		return attrCd;
	}

	public void setAttrCd(String attrCd) {
		this.attrCd = attrCd;
	}

	public String getAttrNm() {
		return attrNm;
	}

	public void setAttrNm(String attrNm) {
		this.attrNm = attrNm;
	}

	public String getActualval() {
		return actualval;
	}

	public void setActualval(String actualval) {
		this.actualval = actualval;
	}

	public String getDefaultVal() {
		return defaultVal;
	}

	public void setDefaultVal(String defaultVal) {
		this.defaultVal = defaultVal;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Integer getAttrRequired() {
		return attrRequired;
	}

	public void setAttrRequired(Integer attrRequired) {
		this.attrRequired = attrRequired;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAttrId() {
		return attrId;
	}

	public void setAttrId(String attrId) {
		this.attrId = attrId;
	}
	
	
}
