package org.ysh.p2p.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.ysh.p2p.dao.CategoryAttrDao;
import org.ysh.p2p.model.Category;
import org.ysh.p2p.model.CategoryAttr;
import org.ysh.p2p.util.DaoUtil;

public class CategoryAttrDaoImpl extends AbstractDaoImpl<CategoryAttr> implements CategoryAttrDao{
	
	public List<CategoryAttr> findByCategoryCd(String paramString) {
		String SQL="select a.attrId,a.createBy,a.createTime,a.effTime,a.expTime,a.listSort,a.state,a.stateTime,a.actualval,a.attrCd,a.attrNm,a.attrRequired,a.defaultVal,a.remark,a.categoryId"
				+ ",c.categoryId,c.createTime,c.createBy,c.effTime,c.expTime,c.listSort,c.state,c.stateTime,c.categoryCd,c.categoryDesc,c.categoryNm,c.configed,c.edited,c.remark from categoryattr a,category c where a.state='F0A' and a.categoryId=c.categoryId and a.categoryId=(select categoryId from category where categoryCd=?)";
		List<CategoryAttr> resultList = new ArrayList<CategoryAttr>();
		try {
			Connection conn = DaoUtil.getInstance().getConnection();
			PreparedStatement pstm = conn.prepareStatement(SQL);
			pstm.setString(1, paramString);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				CategoryAttr attr = new CategoryAttr();
				attr.setUuid(rs.getString(1));
				attr.setCreateBy(rs.getString(2));
				attr.setCreateTime(rs.getTimestamp(3));
				attr.setEffTime(rs.getTimestamp(4));
				attr.setExpTime(rs.getTimestamp(5));
				attr.setListSort(rs.getInt(6));
				attr.setState(rs.getString(7));
				attr.setStateTime(rs.getTimestamp(8));
				attr.setActualval(rs.getString(9));
				attr.setAttrCd(rs.getString(10));
				attr.setAttrNm(rs.getString(11));
				attr.setAttrRequired(rs.getInt(12));
				attr.setDefaultVal(rs.getString(13));
				attr.setRemark(rs.getString(14));
				attr.setCategoryId(rs.getString(15));
				
				Category category = new Category();
				category.setUuid(rs.getString(16));
				category.setCreateTime(rs.getTimestamp(17));
				category.setCreateBy(rs.getString(18));
				category.setEffTime(rs.getTimestamp(19));
				category.setExpTime(rs.getTimestamp(20));
				category.setListSort(rs.getInt(21));
				category.setState(rs.getString(22));
				category.setCreateTime(rs.getTimestamp(23));
				category.setCategoryCd(rs.getString(24));
				category.setCategoryDesc(rs.getString(25));
				category.setCategoryNm(rs.getString(26));
				category.setConfiged(rs.getInt(27));
				category.setEdited(rs.getInt(28));
				category.setRemark(rs.getString(29));
				attr.setCategory(category);
				
				resultList.add(attr);
			}
			
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return resultList;
	}

	public List<CategoryAttr> findByCategoryId(String paramString) {
		String SQL="select a.attrId,a.createBy,a.createTime,a.effTime,a.expTime,a.listSort,a.state,a.stateTime,a.actualval,a.attrCd,a.attrNm,a.attrRequired,a.defaultVal,a.remark,a.categoryId"
				+ ",c.categoryId,c.createTime,c.createBy,c.effTime,c.expTime,c.listSort,c.state,c.stateTime,c.categoryCd,c.categoryDesc,c.categoryNm,c.configed,c.edited,c.remark from categoryattr a,category c where a.state='F0A' and a.categoryId=c.categoryId and a.categoryId=?";
		List<CategoryAttr> resultList = new ArrayList<CategoryAttr>();
		try {
			Connection conn = DaoUtil.getInstance().getConnection();
			PreparedStatement pstm = conn.prepareStatement(SQL);
			pstm.setString(1, paramString);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				CategoryAttr attr = new CategoryAttr();
				attr.setUuid(rs.getString(1));
				attr.setCreateBy(rs.getString(2));
				attr.setCreateTime(rs.getTimestamp(3));
				attr.setEffTime(rs.getTimestamp(4));
				attr.setExpTime(rs.getTimestamp(5));
				attr.setListSort(rs.getInt(6));
				attr.setState(rs.getString(7));
				attr.setStateTime(rs.getTimestamp(8));
				attr.setActualval(rs.getString(9));
				attr.setAttrCd(rs.getString(10));
				attr.setAttrNm(rs.getString(11));
				attr.setAttrRequired(rs.getInt(12));
				attr.setDefaultVal(rs.getString(13));
				attr.setRemark(rs.getString(14));
				attr.setCategoryId(rs.getString(15));
				
				Category category = new Category();
				category.setUuid(rs.getString(16));
				category.setCreateTime(rs.getTimestamp(17));
				category.setCreateBy(rs.getString(18));
				category.setEffTime(rs.getTimestamp(19));
				category.setExpTime(rs.getTimestamp(20));
				category.setListSort(rs.getInt(21));
				category.setState(rs.getString(22));
				category.setCreateTime(rs.getTimestamp(23));
				category.setCategoryCd(rs.getString(24));
				category.setCategoryDesc(rs.getString(25));
				category.setCategoryNm(rs.getString(26));
				category.setConfiged(rs.getInt(27));
				category.setEdited(rs.getInt(28));
				category.setRemark(rs.getString(29));
				attr.setCategory(category);
				
				resultList.add(attr);
			}
			
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return resultList;
	}

	public CategoryAttr findByAttrCd(String paramString) {
		String SQL="select a.attrId,a.createBy,a.createTime,a.effTime,a.expTime,a.listSort,a.state,a.stateTime,a.actualval,a.attrCd,a.attrNm,a.attrRequired,a.defaultVal,a.remark,a.categoryId"
				+ ",c.categoryId,c.createTime,c.createBy,c.effTime,c.expTime,c.listSort,c.state,c.stateTime,c.categoryCd,c.categoryDesc,c.categoryNm,c.configed,c.edited,c.remark from categoryattr a,category c where a.state='F0A' and a.categoryId=c.categoryId and c.attrCd=?";
		CategoryAttr attr = null;
		try {
			Connection conn = DaoUtil.getInstance().getConnection();
			PreparedStatement pstm = conn.prepareStatement(SQL);
			pstm.setString(1, paramString);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				attr = new CategoryAttr();
				attr.setUuid(rs.getString(1));
				attr.setCreateBy(rs.getString(2));
				attr.setCreateTime(rs.getTimestamp(3));
				attr.setEffTime(rs.getTimestamp(4));
				attr.setExpTime(rs.getTimestamp(5));
				attr.setListSort(rs.getInt(6));
				attr.setState(rs.getString(7));
				attr.setStateTime(rs.getTimestamp(8));
				attr.setActualval(rs.getString(9));
				attr.setAttrCd(rs.getString(10));
				attr.setAttrNm(rs.getString(11));
				attr.setAttrRequired(rs.getInt(12));
				attr.setDefaultVal(rs.getString(13));
				attr.setRemark(rs.getString(14));
				attr.setCategoryId(rs.getString(15));
				
				Category category = new Category();
				category.setUuid(rs.getString(16));
				category.setCreateTime(rs.getTimestamp(17));
				category.setCreateBy(rs.getString(18));
				category.setEffTime(rs.getTimestamp(19));
				category.setExpTime(rs.getTimestamp(20));
				category.setListSort(rs.getInt(21));
				category.setState(rs.getString(22));
				category.setCreateTime(rs.getTimestamp(23));
				category.setCategoryCd(rs.getString(24));
				category.setCategoryDesc(rs.getString(25));
				category.setCategoryNm(rs.getString(26));
				category.setConfiged(rs.getInt(27));
				category.setEdited(rs.getInt(28));
				category.setRemark(rs.getString(29));
				attr.setCategory(category);
				
			}
			
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return attr;
	}

	public void updateCategoryAttr(String paramString1, String paramString2,
			String paramString3) {
		
	}

}
