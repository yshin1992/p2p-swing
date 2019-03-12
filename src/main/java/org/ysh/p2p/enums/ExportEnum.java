package org.ysh.p2p.enums;

public enum ExportEnum {
	//佣金付款明细和代发表
		LOAN_BROKERAGE_REFUND("loanBrokerage.xlsx","佣金付款明细表.xlsx"),
		LOAN_BROKERAGE_REFUND_BEHALF("loanBrokerageBehalf.xlsx","佣金付款明细表(代发).xlsx"),
		
		PRODUCT_REPAYDETAIL("productRepayDetail.xlsx","项目还款明细表.xlsx"),
		PRODUCT_REPAYDETAIL_BEHALF("productRepayDetailBehalf.xlsx","项目还款明细表(代发).xlsx"),
		PRODUCT_INVESTMENTINCOMEPLAN("investmentIncomePlan.xlsx","项目投资计划收益表.xlsx"),

	    PRODUCT_INVEST_DETAIL("productInvestDetail.xlsx", "项目投资记录表.xlsx"),
	    PRODUCT_INVEST_DETAIL_BEHALF("productInvestDetailBeHalf.xlsx", "项目投资记录表(代发).xlsx"),
	    
	    // 订单退款报表和代发报表
		PRODUCT_ORDER_REFUND("orderRefundReport.xlsx","订单退款明细表.xlsx"),
		PRODUCT_ORDER_REFUND_BEHALF("orderRefundReportBehalf.xlsx","订单退款明细表(代发).xlsx"),

	    PRODUCT_INVEST_DETAIL_FAILED("productInvestDetailFail.xlsx", "项目流标退款表.xlsx"),
	    PRODUCT_INVEST_DETAIL_FAILED_BEHALF("productInvestDetailFailBeHalf.xlsx", "项目流标退款表(代发).xlsx"),

		PRODUCT_LOANINFO_BEHALF("productLoanInfoBehalf.xlsx","项目放款表(代发).xlsx"),
		
		PRODUCT_REPAYPLAN_REPORT("productRepayPlanReport.xlsx","项目还款计划表.xlsx"),
		
		PRODUCT_ORDER_DETAIL("productOrderDetail.xlsx","订单明细.xlsx"),
		
		//客户管理数据报表
		MEMBER_REALNAME_DETAIL("memberRealNameDetail.xlsx","实名认证信息表.xlsx"),
		MEMBER_PHONE_DETAIL("memberPhoneDetail.xlsx","会员手机信息表.xlsx"),
		MEMBER_ACCOUNT_DETAIL("memberAccountDetail.xlsx","会员账户信息表.xlsx"),
		MEMBER_CAPITAL_DETAIL("memberCapitalDetail.xlsx","会员资金明细表.xlsx"),
		MEMBER_CAPITAL_RECORD("memberCapitalRecord.xlsx","会员资金记录表.xlsx"),
		MEMBER_WITHDRAW_RECORD("memberWithdrawRecord.xlsx","会员提现记录表.xlsx"),
		MEMBER_INFO_DETAIL("memberInfoDetail.xlsx","会员信息表.xlsx"),
		MEMBER_BANK_DETAIL("memberBankDetail.xlsx","会员银行卡信息表.xlsx"),
		MEMBER_BANK_AUDIT("memberBankAudit.xlsx","银行卡认证审核信息表.xlsx"),
		
		
		PLATFORM_FEE_RECORD("platformFeeRecord.xlsx","平台费用记录表.xlsx"),
	    
		//财务处理报表
		PRODUCT_FOR_LENDING("productForLending.xlsx","待放款项目表.xlsx"),
		PRODUCT_FOR_REPAYMENT("productForRepayment.xlsx","待还款项目表.xlsx"),
		PRODUCT_FOR_DRAWBACK("productForDrawback.xlsx","待退款项目表.xlsx"),
		
		//还款管理
		PRODUCT_REPAYMENTING("productRepaymenting.xlsx","还款中项目表.xlsx"),
		PRODUCT_OVERDUE("productOverdue.xlsx","逾期未还项目表.xlsx"),
		
		/**
		 * 积分管理  memberIntegralManager.xlsx 
		 */
		MEMBERINTEGRALMANAGER("memberIntegralManager.xlsx","积分管理.xlsx");
		
		
		private String code;
		
		private String desc;
		
		private ExportEnum(String code, String desc){
			this.code = code;
			this.desc = desc;
		}
		
		public String getCode() {
			return code;
		}
		
		public void setCode(String code) {
			this.code = code;
		}
		
		public String getDesc() {
			return desc;
		}
		
		public void setDesc(String desc) {
			this.desc = desc;
		}
		
		public Integer getCodeInt() {
			try {
				return Integer.parseInt(this.code);
			} catch (Exception e) {
				return null;
			}
		}
}
