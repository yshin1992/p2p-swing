package org.ysh.p2p.service.impl;

import java.util.Date;

import org.ysh.p2p.dao.AbstractDao;
import org.ysh.p2p.dao.MemberDao;
import org.ysh.p2p.dao.UserSummaryDao;
import org.ysh.p2p.dao.impl.MemberDaoImpl;
import org.ysh.p2p.dao.impl.UserSummaryDaoImpl;
import org.ysh.p2p.model.Member;
import org.ysh.p2p.model.UserSummary;
import org.ysh.p2p.service.MemberService;
import org.ysh.p2p.support.ApplicationContext;
import org.ysh.p2p.support.RegisterSuccessEvent;
import org.ysh.p2p.util.LogUtil;
import org.ysh.p2p.util.StringUtil;
import org.ysh.p2p.vo.ResponseMsg;

public class MemberServiceImpl extends AbstractServiceImpl<Member> implements MemberService{

	private MemberDao memberDao = new MemberDaoImpl();
	
	private UserSummaryDao userSummaryDao = new UserSummaryDaoImpl();
	
	private ApplicationContext ctx = ApplicationContext.getApplicationContext();
	
	public AbstractDao<Member> getDao() {
		return memberDao;
	}


	public ResponseMsg<Member> register(Member member) throws Exception {
		
		// TODO Auto-generated method stub
		ResponseMsg<Member> respMsg= new ResponseMsg<Member>();
		//验证手机号
		if(StringUtil.isEmpty(member.getPhone())){
			respMsg.failure(null,"用户手机号不能为空");
			return respMsg;
		}else{
			Member checkMember = new Member();
			checkMember.setPhone(member.getPhone());
			checkMember = memberDao.query(checkMember, Member.class);
			if(null != checkMember){
				respMsg.failure(null,"该手机号已经注册");
				return respMsg;
			}
		}
		LogUtil.getLogger(this).warning("手机号["+member.getPhone()+"]验证完成");
		
		if(StringUtil.isEmpty(member.getNickName())){
			respMsg.failure(null,"用户昵称不能为空");
			return respMsg;
		}else{
			Member checkMember = new Member();
			checkMember.setNickName(member.getNickName());
			checkMember = memberDao.query(checkMember, Member.class);
			if(null != checkMember){
				respMsg.failure(null,"用户昵称重复");
				return respMsg;
			}
		}
		
		LogUtil.getLogger(this).warning("用户昵称["+member.getNickName()+"]验证完成");
		//TODO 对推荐用户进行奖励
		if(StringUtil.isNotEmpty(member.getPromotionId())){
			
			Member promtion = new Member();
			promtion.setPhone(member.getPromotionId());
			
			promtion = memberDao.query(promtion, Member.class);
			if(member != null){
				member.setMemberIdZ(member.getMemberIdZ());
				//TODO 这里还要做发送红包的事务
			}
			
		}
		
		try {
			member.setUuid(StringUtil.generateUuid());
			//生成realCd
			member.setRealCd(StringUtil.generateShortUuid());
			// 注册生成推荐码
			member.setPromotionId(member.getPhone());
			member.setCreateTime(new Date());
			member.setUpdateTime(new Date());
			member.setRegistTime(new Date());
			member.setLoginCount(0);
			member.setAuditType(8);
			member.setPassword(StringUtil.getMd5(StringUtil.getMd5(member.getPassword())+ member.getRealCd()));//对密码进行加密
			
			if(member.getMemberKind() == Member.MEMBER_KIND_ENTERPRISE){
				//TODO 企业信息
			}
			
			memberDao.add(member, Member.class);
			
			userSummaryDao.add(new UserSummary(member.getUuid()), UserSummary.class);
		} catch (Exception e) {
			LogUtil.getLogger(this).warning("会员添加失败"+member);
			e.printStackTrace();
		}
		
		//还有待做
		//TODO
		
		ctx.publishEvent(new RegisterSuccessEvent<String>(member.getUuid()));
		
		respMsg.setData(member);
		return respMsg;
	}

}
