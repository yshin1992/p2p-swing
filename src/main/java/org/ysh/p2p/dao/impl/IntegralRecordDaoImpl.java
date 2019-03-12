package org.ysh.p2p.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.ysh.p2p.dao.IntegralRecordDao;
import org.ysh.p2p.model.IntegralRecord;
import org.ysh.p2p.util.DaoUtil;
import org.ysh.p2p.util.LogUtil;
import org.ysh.p2p.util.StringUtil;
import org.ysh.p2p.vo.PageRequest;
import org.ysh.p2p.vo.PageResponse;

public class IntegralRecordDaoImpl extends AbstractDaoImpl<IntegralRecord> implements IntegralRecordDao {
	
	private static final Logger logger = LogUtil.getLogger(IntegralRecordDaoImpl.class);
	public PageResponse<IntegralRecord> queryByPage(PageRequest pageRequest,
			String memberId,Integer isAddFlag,String queryStart, String queryEnd) {
		StringBuilder CountSQL = new StringBuilder("SELECT COUNT(0) FROM integralrecord mi WHERE mi.memberId=? ");
		StringBuilder WhereSQL=new StringBuilder();
		List<Object> paramList = new ArrayList<Object>();
		
		paramList.add(memberId);
		
		if(StringUtil.isNotEmpty(isAddFlag)){
			WhereSQL.append(" AND mi.isAddFlag =?");
			paramList.add(isAddFlag);
		}
		
		if(StringUtil.isNotEmpty(queryStart)){
			WhereSQL.append(" AND mi.createTime >=?");
			paramList.add(queryStart+" 00:00:00");
		}
		if(StringUtil.isNotEmpty(queryEnd)){
			WhereSQL.append(" AND mi.createTime <=?");
			paramList.add(queryEnd+" 23:59:59");
		}
		CountSQL.append(WhereSQL);
		logger.warning("SQL->" + CountSQL.toString());
		logger.warning("Params->" + Arrays.toString(paramList.toArray()));
		
		Long recordCount = DaoUtil.getInstance().queryRecordCount(CountSQL.toString(), paramList.toArray());
		
		PageResponse<IntegralRecord> pager = new PageResponse<IntegralRecord>(pageRequest);
		pager.setTotalCount(recordCount);
		
		StringBuilder SQL = new StringBuilder("SELECT mi.integralVal,mi.amount,mi.objType,mi.remark,mi.createTime FROM integralrecord mi WHERE memberId=? ");
		SQL.append(WhereSQL);
		SQL.append(" order by createTime desc limit ?,?");
		paramList.add(pageRequest.getFirstResult());
		paramList.add(pageRequest.getPageSize());
		
		logger.warning("SQL->" + SQL.toString());
		logger.warning("Params->" + Arrays.toString(paramList.toArray()));
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		List<IntegralRecord> records = new ArrayList<IntegralRecord>();
		try{
			conn = DaoUtil.getInstance().getConnection();
			pstm = conn.prepareStatement(SQL.toString());
			for(int i=0;i<paramList.size();i++){
				pstm.setObject(i+1, paramList.get(i));
			}
			rs=pstm.executeQuery();
			
			while(rs.next()){
				IntegralRecord record = new IntegralRecord();
				record.setIntegralVal(rs.getInt(1));
				record.setAmount(rs.getBigDecimal(2));
				record.setObjType(rs.getInt(3));
				record.setRemark(rs.getString(4));
				record.setCreateTime(rs.getTimestamp(5));
				records.add(record);
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
