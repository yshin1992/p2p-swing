package org.ysh.p2p.model;

import java.math.BigDecimal;

import org.ysh.p2p.annotation.Column;
import org.ysh.p2p.annotation.Table;
import org.ysh.p2p.annotation.Transient;

/**
 * 会员积分表实体
 * @author yshin1992
 *
 */
@Table(name="memberintegral")
public class MemberIntegral extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 会员标识
	 */
	@Column(name = "memberId")
	private String memberId;

	/**
	 * 会员
	 */
	@Transient
	private Member member;

	/**
	 * 可用积分
	 */
	@Column(name="integralVal")
	private Integer integralVal;
	/**
	 * 获取积分总额
	 */
	@Column(name="total")
	private Integer total;
	/**
	 * 已使用积分
	 */
	@Column(name="usedValue")
	private Integer usedValue;
	/**
	 * 已使用面值
	 */
	@Column(name="usedAmount")
	private BigDecimal usedAmount;
	/**
	 * 最高投资记录
	 */
	@Column(name="maxInvestAmount")
	private BigDecimal maxInvestAmount;
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Integer getIntegralVal() {
		return integralVal;
	}
	public void setIntegralVal(Integer integralVal) {
		this.integralVal = integralVal;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getUsedValue() {
		return usedValue;
	}
	public void setUsedValue(Integer usedValue) {
		this.usedValue = usedValue;
	}
	public BigDecimal getUsedAmount() {
		return usedAmount;
	}
	public void setUsedAmount(BigDecimal usedAmount) {
		this.usedAmount = usedAmount;
	}
	public BigDecimal getMaxInvestAmount() {
		return maxInvestAmount;
	}
	public void setMaxInvestAmount(BigDecimal maxInvestAmount) {
		this.maxInvestAmount = maxInvestAmount;
	}
	@Override
	public String toString() {
		return "MemberIntegral [memberId=" + memberId + ", member=" + member
				+ ", integralVal=" + integralVal + ", total=" + total
				+ ", usedValue=" + usedValue + ", usedAmount=" + usedAmount
				+ ", maxInvestAmount=" + maxInvestAmount + "]";
	}
	

}
