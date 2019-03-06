package org.ysh.p2p.model;

import java.math.BigDecimal;

import org.ysh.p2p.annotation.Column;
import org.ysh.p2p.annotation.Table;
/**
 * 产品质押物拍卖纪录实体类
 * @author yshin1992
 *
 */
@Table(name = "product_pledge")
public class ProductPledge extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 产品ID
	 */
	private String productId;
	/**
	 * 作者
	 */
	@Column(name = "author")
	private String author;
	/**
	 * 作品名称
	 */
	@Column(name = "workName")
	private String workName;
	/**
	 * 作品尺寸
	 */
	@Column(name = "size")
	private String size;
	/**
	 * 拍卖公司
	 */
	@Column(name = "auctionCompany")
	private String auctionCompany;
	/**
	 * 拍卖时间
	 */
	@Column(name = "auctionTime")
	private String auctionTime;
	
	/**
	 * 成交价格
	 */
	@Column(name = "dealAmt")
	private BigDecimal dealAmt;
	/**
	 * 纪录类型
	 * 0-当前拍卖纪录
	 * 1-相关拍卖纪录类型
	 */
	@Column(name = "pledgeType")
	private Integer pledgeType;
	/**
	 * 备注
	 */
	@Column(name = "remark")
	private String remark;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getAuctionCompany() {
		return auctionCompany;
	}
	public void setAuctionCompany(String auctionCompany) {
		this.auctionCompany = auctionCompany;
	}
	public String getAuctionTime() {
		return auctionTime;
	}
	public void setAuctionTime(String auctionTime) {
		this.auctionTime = auctionTime;
	}
	public BigDecimal getDealAmt() {
		return dealAmt;
	}
	public void setDealAmt(BigDecimal dealAmt) {
		this.dealAmt = dealAmt;
	}
	public Integer getPledgeType() {
		return pledgeType;
	}
	public void setPledgeType(Integer pledgeType) {
		this.pledgeType = pledgeType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "ProductPledge [productId=" + productId + ", author=" + author
				+ ", workName=" + workName + ", size=" + size
				+ ", auctionCompany=" + auctionCompany + ", auctionTime="
				+ auctionTime + ", dealAmt=" + dealAmt + ", pledgeType="
				+ pledgeType + ", remark=" + remark + "]"+super.toString();
	}
	
	
}
