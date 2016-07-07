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
 * 收入信息表的操作（增删改查）
 */
public class IncomeDao {
	
	/**
	 * 增加收入项
	 * @param Income 收入信息对象
	 * @throws SQLException 
	 */
	public static boolean addIncome(Income income) throws SQLException {
		
		// 返回影响的行数
		int row = 0;
		
		// 建立数据库连接
		Connection aconnection = DBUtils.getConnection();
		
		// 要执行的SQL语句
		String sql = "insert into FFM_INCOME(INCOMEID, " +			// 收入ID
			    									"INCOMETYPE, " +		// 收入类型
			    									"INCOMEOWNER, " +	// 收入所有人
			    									"INCOMECOUNT, " +	// 收入额
			    									"INCOMEDATE, " +		// 收入日期
			    									"INCOMESOURCE, " +	// 收入来源
			    									"INCOMEDETAILS) " +	// 收入明细
			    		"values(?, ?, ?, ?, ?, ?, ?)";

		// 用PreparedStatement语句执行对象执行sql语句
		PreparedStatement pstmt = aconnection.prepareStatement(sql);
		
		try {
			pstmt.setString(1, income.getIncomeId());
			pstmt.setLong(2, income.getIncomeType());
			pstmt.setString(3, income.getIncomeOwner().getIdCard());
			pstmt.setLong(4, income.getIncomeCount());
			pstmt.setString(5, income.getIncomeDate());
			pstmt.setString(6, income.getIncomeSource());
			pstmt.setString(7, income.getIncomeDetails());
			row = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Error on adding income", e);
		} finally {
			DBUtils.closeStatement(pstmt);
		}
		// 返回结果
		System.out.println("增加了" + row + "行！");
		return (row > 0 ? true : false);
		
	}

	/**
	 * 删除收入项
	 * @param incomeList 收入信息的id的集合
	 */
	public static void deleteIncomes(String[] incomeList) {
		// 建立数据库连接
		Connection aconnection = DBUtils.getConnection();
				
		StringBuffer sbfSql = new StringBuffer();
		for (int i = 0; i < incomeList.length; i++) {
			sbfSql.append("'").append(incomeList[i]).append("'").append(",");
		}
		String sql = "delete from FFM_INCOME where INCOMEID in ("
				+ sbfSql.substring(0, sbfSql.length() - 1) + ")";
		Statement stmt = null;
		try {
			stmt = aconnection.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			throw new DaoException("Error on deleting income", e);
		} finally {
			DBUtils.closeStatement(stmt);
		}
	}

	/**
	 * 查询所有收入项
	 * @return incomeList 所有收入信息的集合
	 */
	public static ArrayList<Income> findAllIncomeList() {
		// 建立数据库连接
		Connection aconnection = DBUtils.getConnection();
		String sql = "select * from FFM_INCOME ";
		Statement stmt = null;
		ResultSet rs = null;
		
		ArrayList<Income> incomeList = new ArrayList<Income>();
		try {
			stmt = aconnection.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Income income = new Income();
				income.setIncomeId(rs.getString("INCOMEID"));							// 收入ID
				income.setIncomeType(rs.getInt("INCOMETYPE"));						// 收入类型
				income.setIncomeOwner(
						UserDao.findUserByIdCard(rs.getString("INCOMEOWNER")));	// 收入所有人
				income.setIncomeCount(rs.getInt("INCOMECOUNT"));					// 收入额
				income.setIncomeDate(rs.getString("INCOMEDATE"));					// 收入日期
				income.setIncomeSource(rs.getString("INCOMESOURCE"));				// 收入来源
				income.setIncomeDetails(rs.getString("INCOMEDETAILS"));				// 收入明细
				incomeList.add(income);
			}
		} catch (SQLException e) {
			throw new DaoException("Error on finding income", e);
		} finally {
			DBUtils.closeStatement(stmt);
		}
		return incomeList;
	}
	
	/**
	 * 查询收入项目
	 * @param incomeId 收入项编号
	 * @return income  收入项信息
	 */
	public static Income findIncomeByIncomeId(String incomeId) {
		// 建立数据库连接
		Connection aconnection = DBUtils.getConnection();
		
		String sql = "select * from FFM_INCOME where INCOMEID=?";
		Income income = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = aconnection.prepareStatement(sql);
			pstmt.setString(1, incomeId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				income = new Income();
				income.setIncomeId(rs.getString("INCOMEID"));							// 收入ID
				income.setIncomeType(rs.getInt("INCOMETYPE"));						// 收入类型
				income.setIncomeOwner(
						UserDao.findUserByIdCard(rs.getString("INCOMEOWNER")));	// 收入所有人
				income.setIncomeCount(rs.getInt("INCOMECOUNT"));					// 收入额
				income.setIncomeDate(rs.getString("INCOMEDATE"));					// 收入日期
				income.setIncomeSource(rs.getString("INCOMESOURCE"));				// 收入来源
				income.setIncomeDetails(rs.getString("INCOMEDETAILS"));				// 收入明细
			}
		} catch (SQLException e) {
			throw new DaoException("Error on getting income", e);
		} finally {
			DBUtils.closeStatement(pstmt);
		}
		return income;
	}
	
	/**
	 * 查询收入项目
	 * @param incomeOwnerName 收入者姓名
	 * @return incomeList 所有收入信息的集合
	 */
	public static ArrayList<Income> findIncomeByIncomeOwnerName(String incomeOwnerName) {
		// 建立数据库连接
		Connection aconnection = DBUtils.getConnection();
		
		User owner = UserDao.findUserByUserName(incomeOwnerName);
		ArrayList<Income> incomeList = new ArrayList<Income>();
		
		String sql = "select * from FFM_INCOME where INCOMEOWNER=" + owner.getIdCard();
		
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = aconnection.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Income income = new Income();
				income.setIncomeId(rs.getString("INCOMEID"));							// 收入ID
				income.setIncomeType(rs.getInt("INCOMETYPE"));						// 收入类型
				income.setIncomeOwner(
						UserDao.findUserByIdCard(rs.getString("INCOMEOWNER")));	// 收入所有人
				income.setIncomeCount(rs.getInt("INCOMECOUNT"));					// 收入额
				income.setIncomeDate(rs.getString("INCOMEDATE"));					// 收入日期
				income.setIncomeSource(rs.getString("INCOMESOURCE"));				// 收入来源
				income.setIncomeDetails(rs.getString("INCOMEDETAILS"));				// 收入明细
				incomeList.add(income);
			}
		} catch (SQLException e) {
			throw new DaoException("Error on finding income", e);
		} finally {
			DBUtils.closeStatement(stmt);
		}
		return incomeList;
	}
	
	/**
	 * 查询收入项目
	 * @param incomeId 收入日期
	 * @return incomeList 所有收入信息的集合
	 */
	public static ArrayList<Income> findIncomeByIncomeDate(String incomeDate) {
		// 建立数据库连接
		Connection aconnection = DBUtils.getConnection();
		
		ArrayList<Income> incomeList = new ArrayList<Income>();
		
		String sql = "select * from FFM_INCOME where INCOMEDATE=" + incomeDate;
		
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = aconnection.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Income income = new Income();
				income.setIncomeId(rs.getString("INCOMEID"));							// 收入ID
				income.setIncomeType(rs.getInt("INCOMETYPE"));						// 收入类型
				income.setIncomeOwner(
						UserDao.findUserByIdCard(rs.getString("INCOMEOWNER")));	// 收入所有人
				income.setIncomeCount(rs.getInt("INCOMECOUNT"));					// 收入额
				income.setIncomeDate(rs.getString("INCOMEDATE"));					// 收入日期
				income.setIncomeSource(rs.getString("INCOMESOURCE"));				// 收入来源
				income.setIncomeDetails(rs.getString("INCOMEDETAILS"));				// 收入明细
				incomeList.add(income);
			}
		} catch (SQLException e) {
			throw new DaoException("Error on finding income", e);
		} finally {
			DBUtils.closeStatement(stmt);
		}
		return incomeList;
	}

	/**
	 * 修改收入项信息
	 * @param income 收入项信息
	 */
	public static void modifyIncome(Income income) {
		// 建立数据库连接
		Connection aconnection = DBUtils.getConnection();
				
		String sql = "update FFM_INCOME set INCOMETYPE=?, " +	// 收入类型
										  "INCOMEOWNER=?, " + 		// 收入所有人
										  "INCOMECOUNT=?, " +		// 收入额
										  "INCOMEDATE=?, " +			// 收入时间
										  "INCOMESOURCE=?, " +		// 收入来源
										  "INCOMEDETAILS=? " +		// 收入明细
										  "where INCOMEID=?";		// 收入ID
		PreparedStatement pstmt = null;
		try {
			pstmt = aconnection.prepareStatement(sql);
			pstmt.setInt(1, income.getIncomeType());
			pstmt.setString(2, income.getIncomeOwner().getIdCard());
			pstmt.setInt(3, income.getIncomeCount());
			pstmt.setString(4, income.getIncomeDate());
			pstmt.setString(5, income.getIncomeSource());
			pstmt.setString(6, income.getIncomeDetails());
			pstmt.setString(7, income.getIncomeId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Error on updating income", e);
		} finally {
			DBUtils.closeStatement(pstmt);
		}
	}
	
	public static ArrayList<Income> findIncomeByDateAndOwener(String date,String owner) {
		// 建立数据库连接
		Connection aconnection = DBUtils.getConnection();
		String sql;
		ArrayList<Income> incomeList = new ArrayList<Income>();
		PreparedStatement pstmt = null;
		try {
			if (date == null){
				sql = "select * from FFM_INCOME where INCOMEOWNER=?";
				pstmt = aconnection.prepareStatement(sql);
				pstmt.setString(1,owner);
			}else if (owner == null){
				if (date.length()>7)
					sql = "select * from FFM_INCOME where INCOMEDATE like "+date +"%";
				else
					sql = "select * from FFM_INCOME where INCOMEDATE like "+date;
				pstmt = aconnection.prepareStatement(sql);
			}else {
				if (date.length()>7)
					sql = "select * from FFM_INCOME where INCOMEOWNER=? AND INCOMEDATE like "+date +"%";
				else
					sql = "select * from FFM_INCOME where INCOMEOWNER=? AND INCOMEDATE like "+date;
				pstmt = aconnection.prepareStatement(sql);
				pstmt.setString(1,owner);
			}
			Income income = null;
			ResultSet rs = null;

			rs = pstmt.executeQuery();
			if (rs.next()) {
				income = new Income();
				income.setIncomeId(rs.getString("INCOMEID"));							// 收入ID
				income.setIncomeType(rs.getInt("INCOMETYPE"));						// 收入类型
				income.setIncomeOwner(
						UserDao.findUserByIdCard(rs.getString("INCOMEOWNER")));	// 收入所有人
				income.setIncomeCount(rs.getInt("INCOMECOUNT"));					// 收入额
				income.setIncomeDate(rs.getString("INCOMEDATE"));					// 收入日期
				income.setIncomeSource(rs.getString("INCOMESOURCE"));				// 收入来源
				income.setIncomeDetails(rs.getString("INCOMEDETAILS"));				// 收入明细
				incomeList.add(income);
			}
		} catch (SQLException e) {
			throw new DaoException("Error on getting income", e);
		}
		finally {
			DBUtils.closeStatement(pstmt);
		}
		return incomeList;
	}
}
