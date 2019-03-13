package org.ysh.p2p;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.TestCase;

import org.ysh.p2p.dao.ProductDao;
import org.ysh.p2p.dao.impl.ProductDaoImpl;
import org.ysh.p2p.model.Product;
import org.ysh.p2p.util.DateUtil;
import org.ysh.p2p.util.StringUtil;
import org.ysh.p2p.vo.PageRequest;
import org.ysh.p2p.vo.PageResponse;
import org.ysh.p2p.vo.ProductDto;

public class ProductDaoTest extends TestCase {

	private ProductDao productDao = new ProductDaoImpl();
	
	public void testAdd(){
		Product p =new Product();
		
		p.setUuid(StringUtil.generateUuid());
		Date now = new Date();
		p.setCreateTime(now);
		p.setUpdateTime(now);
		p.setStatus(10);
		String pCd = "p-"+new SimpleDateFormat("yyyyMMddHHmmss").format(now);
		p.setProductCd(pCd);
		p.setProductNm("兴信融-2018014");
		p.setRate(new BigDecimal(0.074));
		p.setPeriod(170);
		p.setPeriodType(1);
		p.setUnitPrice(new BigDecimal(100));
		p.setQuantity(1000L);
		p.setMinFullQuantity(10L);
		p.setMaxTenderQuantity(1000L);
		p.setMinFullQuantity(1000L);
		p.setGroundTime(DateUtil.addHours(now, 4));
		p.setTenderStart(DateUtil.addHours(now, 4));
		p.setTenderEnd(DateUtil.addHours(now, 6));
		p.setBorrowUse(1);
		p.setBorrowUseName("资金周转");
		p.setRepayMethod(1);
		p.setTenderKind(2);
		p.setTenderKindName("信用标");
		p.setBusinessType(1);
		p.setBusinessTypeNm("兴信融");
		p.setMemberId("502205f1b6ea442486ae1d74389f73ed");
		p.setProductContent("该项目实际借款人为某汽车配件公司的高管，现因资金周转需求，通过本平台一次性融资总额20万元，该项目2017年在莫愁信融平台融资，目前已到期，进行再次融资。此次借款，由南京市金陵文化科技小额贷款有限公司提供担保，借款人的反担保措施为南京仙林某投资有限公司以及公司法人代表夫妻双方提供连带责任担保，风险可控。");
		p.setFundUse("个人投资经营的资金周转。");
		p.setRepaySource("1、 借款人按借款协议还款 2、 借款人未按时还款时，南京市金陵文化科技小额贷款有限公司提供代偿 3、 南京仙林某投资有限公司以及公司法人代表夫妻双方提供担保以及借款人个人连带责任担保 4、 法律援助");
		p.setAmount(new BigDecimal(100000));
		p.setInterestAlgorithm(1);
		
		try {
			productDao.add(p, Product.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testQueryByPage(){
		ProductDto dto = new ProductDto();
		PageResponse<Product> pager = productDao.queryByPage(new PageRequest(), dto);
		System.out.println(pager.getTotalPage());
		System.out.println(pager.getTotalCount());
	}
	
}
