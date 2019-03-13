package org.ysh.p2p.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.ysh.p2p.dao.ItemTypeDao;
import org.ysh.p2p.model.ItemType;
import org.ysh.p2p.util.DaoUtil;
import org.ysh.p2p.util.LogUtil;
import org.ysh.p2p.util.StringUtil;
import org.ysh.p2p.vo.PageRequest;
import org.ysh.p2p.vo.PageResponse;

public class ItemTypeDaoImpl extends AbstractDaoImpl<ItemType> implements ItemTypeDao{

	private final static Logger logger = LogUtil.getLogger(ItemTypeDaoImpl.class);
	
	public PageResponse<ItemType> queryByPage(PageRequest request,
			String keyword) {
		StringBuilder CountSQL = new StringBuilder("SELECT COUNT(0) FROM itemtype m WHERE 1=1 ");
		StringBuilder WhereSQL=new StringBuilder();
		List<Object> paramList = new ArrayList<Object>();
		
		if(StringUtil.isNotEmpty(keyword)){
			WhereSQL.append(" AND itemTypeNm=?");
			paramList.add(keyword);
		}
		CountSQL.append(WhereSQL);
		logger.warning("SQL->" + CountSQL.toString());
		logger.warning("Params->" + Arrays.toString(paramList.toArray()));
		
		Long recordCount = DaoUtil.getInstance().queryRecordCount(CountSQL.toString(), paramList.toArray());
		
		PageResponse<ItemType> pager = new PageResponse<ItemType>(request);
		pager.setTotalCount(recordCount);
		
		StringBuilder SQL = new StringBuilder("SELECT itemTypeId,createBy,createTime,biller,calOnlineFlag,charger,edited,feeType,itemTypeCd,itemTypeNm,itemTypePCd,maxAmount,minAmount,node,periodOrDay,rate,rateReferened FROM itemtype WHERE 1=1 ");
		SQL.append(WhereSQL);
		SQL.append(" order by createTime desc limit ?,?");
		paramList.add(request.getFirstResult());
		paramList.add(request.getPageSize());
		
		logger.warning("SQL->" + SQL.toString());
		logger.warning("Params->" + Arrays.toString(paramList.toArray()));
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		List<ItemType> records = new ArrayList<ItemType>();
		try{
			conn = DaoUtil.getInstance().getConnection();
			pstm = conn.prepareStatement(SQL.toString());
			for(int i=0;i<paramList.size();i++){
				pstm.setObject(i+1, paramList.get(i));
			}
			rs=pstm.executeQuery();
			
			while(rs.next()){
				ItemType type = new ItemType();
				type.setItemTypeId(rs.getString(1));
				type.setCreateBy(rs.getString(2));
				type.setCreateTime(rs.getTimestamp(3));
				type.setBiller(rs.getInt(4));
				type.setCalOnlineFlag(rs.getInt(5));
				type.setCharger(rs.getInt(6));
				type.setEdited(rs.getInt(7));
				type.setFeeType(rs.getString(8));
				type.setItemTypeCd(rs.getString(9));
				type.setItemTypeNm(rs.getString(10));
				type.setItemTypePCd(rs.getString(11));
				type.setMaxAmount(rs.getBigDecimal(12));
				type.setMinAmount(rs.getBigDecimal(13));
				type.setNode(rs.getInt(14));
				type.setPeriodOrDay(rs.getInt(15));
				type.setRate(rs.getBigDecimal(16));
				type.setRateReferened(rs.getInt(17));
				records.add(type);
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

	public String getCategoryMaxCd(String feeType) {
		String SQL = "select max(itemTypeCd) from itemtype where itemTypeCd like ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		Integer maxCd = null;
		try{
			conn = DaoUtil.getInstance().getConnection();
			pstm = conn.prepareStatement(SQL.toString());
			pstm.setString(1, feeType +"%");
			rs = pstm.executeQuery();
			while(rs.next()){
				maxCd = rs.getInt(1);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			DaoUtil.getInstance().closeResultSet(rs);
			DaoUtil.getInstance().closeStatement(pstm);
			DaoUtil.getInstance().closeConnection(conn);
		}
		return maxCd ==null?"0":String.valueOf(maxCd);
	}

}
