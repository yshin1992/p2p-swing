package org.ysh.p2p.vo;

public class ProductDto {

	private String keywords;
	
	private String queryStart;
	
	private String queryEnd;
	
	private Integer productStatus;

	/**
	 * 产品状态：如果是待处理的，则对应查询的产品状态应该是 10,13,14
	 * 如果是已处理的，则对应的是 11,12,15,30,31,32
	 */
	private String desc;
	
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getQueryStart() {
		return queryStart;
	}

	public void setQueryStart(String queryStart) {
		this.queryStart = queryStart;
	}

	public String getQueryEnd() {
		return queryEnd;
	}

	public void setQueryEnd(String queryEnd) {
		this.queryEnd = queryEnd;
	}

	public Integer getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
}
