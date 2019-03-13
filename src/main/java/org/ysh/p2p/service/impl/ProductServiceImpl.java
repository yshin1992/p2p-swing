package org.ysh.p2p.service.impl;

import org.ysh.p2p.dao.AbstractDao;
import org.ysh.p2p.dao.ProductDao;
import org.ysh.p2p.dao.impl.ProductDaoImpl;
import org.ysh.p2p.model.Product;
import org.ysh.p2p.service.ProductService;
import org.ysh.p2p.vo.PageRequest;
import org.ysh.p2p.vo.PageResponse;
import org.ysh.p2p.vo.ProductDto;

public class ProductServiceImpl extends AbstractServiceImpl<Product> implements ProductService{

	private ProductDao productDao= new ProductDaoImpl();
	
	public AbstractDao<Product> getDao() {
		return productDao;
	}

	public PageResponse<Product> queryByPage(PageRequest request, ProductDto dto) {
		return productDao.queryByPage(request, dto);
	}

}
