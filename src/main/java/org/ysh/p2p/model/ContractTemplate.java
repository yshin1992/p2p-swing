package org.ysh.p2p.model;

import java.io.Serializable;
import java.util.Date;

import org.ysh.p2p.annotation.Column;
import org.ysh.p2p.annotation.Table;

@Table(name="contract_template")
public class ContractTemplate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="createBy")
	private String createBy;
	
	@Column(name="createTime")
	private Date createTime;
	
	/**
	 * 模板ID
	 */
	@Column(name = "templateId")
	private String templateId;
	
	/**
	 * 模板名称，一般是合同模板文件的名称
	 */
	@Column(name="templateName")
	private String templateName;
	
	/**
	 * 模板文件的位置(doc文件)
	 */
	@Column(name="templatePath")
	private String templatePath;
	
	/**
	 * 模板的PDF文件位置(pdf文件)
	 */
	@Column(name="templatePdfPath")
	private String templatePdfPath;

	
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

	public String getTemplateId() {
		return templateId;
	}



	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}



	public String getTemplateName() {
		return templateName;
	}



	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}



	public String getTemplatePath() {
		return templatePath;
	}



	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}



	public String getTemplatePdfPath() {
		return templatePdfPath;
	}



	public void setTemplatePdfPath(String templatePdfPath) {
		this.templatePdfPath = templatePdfPath;
	}



	@Override
	public String toString() {
		return "ContractTemplate [createBy=" + createBy + ", createTime="
				+ createTime + ", templateId=" + templateId + ", templateName="
				+ templateName + ", templatePath=" + templatePath
				+ ", templatePdfPath=" + templatePdfPath + "]";
	}
	
	
}
