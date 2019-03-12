package org.ysh.p2p.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.ysh.p2p.dao.MemberIntegralDao;
import org.ysh.p2p.model.Member;
import org.ysh.p2p.model.MemberIntegral;
import org.ysh.p2p.util.DaoUtil;
import org.ysh.p2p.util.LogUtil;
import org.ysh.p2p.util.StringUtil;
import org.ysh.p2p.vo.MemberIntegralTitleDto;
import org.ysh.p2p.vo.PageRequest;
import org.ysh.p2p.vo.PageResponse;


public class MemberIntegralDaoImpl extends AbstractDaoImpl<MemberIntegral> implements MemberIntegralDao{

	private static final Logger logger = LogUtil.getLogger(MemberIntegralDaoImpl.class);
	
	public MemberIntegralTitleDto queryMemberIntegralTitle(String keyword,
			String queryStart, String queryEnd) {
		StringBuilder SQL = new StringBuilder("SELECT SUM(IFNULL(mi.integralVal,0)) AS effectiveValue");
		SQL.append(",SUM(IFNULL(mi.usedAmount,0)) AS usedAmount");
		SQL.append(",SUM(IFNULL(mi.usedValue,0)) AS usedValue");
		SQL.append(",SUM(IFNULL(mi.total,0)) AS total");
		SQL.append(" FROM memberintegral mi inner join member m on m.uuid = mi.memberId WHERE 1=1 ");
		List<String> paramList = new ArrayList<String>();
		if(StringUtil.isNotEmpty(queryStart)){
			SQL.append(" AND mi.update.updateTime >=?");
			paramList.add(queryStart+" 00:00:00");
		}
		if(StringUtil.isNotEmpty(queryEnd)){
			SQL.append(" AND mi.updateTime<=?");
			paramList.add(queryEnd+" 23:59:59");
		}
		if(StringUtil.isNotEmpty(keyword)){
			SQL.append(" AND (m.phone like ? OR m.realCd like ? OR m.realNm like ? OR m.nickName like ?)");
			paramList.add("%"+keyword+"%");
			paramList.add("%"+keyword+"%");
			paramList.add("%"+keyword+"%");
			paramList.add("%"+keyword+"%");
		}
		logger.warning("SQL->" + SQL.toString());
		logger.warning("Params->" + Arrays.toString(paramList.toArray(new String[]{})));
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		MemberIntegralTitleDto titleDto = null;
		try {
			conn = DaoUtil.getInstance().getConnection();
			pstm = conn.prepareStatement(SQL.toString());
			for(int i=0;i<paramList.size();i++){
				pstm.setString(i+1, paramList.get(i));
			}
			rs = pstm.executeQuery();
			while(rs.next()){
				titleDto = new MemberIntegralTitleDto();
				titleDto.setEffectiveValue(rs.getBigDecimal(1));
				titleDto.setUsedAmount(rs.getBigDecimal(2));
				titleDto.setUsedValue(rs.getBigDecimal(3));
				titleDto.setTotalValue(rs.getBigDecimal(4));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DaoUtil.getInstance().closeResultSet(rs);
			DaoUtil.getInstance().closeStatement(pstm);
			DaoUtil.getInstance().closeConnection(conn);
		}
		
		return titleDto;
	}

	public PageResponse<MemberIntegral> queryByPage(PageRequest pageRequest,
			String keyword, String queryStart, String queryEnd) {
		StringBuilder CountSQL = new StringBuilder("SELECT COUNT(0) FROM memberintegral mi LEFT JOIN member m ON m.uuid=mi.memberId WHERE 1=1 ");
		StringBuilder WhereSQL=new StringBuilder();
		List<Object> paramList = new ArrayList<Object>();
		if(StringUtil.isNotEmpty(queryStart)){
			WhereSQL.append(" AND mi.update.updateTime >=?");
			paramList.add(queryStart+" 00:00:00");
		}
		if(StringUtil.isNotEmpty(queryEnd)){
			WhereSQL.append(" AND mi.updateTime<=?");
			paramList.add(queryEnd+" 23:59:59");
		}
		if(StringUtil.isNotEmpty(keyword)){
			WhereSQL.append(" AND (m.phone like ? OR m.realCd like ? OR m.realNm like ? OR m.nickName like ?)");
			paramList.add("%"+keyword+"%");
			paramList.add("%"+keyword+"%");
			paramList.add("%"+keyword+"%");
			paramList.add("%"+keyword+"%");
		}
		CountSQL.append(WhereSQL);
		logger.warning("SQL->" + CountSQL.toString());
		logger.warning("Params->" + Arrays.toString(paramList.toArray()));
		
		Long recordCount = DaoUtil.getInstance().queryRecordCount(CountSQL.toString(), paramList.toArray());
		
		PageResponse<MemberIntegral> pager = new PageResponse<MemberIntegral>(pageRequest);
		pager.setTotalCount(recordCount);
		
		StringBuilder SQL = new StringBuilder("SELECT mi.integralVal,mi.total,mi.usedValue,mi.usedAmount,mi.updateTime,m.realNm,m.nickName,m.realCd,m.uuid FROM memberintegral mi INNER JOIN member m on m.uuid=mi.memberId WHERE 1=1 ");
		SQL.append(WhereSQL);
		SQL.append(" order by updateTime desc limit ?,?");
		paramList.add(pageRequest.getFirstResult());
		paramList.add(pageRequest.getPageSize());
		
		logger.warning("SQL->" + SQL.toString());
		logger.warning("Params->" + Arrays.toString(paramList.toArray()));
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		List<MemberIntegral> records = new ArrayList<MemberIntegral>();
		try{
			conn=DaoUtil.getInstance().getConnection();
			pstm = conn.prepareStatement(SQL.toString());
			for(int i=0;i<paramList.size();i++){
				pstm.setObject(i+1, paramList.get(i));
			}
			rs = pstm.executeQuery();
			while(rs.next()){
				MemberIntegral mi = new MemberIntegral();
				mi.setIntegralVal(rs.getInt(1));
				mi.setTotal(rs.getInt(2));
				mi.setUsedValue(rs.getInt(3));
				mi.setUsedAmount(rs.getBigDecimal(4));
				mi.setUpdateTime(rs.getTimestamp(5));
				Member m = new Member();
				m.setRealNm(rs.getString(6));
				m.setNickName(rs.getString(7));
				m.setRealCd(rs.getString(8));
				m.setUuid(rs.getString(9));
				mi.setMemberId(rs.getString(9));
				mi.setMember(m);
				records.add(mi);
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
