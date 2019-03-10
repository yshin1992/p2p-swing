package org.ysh.p2p.vo;

import java.io.Serializable;

public class DropDownDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String attrCd;
	
	private String attrNm;
	
	private boolean display = true;
	
	private String actualVal;
	
	private String defaultVal;
	
	public DropDownDto(){}
	
	public DropDownDto(String attrCd,String attrNm){
		this(attrCd,attrNm,true,null,null);
	}
	
	public DropDownDto(String attrCd,String attrNm,boolean display,String actualVal,String defaultVal){
		this.attrCd = attrCd;
		this.attrNm = attrNm;
		this.display = display;
		this.actualVal = actualVal;
		this.defaultVal = defaultVal;
		
	}

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

	public boolean isDisplay() {
		return display;
	}

	public void setDisplay(boolean display) {
		this.display = display;
	}

	public String getActualVal() {
		return actualVal;
	}

	public void setActualVal(String actualVal) {
		this.actualVal = actualVal;
	}

	public String getDefaultVal() {
		return defaultVal;
	}

	public void setDefaultVal(String defaultVal) {
		this.defaultVal = defaultVal;
	}
	
}
