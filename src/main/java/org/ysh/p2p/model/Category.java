package org.ysh.p2p.model;

import java.util.HashSet;
import java.util.Set;

import org.ysh.p2p.annotation.Column;
import org.ysh.p2p.annotation.Table;
import org.ysh.p2p.annotation.Transient;

@Table(name="category")
public class Category extends StaticModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4638201903208817170L;

	@Column(name="categoryId")
	private String categoryId;
	
	/**
	 * 种类编码
	 */
	@Column(name="categoryCd")
	private String categoryCd;
	
	/**
	 * 种类名称
	 */
	@Column(name="categoryNm")
	private String categoryNm;
	
	/**
	 * 种类描述
	 */
	@Column(name="categoryDesc")
	private String categoryDesc;
	
	@Column(name="edited")
	private Integer edited;
	
	@Column(name="configed")
	private Integer configed;
	
	@Column(name="remark")
	private String remark;

	@Transient
	private Set<CategoryAttr> attrs = new HashSet<CategoryAttr>();
	
	public String getCategoryCd() {
		return categoryCd;
	}

	public void setCategoryCd(String categoryCd) {
		this.categoryCd = categoryCd;
	}

	public String getCategoryNm() {
		return categoryNm;
	}

	public void setCategoryNm(String categoryNm) {
		this.categoryNm = categoryNm;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	public Integer getEdited() {
		return edited;
	}

	public void setEdited(Integer edited) {
		this.edited = edited;
	}

	public Integer getConfiged() {
		return configed;
	}

	public void setConfiged(Integer configed) {
		this.configed = configed;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set<CategoryAttr> getAttrs() {
		return attrs;
	}

	public void setAttrs(Set<CategoryAttr> attrs) {
		this.attrs = attrs;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	
}
