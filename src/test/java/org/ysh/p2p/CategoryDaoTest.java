package org.ysh.p2p;

import java.util.List;

import org.ysh.p2p.dao.CategoryDao;
import org.ysh.p2p.dao.MemberIntegralDao;
import org.ysh.p2p.dao.impl.CategoryDaoImpl;
import org.ysh.p2p.dao.impl.MemberIntegralDaoImpl;
import org.ysh.p2p.model.Category;
import org.ysh.p2p.vo.MemberIntegralTitleDto;

public class CategoryDaoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*CategoryDao dao = new CategoryDaoImpl();
		List<Category> list = dao.findAll();
		
		System.out.print(list.size());*/
		
		MemberIntegralDao dao = new MemberIntegralDaoImpl();
		MemberIntegralTitleDto dto = dao.queryMemberIntegralTitle(null, null, null);
		System.out.println(dto);
		
	}

}
