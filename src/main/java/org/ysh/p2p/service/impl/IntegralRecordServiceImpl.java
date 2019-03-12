package org.ysh.p2p.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.logging.Logger;

import org.ysh.p2p.dao.AbstractDao;
import org.ysh.p2p.dao.IntegralRecordDao;
import org.ysh.p2p.dao.impl.IntegralRecordDaoImpl;
import org.ysh.p2p.enums.IntegralRecordEnum;
import org.ysh.p2p.model.IntegralRecord;
import org.ysh.p2p.model.Member;
import org.ysh.p2p.service.IntegralRecordService;
import org.ysh.p2p.util.LogUtil;
import org.ysh.p2p.util.StringUtil;
import org.ysh.p2p.vo.PageRequest;
import org.ysh.p2p.vo.PageResponse;

/**
 * 会员积分服务类实现
 * @author yshin1992
 *
 */
public class IntegralRecordServiceImpl extends AbstractServiceImpl<IntegralRecord> implements IntegralRecordService{

	private final static Logger logger = LogUtil.getLogger(IntegralRecordServiceImpl.class);
	
	private IntegralRecordDao integralRecordDao = new IntegralRecordDaoImpl();
	
	
	/**
	 * 
	 * @param member
	 *            积分所属会员
	 * @param integralVal
	 *            积分值 根据isAddFlag判断 增加存正值 消耗存负值
	 * @param isAddFlag
	 *            新增或消耗
	 * @param remark
	 *            备注
	 * @param objId
	 *            关联数据主键 0注册:增加:关联memberId 1登录:增加:关联memberId 2推荐好友注册:增加:关联memberId 3投资成功:增加:关联投资订单 4推荐好友投资成功:增加:关联投资订单 5新投资额:增加:关联投资订单 6投资消耗:减少:关联投资订单
	 * @param objType
	 *            关联数据类型 0注册 1登录 2推荐好友注册 3投资成功 4推荐好友投资成功 5新投资额 6投资消耗
	 * @param amount
	 *            *对应面值 可空
	 * @throws Exception 
	 */
	private void sendIntegral(Member member, Integer integralVal, IntegralRecordEnum isAddFlag, String remark, String objId,
			IntegralRecordEnum objType, BigDecimal amount) throws Exception {
		IntegralRecord integralRecord = new IntegralRecord();
		integralRecord.setRecordId(StringUtil.generateUuid());
		integralRecord.setMember(member);// 积分所属会员
		integralRecord.setMemberId(member.getUuid());
		integralRecord.setIntegralVal(isAddFlag == IntegralRecordEnum.ISADDFLAG_ADD_1 ? Math.abs(integralVal) : -1 * Math.abs(integralVal)); // 积分值
		integralRecord.setIsAddFlag(Integer.valueOf(isAddFlag.getKey()));// 新增或消耗
		integralRecord.setRemark(StringUtil.isNotEmpty(remark) ? (remark.length() <= 200 ? remark : remark.substring(0, 199)) : "");// 备注
		integralRecord.setObjId(objId);// 关联数据主键
		integralRecord.setObjType(Integer.valueOf(objType.getKey()));// 关联数据类型
		integralRecord.setAmount(null == amount ? BigDecimal.ZERO : amount);// *对应面值
		integralRecord.setStatus(Integer.valueOf(IntegralRecordEnum.STATUS_1.getKey())); // 状态默认有效
		integralRecord.setIsPermanent(Integer.valueOf(IntegralRecordEnum.ISPERMANENT_1.getKey()));// 默认永久有效
		integralRecord.setCreateTime(new Date());
		integralRecord.setFailureTime(new Date());
		integralRecordDao.add(integralRecord,IntegralRecord.class);
		logger.warning("添加积分明细："+integralRecord );
	}
	
	private String buildRemark(String... strings) {
		StringBuilder returnSB = new StringBuilder();
		if (strings.length <= 0) {
			returnSB.append("-");
		}
		for (String string : strings) {
			returnSB.append(string + "-");
		}
		returnSB.replace(returnSB.length() - 1, returnSB.length(), "");
		return returnSB.toString();
	}
	
	public AbstractDao<IntegralRecord> getDao() {
		return integralRecordDao;
	}


	public void registerGiveIntegral(Member member, Integer integralVal)throws Exception {
		String remark=buildRemark("会员[" + member.getRealCd() + "]手机号[" + member.getPhone() + "]", "于" + member.getRegistTimeStr() + "注册获得积分");
		sendIntegral(member, integralVal, IntegralRecordEnum.ISADDFLAG_ADD_1, remark, member.getUuid(), IntegralRecordEnum.OBJTYPE_REGISTER_0,
				null);
	}


	public void recommendedRegisterGiveIntegral(Member registerMember,
			Member recommendMember, Integer integralVal) throws Exception{
		String remark = this.buildRemark(
				"会员[" + recommendMember.getRealCd() + "]["
						+ (StringUtil.isNotEmpty(recommendMember.getRealNm()) ? recommendMember.getRealCd() : recommendMember.getPhone()) + "]",
				"推荐好友[" + registerMember.getRealCd() + "]手机号[" + registerMember.getPhone() + "]", "于" + registerMember.getRegistTimeStr() + "注册获得积分");
		sendIntegral(recommendMember, integralVal, IntegralRecordEnum.ISADDFLAG_ADD_1, remark, registerMember.getUuid(),
				IntegralRecordEnum.OBJTYPE_RECOMMENDED_FRIEND_REGISTRATION_2, null);
	}


	public void loginGiveIntegral(Member member, Integer integralVal) throws Exception{
		// TODO Auto-generated method stub
		String remark = this.buildRemark(
				"会员[" + member.getRealCd() + "][" + (StringUtil.isNotEmpty(member.getRealNm()) ? member.getRealNm() : member.getPhone()) + "]",
				"于" + member.getLastLoginFormat() + "登录获得积分");
		sendIntegral(member, integralVal, IntegralRecordEnum.ISADDFLAG_ADD_1, remark, member.getUuid(), IntegralRecordEnum.OBJTYPE_LOGIN_1, null);		
	}

	public PageResponse<IntegralRecord> queryByPage(PageRequest pageRequest,
			String memberId, String flag,String queryStart, String queryEnd) {
		// TODO Auto-generated method stub
		Integer addFlag = null;
		if(IntegralRecordEnum.ISADDFLAG_ADD_1.getText().equals(flag)){
			addFlag = Integer.parseInt(IntegralRecordEnum.ISADDFLAG_ADD_1.getKey());
		}else if(IntegralRecordEnum.ISADDFLAG_SUB_0.getText().equals(flag)){
			addFlag = Integer.parseInt(IntegralRecordEnum.ISADDFLAG_SUB_0.getKey());
		}
		return integralRecordDao.queryByPage(pageRequest, memberId,addFlag, queryStart, queryEnd);
	}

}
