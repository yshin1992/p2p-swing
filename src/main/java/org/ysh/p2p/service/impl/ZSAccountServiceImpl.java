package org.ysh.p2p.service.impl;

import org.ysh.p2p.dao.AbstractDao;
import org.ysh.p2p.dao.ZSAccountDao;
import org.ysh.p2p.dao.impl.ZSAccountDaoImpl;
import org.ysh.p2p.model.ZSAccount;
import org.ysh.p2p.service.ZSAccountService;

public class ZSAccountServiceImpl extends AbstractServiceImpl<ZSAccount> implements ZSAccountService {

	private ZSAccountDao zsAccountDao = new ZSAccountDaoImpl();
	
	public AbstractDao<ZSAccount> getDao() {
		return zsAccountDao;
	}

}
