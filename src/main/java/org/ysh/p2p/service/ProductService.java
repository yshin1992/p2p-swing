package org.ysh.p2p.service;

import org.ysh.p2p.model.Product;
import org.ysh.p2p.vo.PageRequest;
import org.ysh.p2p.vo.PageResponse;
import org.ysh.p2p.vo.ProductDto;

public interface ProductService extends AbstractService<Product> {

	public PageResponse<Product> queryByPage(PageRequest request,ProductDto dto);
}
