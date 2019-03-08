package org.ysh.p2p;

import java.util.List;

import org.ysh.p2p.dao.CategoryDao;
import org.ysh.p2p.dao.impl.CategoryDaoImpl;
import org.ysh.p2p.model.Category;

public class CategoryDaoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CategoryDao dao = new CategoryDaoImpl();
		List<Category> list = dao.findAll();
		
		System.out.print(list.size());
	}

}
