package org.ysh.p2p.service.impl;

import java.util.List;
import java.util.logging.Logger;

import org.ysh.p2p.dao.CategoryAttrDao;
import org.ysh.p2p.dao.impl.CategoryAttrDaoImpl;
import org.ysh.p2p.enums.CategoryAttrEnum;
import org.ysh.p2p.enums.CategoryEnum;
import org.ysh.p2p.model.CategoryAttr;
import org.ysh.p2p.model.SysOperationLog;
import org.ysh.p2p.service.IntegralSetService;
import org.ysh.p2p.service.SysOperationLogService;
import org.ysh.p2p.session.Session;
import org.ysh.p2p.support.ApplicationContext;
import org.ysh.p2p.support.BackstageOperationSuccessEvent;
import org.ysh.p2p.util.CacheUtil;
import org.ysh.p2p.util.LogUtil;
import org.ysh.p2p.vo.IntegralSetDto;

public class IntegralSetServiceImpl implements IntegralSetService {

	private Logger logger = LogUtil.getLogger(this);
	
	private CategoryAttrDao attrDao = new CategoryAttrDaoImpl();
	
	private SysOperationLogService sysOperationLogService = new SysOperationLogServiceImpl();
	
	private ApplicationContext ctx = ApplicationContext.getApplicationContext();

	
	public void save(IntegralSetDto dto) {
		logger.warning("积分参数设置保存=====开始 参数传入：" + dto);
		
		List<CategoryAttr> dropDownDtoList = attrDao.findByCategoryCd(CategoryEnum.INTEGRALCONFIG.getAttrCd());

		boolean isUpdate = true;
		String value = "";
		StringBuilder oldContentSb = new StringBuilder();
		StringBuilder modifyContentSb = new StringBuilder();
		for (CategoryAttr item : dropDownDtoList) {
			value = "";
			isUpdate = true;
			if (item.getAttrCd().equals(CategoryAttrEnum.INTEGRAL_ISUSEINTEGRAL.getAttrCd())) {
				value = dto.getIsUseIntegral().toString();
			} else if (item.getAttrCd().equals(CategoryAttrEnum.INTEGRAL_REGISTERGIVEINTEGRAL.getAttrCd())) {
				value = dto.getRegisterGiveIntegral().toString();
			} else if (item.getAttrCd().equals(CategoryAttrEnum.INTEGRAL_RECOMMENDFRIENDSGIVEINTEGRAL.getAttrCd())) {
				value = dto.getRecommendFriendsGiveIntegral().toString();
			} else if (item.getAttrCd().equals(CategoryAttrEnum.INTEGRAL_LOGINGIVEINTEGRAL.getAttrCd())) {
				value = dto.getLoginGiveIntegral().toString();
			} else if (item.getAttrCd().equals(CategoryAttrEnum.INTEGRAL_FRIENDSINVESTGIVEINTEGRAL.getAttrCd())) {
				value = dto.getFriendsInvestGiveIntegral().toString();
			} else if (item.getAttrCd().equals(CategoryAttrEnum.INTEGRAL_INVESTGIVEINTEGRAL.getAttrCd())) {
				value = dto.getInvestGiveIntegral().toString();
			} else if (item.getAttrCd().equals(CategoryAttrEnum.INTEGRAL_MAXINVESTGIVEINTEGRAL.getAttrCd())) {
				value = dto.getMaxInvestGiveIntegral().toString();
			} else {
				isUpdate = false;
			}

			if (isUpdate) {
				CategoryAttr upAttr = attrDao.findByAttrCd(item.getAttrCd());
				if (!upAttr.getActualval().equals(value)) {
					oldContentSb.append("[" + item.getAttrCd() + ":" + item.getActualval() + "]");
					upAttr.setActualval(value);
					modifyContentSb.append("[" + upAttr.getAttrCd() + ":" + upAttr.getActualval() + "]");
					
					try {
						//更新数据库中的记录
						attrDao.updateCategoryAttr(Session.getInstance().getLoginUser().getUserCd(), value, item.getAttrId());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		if (oldContentSb.length() > 0) {
			oldContentSb.insert(0, "[修改前的值]");
			modifyContentSb.insert(0, "[修改后的值]");
			SysOperationLog log = sysOperationLogService.getSysLogByModifySystemConfig(Session.getInstance().getLoginUser(), modifyContentSb.toString(),
					oldContentSb.toString());
			ctx.publishEvent(new BackstageOperationSuccessEvent(log));
		}
		CacheUtil.getInstance().refresh();//更新缓存
		logger.warning("积分参数设置保存=====结束");
	}

}
