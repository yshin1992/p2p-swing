package org.ysh.p2p.model;

import java.io.Serializable;
import java.util.Date;

import org.ysh.p2p.annotation.Column;
import org.ysh.p2p.enums.Status;
import org.ysh.p2p.util.DateUtil;

/**
 * 基础数据模型
 * 其中包含着所有实体Model共有的变量
 * @author yshin1992
 *
 */
public abstract class BaseModel implements Serializable{

	private static final long serialVersionUID = -8958754734980273831L;

	/**
	 * 主体ID
	 */
	@Column(name = "uuid")
	private String uuid;
	
	/**
	 * 创建时间
	 */
	@Column(name = "createTime")
	private Date createTime;
	
	/**
	 * 更新时间
	 */
	@Column(name = "updateTime")
	private Date updateTime;
	
	/**
	 * 状态：默认为正常
	 */
	@Column(name = "status")
	private Integer status = 0;

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUuid() {
		return uuid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public void delete(){
		this.status = Status.DELETED.value();
	}
	
	public String getCreateTimeStr(){
		return DateUtil.defaultFormat(this.createTime);
	}
	
	public String getUpdateTimeStr(){
		return DateUtil.defaultFormat(this.updateTime);
	}

	@Override
	public String toString() {
		return "[uuid=" + uuid + ", createTime=" + getCreateTimeStr()
				+ ", updateTime=" + getUpdateTimeStr() + ", status=" + status + "]";
	}
	
	
}
