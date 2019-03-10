package org.ysh.p2p.model;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import org.ysh.p2p.annotation.Column;
import org.ysh.p2p.util.DateUtil;

public abstract class StaticModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4914190798514073045L;

	public static final String STATE_DISABLED = "F0X";
	
	public static final String STATE_ENABLED = "F0A";
	
	@Column(name="createBy")
	private String createBy = "system";
	
	@Column(name="createTime")
	private Date createTime;
	
	@Column(name="effTime")
	private Date effTime;
	
	@Column(name="expTime")
	private Date expTime;
	
	@Column(name="stateTime")
	private Date stateTime;
	
	@Column(name="state")
	private String state;
	
	@Column(name="listSort")
	private Integer listSort;

	public Date getEffTime() {
		return effTime;
	}

	public void setEffTime(Date effTime) {
		this.effTime = effTime;
	}

	public Date getExpTime() {
		return expTime;
	}

	public void setExpTime(Date expTime) {
		this.expTime = expTime;
	}

	public Date getStateTime() {
		return stateTime;
	}

	public void setStateTime(Date stateTime) {
		this.stateTime = stateTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getListSort() {
		return listSort;
	}

	public void setListSort(Integer listSort) {
		this.listSort = listSort;
	}
	
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public void setEnable(boolean enable){
		setState(enable?"F0A":"F0X");
	}
	
	public void enable(){
		setEnable(true);
		setStateTime(new Date());
		setExpTime(DateUtil.addYears(new Date(), 100));
	}
	
	public boolean isEnabled(){
		Date d1 = new Date();
		return ("F0A".equals(this.state))&& (d1.after(this.effTime)) && (d1.before(this.expTime));
	}
	
	public void disable(){
		setEnable(false);
		setStateTime(new Date());
		setExpTime(new Date());
	}
	
	public void init(){
		if(getCreateBy() == null)
			setCreateBy("system");
		if(getCreateTime() == null)
			setCreateTime(new Date());
		if (getEffTime() == null) 
			setEffTime(new Date());
		if (getExpTime() == null)
			try {
				setExpTime(DateUtil.parse("22001231235959", "yyyyMMddHHmmss"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		if (getStateTime() == null) setStateTime(new Date());
		if (getState() == null) setEnable(true);
		if (getListSort() == null) setListSort(Integer.valueOf(1));
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
