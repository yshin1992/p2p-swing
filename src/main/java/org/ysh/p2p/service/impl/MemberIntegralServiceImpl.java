package org.ysh.p2p.service.impl;

import java.math.BigDecimal;

import org.ysh.p2p.dao.AbstractDao;
import org.ysh.p2p.dao.MemberDao;
import org.ysh.p2p.dao.MemberIntegralDao;
import org.ysh.p2p.dao.impl.MemberDaoImpl;
import org.ysh.p2p.dao.impl.MemberIntegralDaoImpl;
import org.ysh.p2p.enums.CategoryAttrEnum;
import org.ysh.p2p.model.Member;
import org.ysh.p2p.model.MemberIntegral;
import org.ysh.p2p.service.IntegralRecordService;
import org.ysh.p2p.service.MemberIntegralService;
import org.ysh.p2p.util.CacheUtil;
import org.ysh.p2p.util.LogUtil;
import org.ysh.p2p.util.StringUtil;
import org.ysh.p2p.vo.MemberIntegralTitleDto;
import org.ysh.p2p.vo.PageRequest;
import org.ysh.p2p.vo.PageResponse;

/**
 * 会员积分Service
 * @author yshin1992
 *
 */
public class MemberIntegralServiceImpl extends AbstractServiceImpl<MemberIntegral> implements MemberIntegralService{

	private MemberDao memberDao = new MemberDaoImpl();
	
	private MemberIntegralDao memberIntegralDao = new MemberIntegralDaoImpl();
	
	private IntegralRecordService recordService = new IntegralRecordServiceImpl();
	
	public AbstractDao<MemberIntegral> getDao() {
		return memberIntegralDao;
	}

	private boolean checkIsUseIntegral(){
		Integer isUseIntegral = CacheUtil.getInt(CategoryAttrEnum.INTEGRAL_ISUSEINTEGRAL.getAttrCd(), 0);
		boolean result = true;
		if (isUseIntegral <= 0) {
			LogUtil.getLogger(this).warning("本系统未启用积分相关业务。是否启用积分业务配置参数值："+isUseIntegral);
			result = false;
		}
		return result;
	}
	
	public MemberIntegral initMemberIntegral(String memberId) {
		MemberIntegral mi=null;
		try {
			mi = new MemberIntegral();
			mi.setMemberId(memberId);
			mi = memberIntegralDao.query(mi, MemberIntegral.class);
			if(mi != null){
				return mi;
			}else{
				Member member = memberDao.findByUuid(memberId, Member.class);
				if(null == member){
					throw new RuntimeException("未查询到会员信息");
				}
				
				mi = new MemberIntegral();
				mi.setUuid(StringUtil.generateUuid());
				mi.setCreateTime(member.getRegistTime());
				mi.setUpdateTime(member.getRegistTime());
				mi.setMemberId(memberId);
				mi.setMember(member);
				mi.setTotal(0);
				mi.setMaxInvestAmount(BigDecimal.ZERO);
				mi.setIntegralVal(0);
				mi.setUsedAmount(BigDecimal.ZERO);
				mi.setUsedValue(0);
				
				memberIntegralDao.add(mi, MemberIntegral.class);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mi;
	}

	public void registerGiveIntegral(String memberId) {
		//未开启积分设置
		if(!checkIsUseIntegral()){
			return;
		}
		
		try{
			//注册获得积分有两步，第一步，用户自己注册获得积分
			//第二步，如果有推荐人，则推荐人增加积分
			Member member = memberDao.findByUuid(memberId, Member.class);
			if(member == null){
				throw new RuntimeException("未查询到注册用户信息!");
			}
			MemberIntegral integral = new MemberIntegral();
			integral.setMemberId(memberId);
			integral = memberIntegralDao.query(integral, MemberIntegral.class);
			if(null == integral){
				throw new RuntimeException("未查询到用户积分信息");
			}
			
			Integer regIntegral = CacheUtil.getInt(CategoryAttrEnum.INTEGRAL_REGISTERGIVEINTEGRAL.getAttrCd());
			if(regIntegral <= 0){
				LogUtil.getLogger(this).warning("注册积分为"+regIntegral+",未获得积分");
			}else{
				integral.setIntegralVal(regIntegral+ integral.getIntegralVal());
				integral.setTotal(regIntegral+integral.getIntegralVal());
				memberIntegralDao.update(integral, MemberIntegral.class);
				
				// 添加注册会员的获得积分记录
				// 添加明细
				recordService.registerGiveIntegral(member, regIntegral);
			}
			
			//有好友推荐的情况下
			if(StringUtil.isNotEmpty(member.getMemberIdZ())){
				Member recommendMember = memberDao.findByUuid(member.getMemberIdZ(), Member.class);
				if(recommendMember==null){
					throw new RuntimeException("未查询到推荐好友信息!");
				}
				
				MemberIntegral recommendIntegral = new MemberIntegral();
				recommendIntegral.setMemberId(member.getMemberIdZ());
				recommendIntegral = memberIntegralDao.query(recommendIntegral, MemberIntegral.class);
				
				if(null == recommendIntegral){
					throw new RuntimeException("未查询到推荐好友的积分信息!");
				}
				Integer recommendVal = CacheUtil.getInt(CategoryAttrEnum.INTEGRAL_RECOMMENDFRIENDSGIVEINTEGRAL.getAttrCd());
				if(recommendVal <= 0){
					LogUtil.getLogger(this).warning("注册积分为"+regIntegral+",未获得积分");
				}else{
					recommendIntegral.setIntegralVal(recommendVal+ recommendIntegral.getIntegralVal());
					recommendIntegral.setTotal(recommendVal+ recommendIntegral.getTotal());
					memberIntegralDao.update(recommendIntegral, MemberIntegral.class);
					
					// 添加推荐好友的会员的获得积分记录
					// 添加明细
					recordService.recommendedRegisterGiveIntegral(member, recommendMember, recommendVal);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public void loginGiveIntegral(String memberId, boolean isFirstLoginInToday) {
		//未开启积分设置
		if(!checkIsUseIntegral()){
			return;
		}
		LogUtil.getLogger(this).warning("登录获得积分=====传入参数：memberId:"+memberId+",isFirstLoginInToday:"+isFirstLoginInToday);
		try{
			//是今日首次登录的情况下
			if(isFirstLoginInToday){
				Member member = memberDao.findByUuid(memberId, Member.class);
				if(member == null){
					throw new RuntimeException("未查询到注册用户信息!");
				}
				MemberIntegral integral = new MemberIntegral();
				integral.setMemberId(memberId);
				integral = memberIntegralDao.query(integral, MemberIntegral.class);
				if(null == integral){
					throw new RuntimeException("未查询到用户积分信息");
				}
				
				Integer regIntegral = CacheUtil.getInt(CategoryAttrEnum.INTEGRAL_LOGINGIVEINTEGRAL.getAttrCd());
				if(regIntegral <= 0){
					LogUtil.getLogger(this).warning("每日首次登录获得积分为"+regIntegral+",未获得积分");
				}else{
					integral.setIntegralVal(regIntegral+ integral.getIntegralVal());
					integral.setTotal(regIntegral+ integral.getTotal());
					memberIntegralDao.update(integral, MemberIntegral.class);
					// 添加注册会员的获得积分记录
					// 添加明细
					recordService.loginGiveIntegral(member, regIntegral);
				}
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public MemberIntegralTitleDto queryMemberIntegralTitleDto(String keyword,
			String queryStart, String queryEnd) {
		return memberIntegralDao.queryMemberIntegralTitle(keyword, queryStart, queryEnd);
	}

	public PageResponse<MemberIntegral> queryByPage(PageRequest pageRequest, String keyword,
			String queryStart, String queryEnd) {
		return memberIntegralDao.queryByPage(pageRequest, keyword, queryStart, queryEnd);
	}

}
