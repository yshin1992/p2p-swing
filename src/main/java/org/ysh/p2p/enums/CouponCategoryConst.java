package org.ysh.p2p.enums;

/**
 * 红包配置项
 * @author yshin1992
 *
 */
public interface CouponCategoryConst {
	/**发红类型对应项项*/
	public static final String COUPON_KIND ="coupon.kind";
	/**红包发放配置项*/
	public static final String COUPON_SEND = "coupon.send";
	/**注册发红包设置项*/
	public static final String REG_SEND_COUPON = "sendCoupon.reg";
	/**注册发红包对应规则项*/
	public static final String REG_SEND_COUPON_RULEID = "sendCoupon.reg.ruleId";
	/**启用注册发红包*/
	public static final String REG_SEND_COUPON_YES = "1";
	/**停用注册发红包*/
	public static final String REG_SEND_COUPON_NO = "0";
	
	/**实名认证发红包设置项*/
	public static final String REALNAMEV_SEND_COUPON = "sendCoupon.realNameV";
	/**实名认证发红包对应规则项*/
	public static final String REALNAMEV_SEND_COUPON_RULEID = "sendCoupon.realNameV.ruleId";
	/**启用实名认证发红包*/
	public static final String REALNAMEV_SEND_COUPON_YES = "1";
	/**实名认证注册发红包*/
	public static final String REALNAMEV_SEND_COUPON_NO = "0";
	
	/**绑卡发红包设置项*/
	public static final String BINDCARD_SEND_COUPON = "sendCoupon.bindCard";
	/**绑卡发红包对应规则项*/
	public static final String BINDCARD_SEND_COUPON_RULEID = "sendCoupon.bindCard.ruleId";
	/**启用绑卡发红包*/
	public static final String BINDCARD_SEND_COUPON_YES = "1";
	/**停用绑卡发红包*/
	public static final String BINDCARD_SEND_COUPON_NO = "0";
	
	/**投资发红包设置项*/
	public static final String INVEST_SEND_COUPON = "sendCoupon.invest";
	/**投资发红包对应规则项*/
	public static final String INVEST_SEND_COUPON_RULEID = "sendCoupon.invest.ruleId";
	/**投资发红包对应限制金额项*/
	public static final String INVEST_SEND_COUPON_LIMIT = "sendCoupon.invest.limit";
	/**启用投资发红包*/
	public static final String INVEST_SEND_COUPON_YES = "1";
	/**停用投资发红包*/
	public static final String INVEST_SEND_COUPON_NO = "0";
	
	/**推荐发红包设置项*/
	public static final String RECOMMEND_SEND_COUPON = "sendCoupon.recommend";
	/**推荐发红包对应规则项*/
	public static final String RECOMMEND_SEND_COUPON_RULEID = "sendCoupon.recommend.ruleId";
	/**启用推荐发红包*/
	public static final String RECOMMEND_SEND_COUPON_YES = "1";
	/**停用推荐发红包*/
	public static final String RECOMMEND_SEND_COUPON_NO = "0";
	
	/**被推荐发红包设置项*/
	public static final String RECOMMENDED_SEND_COUPON = "sendCoupon.recommended";
	/**被推荐发红包对应规则项*/
	public static final String RECOMMENDED_SEND_COUPON_RULEID = "sendCoupon.recommended.ruleId";
	/**启用被推荐发红包*/
	public static final String RECOMMENDED_SEND_COUPON_YES = "1";
	/**停用被推荐发红包*/
	public static final String RECOMMENDED_SEND_COUPON_NO = "0";
	
	/**注册抢红包*/
	public static final int GRABCOUPN_WAY_REG = 0;
	/**实名抢红包*/
	public static final int GRABCOUPN_WAY_REALNAMEV = 1;
	/**绑卡抢红包*/
	public static final int GRABCOUPN_WAY_BINDCARD = 2;
	/**推荐抢红包*/
	public static final int GRABCOUPN_WAY_RECOMMEND = 3;
	/**投资抢红包*/
	public static final int GRABCOUPN_WAY_INVEST = 4;
	/**被推荐抢红包*/
	public static final int GRABCOUPN_WAY_RECOMMENDED = 5;
	/**首投抢红包*/
	public static final int GRABCOUPN_WAY_INVEST_FIRST = 6;
	/**复投抢红包*/
	public static final int GRABCOUPN_WAY_INVEST_AGAIN = 7;
}
