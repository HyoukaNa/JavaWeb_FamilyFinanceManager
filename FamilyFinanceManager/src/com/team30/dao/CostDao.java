package com.team30.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.util.List;

import com.team30.model.*;
import com.team30.utils.DBUtils;
import com.team30.utils.DaoException;

/**
 * 支出信息表的操作（增删改查）
 */
public class CostDao {
	
	/**
	 * 增加支出项
	 * @param Cost 支出信息对象
	 * @throws SQLException 
	 */
	public static boolean addCost(Cost cost) throws SQLException {
		
		// 返回影响的行数
		int row = 0;
		
		// 建立数据库连接
		Connection aconnection = DBUtils.getConnection();
		
		// 要执行的SQL语句
		String sql = "insert into FFM_COST (COSTID, " +			// 支出ID
			    									"COSTTYPE, " +		// 支出类型
			    									"COSTOWNER, " +	// 支出所有人
			    									"COSTCOUNT, " +	// 支出额
			    									"COSTDATE, " +		// 支出日期
			    									"COSTSOURCE, " +	// 支出来源
			    									"COSTDETAILS) " +	// 支出明细
			    		"values(?, ?, ?, ?, ?, ?, ?)";

		// 用PreparedStatement语句执行对象执行sql语句
		PreparedStatement pstmt = aconnection.prepareStatement(sql);
		
		try {
			pstmt.setString(1, cost.getCostId());
			pstmt.setInt(2, cost.getCostType());
			pstmt.setString(3, cost.getCostOwner().getIdCard());
			pstmt.setInt(4, cost.getCostCount());
			pstmt.setString(5, cost.getCostDate());
			pstmt.setString(6, cost.getCostSource());
			pstmt.setString(7, cost.getCostDetails());
			row = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Error on adding cost", e);
		} finally {
			DBUtils.closeStatement(pstmt);
		}
		// 返回结果
		System.out.println("增加了" + row + "行！");
		return (row > 0 ? true : false);
		
	}

	/**
	 * 删除支出项
	 * @param costList 支出信息的集合
	 */
	public static void deleteCosts(String[] costList) {
		// 建立数据库连接
		Connection aconnection = DBUtils.getConnection();
				
		StringBuffer sbfSql = new StringBuffer();
		for (int i = 0; i < costList.length; i++) {
			sbfSql.append("'").append(costList[i]).append("'").append(",");
		}
		String sql = "delete from FFM_COST where COSTID in ("
				+ sbfSql.substring(0, sbfSql.length() - 1) + ")";
		Statement stmt = null;
		try {
			stmt = aconnection.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			throw new DaoException("Error on deleting cost", e);
		} finally {
			DBUtils.closeStatement(stmt);
		}
	}

	/**
	 * 查询所有支出项
	 * @return costList 所有支出信息的集合
	 */
	public static ArrayList<Cost> findAllCostList() {
		// 建立数据库连接
		Connection aconnection = DBUtils.getConnection();
		String sql = "select * from FFM_COST ";
		Statement stmt = null;
		ResultSet rs = null;
		
		ArrayList<Cost> costList = new ArrayList<Cost>();
		try {
			stmt = aconnection.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Cost cost = new Cost();
				cost.setCostId(rs.getString("COSTID"));							// 支出ID
				cost.setCostType(rs.getInt("COSTTYPE"));						// 支出类型
				cost.setCostOwner(
						UserDao.findUserByIdCard(rs.getString("COSTOWNER")));	// 支出所有人
				cost.setCostCount(rs.getInt("COSTCOUNT"));					// 支出额
				cost.setCostDate(rs.getString("COSTDATE"));					// 支出日期
				cost.setCostSource(rs.getString("COSTSOURCE"));				// 支出来源
				cost.setCostDetails(rs.getString("COSTDETAILS"));				// 支出明细
				costList.add(cost);
			}
		} catch (SQLException e) {
			throw new DaoException("Error on finding cost", e);
		} finally {
			DBUtils.closeStatement(stmt);
		}
		return costList;
	}
	
	/**
	 * 查询支出项目
	 * @param costId 支出项编号
	 * @return cost  支出项信息
	 */
	public static Cost findCostByCostId(String costId) {
		// 建立数据库连接
		Connection aconnection = DBUtils.getConnection();
		
		String sql = "select * from FFM_COST where COSTID=?";
		Cost cost = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = aconnection.prepareStatement(sql);
			pstmt.setString(1, costId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cost = new Cost();
				cost.setCostId(rs.getString("COSTID"));							// 支出ID
				cost.setCostType(rs.getInt("COSTTYPE"));						// 支出类型
				cost.setCostOwner(
						UserDao.findUserByIdCard(rs.getString("COSTOWNER")));	// 支出所有人
				cost.setCostCount(rs.getInt("COSTCOUNT"));						// 支出额
				cost.setCostDate(rs.getString("COSTDATE"));					// 支出日期
				cost.setCostSource(rs.getString("COSTSOURCE"));				// 支出来源
				cost.setCostDetails(rs.getString("COSTDETAILS"));				// 支出明细
			}
		} catch (SQLException e) {
			throw new DaoException("Error on getting cost", e);
		} finally {
			DBUtils.closeStatement(pstmt);
		}
		return cost;
	}

	/**
	 * 查询支出项目
	 * @param costOwnerName 支出者姓名
	 * @return costList 所有支出信息的集合
	 */
	public static ArrayList<Cost> findCostByCostOwnerName(String costOwnerName) {
		// 建立数据库连接
		Connection aconnection = DBUtils.getConnection();
		
		User owner = UserDao.findUserByUserName(costOwnerName);
		ArrayList<Cost> costList = new ArrayList<Cost>();
		
		String sql = "select * from FFM_COST where COSTOWNER=" + owner.getIdCard();
		
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = aconnection.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Cost cost = new Cost();
				cost.setCostId(rs.getString("COSTID"));							// 收入ID
				cost.setCostType(rs.getInt("COSTTYPE"));						// 收入类型
				cost.setCostOwner(
						UserDao.findUserByIdCard(rs.getString("COSTOWNER")));	// 收入所有人
				cost.setCostCount(rs.getInt("COSTCOUNT"));					// 收入额
				cost.setCostDate(rs.getString("COSTDATE"));					// 收入日期
				cost.setCostSource(rs.getString("COSTSOURCE"));				// 收入来源
				cost.setCostDetails(rs.getString("COSTDETAILS"));				// 收入明细
				costList.add(cost);
			}
		} catch (SQLException e) {
			throw new DaoException("Error on finding cost", e);
		} finally {
			DBUtils.closeStatement(stmt);
		}
		return costList;
	}
	
	/**
	 * 查询支出项目
	 * @param costId 支出日期
	 * @return costList 所有支出信息的集合
	 */
	public static ArrayList<Cost> findCostByCostDate(String costDate) {
		// 建立数据库连接
		Connection aconnection = DBUtils.getConnection();
		
		ArrayList<Cost> costList = new ArrayList<Cost>();
		
		String sql = "select * from FFM_COST where COSTDATE=" + costDate;
		
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = aconnection.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Cost cost = new Cost();
				cost.setCostId(rs.getString("COSTID"));							// 收入ID
				cost.setCostType(rs.getInt("COSTTYPE"));						// 收入类型
				cost.setCostOwner(
						UserDao.findUserByIdCard(rs.getString("COSTOWNER")));	// 收入所有人
				cost.setCostCount(rs.getInt("COSTCOUNT"));					// 收入额
				cost.setCostDate(rs.getString("COSTDATE"));					// 收入日期
				cost.setCostSource(rs.getString("COSTSOURCE"));				// 收入来源
				cost.setCostDetails(rs.getString("COSTDETAILS"));				// 收入明细
				costList.add(cost);
			}
		} catch (SQLException e) {
			throw new DaoException("Error on finding cost", e);
		} finally {
			DBUtils.closeStatement(stmt);
		}
		return costList;
	}
	/**
	 * 修改支出项信息
	 * @param cost 支出项信息
	 */
	public static void modifyCost(Cost cost) {
		// 建立数据库连接
		Connection aconnection = DBUtils.getConnection();
				
		String sql = "update FFM_COST set COSTTYPE=?, " +	// 支出类型
										  "COSTOWNER=?, " + 		// 支出所有人
										  "COSTCOUNT=?, " +		// 支出额
										  "COSTDATE=?, " +		// 支出时间
										  "COSTSOURCE=?, " +		// 支出来源
										  "COSTDETAILS=? " +		// 支出明细
										  "where COSTID=?";		// 支出ID
		PreparedStatement pstmt = null;
		try {
			pstmt = aconnection.prepareStatement(sql);
			pstmt.setInt(1, cost.getCostType());
			pstmt.setString(2, cost.getCostOwner().getIdCard());
			pstmt.setInt(3, cost.getCostCount());
			pstmt.setString(4, cost.getCostDate());
			pstmt.setString(5, cost.getCostSource());
			pstmt.setString(6, cost.getCostDetails());
			pstmt.setString(7, cost.getCostId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Error on updating cost", e);
		} finally {
			DBUtils.closeStatement(pstmt);
		}
	}
	
	
	public static ArrayList<Cost> findCostWithDateAndOwner(String date,String owner) {
		// 建立数据库连接
		Connection aconnection = DBUtils.getConnection();
		String sql = null;
		ResultSet rs = null;
		ArrayList<Cost> costList = new ArrayList<Cost>();
		PreparedStatement pstmt = null;


		try {
		if (date == null){
			sql = "select * from FFM_COST where COSTOWNER=?";
			pstmt = aconnection.prepareStatement(sql);
			pstmt.setString(1,owner);
		}else if (owner == null){
			if (date.length()<=7)
				sql = "select * from FFM_COST where COSTDATE like "+date +"%";
			else
				sql = "select * from FFM_COST where COSTDATE like "+date;
			pstmt = aconnection.prepareStatement(sql);
		}else {
			if (date.length()<=8){
				System.out.println(date+"%");
				sql = "select * from FFM_COST where COSTOWNER=? AND COSTDATE like "+date+"%";
				pstmt = aconnection.prepareStatement(sql);
				pstmt.setString(1,owner);
			}
			else{
				sql = "select * from FFM_COST where COSTOWNER=? AND COSTDATE like "+date;
			pstmt = aconnection.prepareStatement(sql);
			pstmt.setString(1,owner);
			}
		}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Cost cost = new Cost();
				cost = new Cost();
				cost.setCostId(rs.getString("COSTID"));							// 支出ID
				cost.setCostType(rs.getInt("COSTTYPE"));						// 支出类型
				cost.setCostOwner(
						UserDao.findUserByIdCard(rs.getString("COSTOWNER")));	// 支出所有人
				cost.setCostCount(rs.getInt("COSTCOUNT"));						// 支出额
				cost.setCostDate(rs.getString("COSTDATE"));					// 支出日期
				cost.setCostSource(rs.getString("COSTSOURCE"));				// 支出来源
				cost.setCostDetails(rs.getString("COSTDETAILS"));				// 支出明细
				costList.add(cost);
			}
		} catch (SQLException e) {
			throw new DaoException("Error on finding cost", e);
		} finally {
			DBUtils.closeStatement(pstmt);
		}
		return costList;
	}

}

