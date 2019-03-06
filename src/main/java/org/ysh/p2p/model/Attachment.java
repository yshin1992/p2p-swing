package org.ysh.p2p.model;

import org.ysh.p2p.annotation.Column;
import org.ysh.p2p.annotation.Table;

@Table(name="attachment")
public class Attachment extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 附件标题
	 */
	@Column(name="attachmentTitle")
	private String attachmentTitle;
	
	/**
	 * 附件组
	 */
	@Column(name="attachmentGroup")
	private String attachmentGroup;
	
	/**
	 * 附件名称
	 */
	@Column(name="attachmentNm")
	private String attachmentNm;
	
	/**
	 * 附件后缀
	 */
	@Column(name="attachmentExt")
	private String attachmentExt;
	
	/**
	 * 附件大小
	 */
	@Column(name="attachmentSize")
	private Long attachmentSize;
	
	/**
	 * 是否是临时附件
	 */
	@Column(name="isTemp")
	private Integer isTemp = Integer.valueOf(0);
	
	/**
	 * 附件路径
	 */
	@Column(name="attachmentPath")
	private String attachmentPath;
	
	/**
	 * 附件hash值
	 */
	@Column(name="attachmentHash")
	private String attachmentHash;

	public String getAttachmentTitle() {
		return attachmentTitle;
	}

	public void setAttachmentTitle(String attachmentTitle) {
		this.attachmentTitle = attachmentTitle;
	}

	public String getAttachmentGroup() {
		return attachmentGroup;
	}

	public void setAttachmentGroup(String attachmentGroup) {
		this.attachmentGroup = attachmentGroup;
	}

	public String getAttachmentNm() {
		return attachmentNm;
	}

	public void setAttachmentNm(String attachmentNm) {
		this.attachmentNm = attachmentNm;
		this.attachmentExt = attachmentNm.substring(attachmentNm.lastIndexOf("."));
	}

	public String getAttachmentExt() {
		return attachmentExt;
	}

	public Long getAttachmentSize() {
		return attachmentSize;
	}

	public void setAttachmentSize(Long attachmentSize) {
		this.attachmentSize = attachmentSize;
	}

	public Integer getIsTemp() {
		return isTemp;
	}

	public void setIsTemp(Integer isTemp) {
		this.isTemp = isTemp;
	}

	public String getAttachmentPath() {
		return attachmentPath;
	}

	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}

	public String getAttachmentHash() {
		return attachmentHash;
	}

	public void setAttachmentHash(String attachmentHash) {
		this.attachmentHash = attachmentHash;
	}

	@Override
	public String toString() {
		return "Attachment [attachmentTitle=" + attachmentTitle
				+ ", attachmentGroup=" + attachmentGroup + ", attachmentNm="
				+ attachmentNm + ", attachmentExt=" + attachmentExt
				+ ", attachmentSize=" + attachmentSize + ", isTemp=" + isTemp
				+ ", attachmentPath=" + attachmentPath + ", attachmentHash="
				+ attachmentHash + "]" + super.toString();
	}
	
	
}
