package org.ysh.p2p.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.ysh.p2p.dao.ProductDao;
import org.ysh.p2p.model.Member;
import org.ysh.p2p.model.Product;
import org.ysh.p2p.util.DaoUtil;
import org.ysh.p2p.util.LogUtil;
import org.ysh.p2p.util.StringUtil;
import org.ysh.p2p.vo.PageRequest;
import org.ysh.p2p.vo.PageResponse;
import org.ysh.p2p.vo.ProductDto;

public class ProductDaoImpl extends AbstractDaoImpl<Product> implements ProductDao {

	private static final Logger logger = LogUtil.getLogger(ProductDaoImpl.class);
	
	public PageResponse<Product> queryByPage(PageRequest request,
			ProductDto dto) {
		StringBuilder CountSQL = new StringBuilder("SELECT COUNT(0) FROM product p LEFT JOIN member m ON m.uuid=p.memberId WHERE 1=1 ");
		StringBuilder WhereSQL=new StringBuilder();
		List<Object> paramList = new ArrayList<Object>();
		
		if(StringUtil.isNotEmpty(dto.getQueryStart())){
			WhereSQL.append(" AND p.createTime >=?");
			paramList.add(dto.getQueryStart()+" 00:00:00");
		}
		if(StringUtil.isNotEmpty(dto.getQueryEnd())){
			WhereSQL.append(" AND p.createTime <=?");
			paramList.add(dto.getQueryEnd()+" 23:59:59");
		}
		
		if(StringUtil.isEmpty(dto.getDesc()) || "1".equals(dto.getDesc())){
			WhereSQL.append(" AND p.status in (10,13,14) ");
		}else{
			WhereSQL.append(" AND p.status in (11,12,15,30,31,32) ");
		}
		
		if(StringUtil.isNotEmpty(dto.getKeywords())){
			WhereSQL.append(" AND (m.realNm=? OR p.productCd=? OR p.productNm=?)");
			paramList.add(dto.getKeywords());
			paramList.add(dto.getKeywords());
			paramList.add(dto.getKeywords());
		}
		CountSQL.append(WhereSQL);
		logger.warning("SQL->" + CountSQL.toString());
		logger.warning("Params->" + Arrays.toString(paramList.toArray()));
		
		Long recordCount = DaoUtil.getInstance().queryRecordCount(CountSQL.toString(), paramList.toArray());
		
		PageResponse<Product> pager = new PageResponse<Product>(request);
		pager.setTotalCount(recordCount);
		
		StringBuilder SQL = new StringBuilder("select p.uuid,p.createTime,p.updateTime,p.status,p.productCd,p.productNm,p.rate,p.period,p.periodType,p.unitPrice,p.quantity,p.castQuantity,p.minTenderQuantity,p.maxTenderQuantity,");
		SQL.append("p.minFullQuantity,p.groundTime,p.tenderStart,p.tenderEnd,p.contractEffTime,p.fullScaleTime,p.interestStartTime,p.borrowUse,p.borrowUseName,p.repayMethod,p.tenderKind,p.tenderKindName,")
			.append("p.businessType,p.businessTypeNm,p.memberId,p.productContent,p.repaySource,p.fundUse,p.minHoldDay,p.canAutobid,p.awardRate,p.awardAmount,p.awardFail,p.amount,p.compensatory,p.compenInterest,")
			.append("p.interestAlgorithm,m.realNm from product p LEFT JOIN member m ON m.uuid=p.memberId where 1=1 ");
		SQL.append(WhereSQL);
		SQL.append(" order by p.createTime desc limit ?,?");
		paramList.add(request.getFirstResult());
		paramList.add(request.getPageSize());
		
		logger.warning("SQL->" + SQL.toString());
		logger.warning("Params->" + Arrays.toString(paramList.toArray()));
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		List<Product> records = new ArrayList<Product>();
		try{
			conn = DaoUtil.getInstance().getConnection();
			pstm = conn.prepareStatement(SQL.toString());
			for(int i=0;i<paramList.size();i++){
				pstm.setObject(i+1, paramList.get(i));
			}
			rs=pstm.executeQuery();
			
			while(rs.next()){
				Product p = new Product();
				Member m = new Member();
				p.setUuid(rs.getString(1));
				p.setCreateTime(rs.getTimestamp(2));
				p.setUpdateTime(rs.getTimestamp(3));
				p.setStatus(rs.getInt(4));
				p.setProductCd(rs.getString(5));
				p.setProductNm(rs.getString(6));
				p.setRate(rs.getBigDecimal(7));
				p.setPeriod(rs.getInt(8));
				p.setPeriodType(rs.getInt(9));
				p.setUnitPrice(rs.getBigDecimal(10));
				p.setQuantity(rs.getLong(11));
				p.setCastQuantity(rs.getLong(12));
				p.setMinTenderQuantity(rs.getLong(13));
				p.setMaxTenderQuantity(rs.getLong(14));
				p.setMinFullQuantity(rs.getLong(15));
				p.setGroundTime(rs.getTimestamp(16));
				p.setTenderStart(rs.getTimestamp(17));
				p.setTenderEnd(rs.getTimestamp(18));
				p.setContractEffTime(rs.getTimestamp(19));
				p.setFullScaleTime(rs.getTimestamp(20));
				p.setInterestStartTime(rs.getTimestamp(21));
				p.setBorrowUse(rs.getInt(22));
				p.setBorrowUseName(rs.getString(23));
				p.setRepayMethod(rs.getInt(24));
				p.setTenderKind(rs.getInt(25));
				p.setTenderKindName(rs.getString(26));
				p.setBusinessType(rs.getInt(27));
				p.setBusinessTypeNm(rs.getString(28));
				p.setMemberId(rs.getString(29));
				m.setUuid(rs.getString(29));
				p.setProductContent(rs.getString(30));
				p.setRepaySource(rs.getString(31));
				p.setFundUse(rs.getString(32));
				p.setMinHoldDay(rs.getInt(33));
				p.setCanAutobid(rs.getInt(34));
				p.setAwardRate(rs.getBigDecimal(35));
				p.setAwardAmount(rs.getBigDecimal(36));
				p.setAwardFail(rs.getInt(37));
				p.setAmount(rs.getBigDecimal(38));
				p.setCompensatory(rs.getInt(39));
				p.setCompenInterest(rs.getBigDecimal(40));
				p.setInterestAlgorithm(rs.getInt(41));
				
				m.setRealCd(rs.getString(42));
				p.setMember(m);
				records.add(p);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DaoUtil.getInstance().closeResultSet(rs);
			DaoUtil.getInstance().closeStatement(pstm);
			DaoUtil.getInstance().closeConnection(conn);
		}
		pager.setRecords(records);
		return pager;
	}


}
