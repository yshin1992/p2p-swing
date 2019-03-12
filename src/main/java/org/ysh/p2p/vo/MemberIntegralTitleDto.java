package org.ysh.p2p.vo;

import java.math.BigDecimal;

import org.ysh.p2p.util.StringUtil;

public class MemberIntegralTitleDto {

	private BigDecimal totalValue;// 总积分数
	private BigDecimal effectiveValue; // 未使用总积分数
	private BigDecimal usedValue;// 已使用总积分数
	private BigDecimal usedAmount;// 已使用积分总面值

	public MemberIntegralTitleDto() {
		totalValue = BigDecimal.ZERO;// 总积分数
		effectiveValue = BigDecimal.ZERO; // 未使用总积分数
		usedValue = BigDecimal.ZERO;// 已使用总积分数
		usedAmount = BigDecimal.ZERO;// 已使用积分总面值
	}

	public BigDecimal getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}

	public BigDecimal getEffectiveValue() {
		return effectiveValue;
	}

	public void setEffectiveValue(BigDecimal effectiveValue) {
		this.effectiveValue = effectiveValue;
	}

	public BigDecimal getUsedValue() {
		return usedValue;
	}

	public void setUsedValue(BigDecimal usedValue) {
		this.usedValue = usedValue;
	}

	public BigDecimal getUsedAmount() {
		return usedAmount;
	}

	public String getUsedAmountStr() {
		return StringUtil.isEmpty(getUsedAmount()) ? "0" : getUsedAmount().setScale(2, BigDecimal.ROUND_HALF_UP)
				.toString();
	}

	public void setUsedAmount(BigDecimal usedAmount) {
		this.usedAmount = usedAmount;
	}

	@Override
	public String toString() {
		return "MemberIntegralTitleDto [totalValue=" + totalValue
				+ ", effectiveValue=" + effectiveValue + ", usedValue="
				+ usedValue + ", usedAmount=" + usedAmount + "]";
	}
	
}
