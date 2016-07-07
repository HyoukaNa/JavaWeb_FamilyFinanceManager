package com.team30.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.util.List;

import com.team30.model.User;
import com.team30.utils.DBUtils;
import com.team30.utils.DaoException;

/**
 * 用户信息表的操作（增删改查）
 */
public class UserDao {
	
	/**
	 * 增加用户
	 * @param user 用户信息对象
	 * @throws SQLException 
	 */
	public static boolean addUser(User user) throws SQLException {
		
		// 返回影响的行数
		int row = 0;
		
		// 建立数据库连接
		Connection aconnection = DBUtils.getConnection();
		
		// 要执行的SQL语句
		String sql = "insert into FFM_USER(USERNAME, " +	// 用户名
			    									"PASSWORD, " +			// 密码
			    									"CREDITCARD, " +			// 银行卡
			    									"AGE, " +						// 年龄
			    									"SALARY, " +					// 工资
			    									"FAMILYCALL, " +			// 家中称谓
			    									"IDCARD, " +					// 身份证号
			    									"ISADMIN) " + 				// 是否管理员
			    		"values(?, ?, ?, ?, ?, ?, ?, ?)";

		// 用PreparedStatement语句执行对象执行sql语句
		PreparedStatement pstmt = aconnection.prepareStatement(sql);
		
		try {
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getCreditCard());
			pstmt.setLong(4, user.getAge());
			pstmt.setLong(5, user.getSalary());
			pstmt.setString(6, user.getFamilyCall());
			pstmt.setString(7, user.getIdCard());
			if(user.isAdmin() == true){
				pstmt.setLong(8, 1);
			} else {
				pstmt.setLong(8, 0);
			}
			row = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Error on adding user", e);
		} finally {
			DBUtils.closeStatement(pstmt);
		}

		// 返回结果
		System.out.println("增加了" + row + "行！");
		return (row > 0 ? true : false);
		
	}

	/**
	 * 删除用户
	 * @param idCradList 用户身份证号的集合
	 */
	public static void deleteUsers(String[] idCradList) {
		// 建立数据库连接
		Connection aconnection = DBUtils.getConnection();
				
		StringBuffer sbfSql = new StringBuffer();
		for (int i = 0; i < idCradList.length; i++) {
			sbfSql.append("'").append(idCradList[i]).append("'").append(",");
		}
		String sql = "delete from FFM_USER where IDCARD in ("
				+ sbfSql.substring(0, sbfSql.length() - 1) + ")";
		Statement stmt = null;
		try {
			stmt = aconnection.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			throw new DaoException("Error on deleting user", e);
		} finally {
			DBUtils.closeStatement(stmt);
		}
	}

	/**
	 * 查询所有用户
	 * @return userList 所有用户信息的集合
	 */
	public static ArrayList<User> findAllUserList() {
		// 建立数据库连接
		Connection aconnection = DBUtils.getConnection();
		String sql = "select * from FFM_USER ";
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<User> userList = new ArrayList<User>();
		try {
			stmt = aconnection.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				User user = new User();
				user.setUserName(rs.getString("USERNAME"));			// 用户名
				user.setPassword(rs.getString("PASSWORD"));			// 密码
				user.setCreditCard(rs.getString("CREDITCARD"));		// 银行卡
				user.setAge(Integer.parseInt(rs.getString("AGE")));						// 年龄
				user.setSalary(Integer.parseInt(rs.getString("SALARY")));			// 工资
				user.setFamilyCall(rs.getString("FAMILYCALL"));			// 家中称谓
				user.setIdCard(rs.getString("IDCARD"));						// 身份证号
				if(rs.getString("ISADMIN") == "true") {						// 是否管理员
					user.setAdmin(true);
				} else {
					user.setAdmin(false);
				}
				userList.add(user);
			}
		} catch (SQLException e) {
			throw new DaoException("Error on finding user", e);
		} finally {
			DBUtils.closeStatement(stmt);
		}
		return userList;
	}

	/**
	 * 查询用户
	 * @param idCard 用户身份证号
	 * @return user  用户信息
	 */
	public static User findUserByIdCard(String idCard) {
		// 建立数据库连接
		Connection aconnection = DBUtils.getConnection();
		
		String sql = "select * from FFM_USER where IDCARD=?";
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = aconnection.prepareStatement(sql);
			pstmt.setString(1, idCard);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUserName(rs.getString("USERNAME"));			// 用户名
				user.setPassword(rs.getString("PASSWORD"));			// 密码
				user.setCreditCard(rs.getString("CREDITCARD"));		// 银行卡
				user.setAge(Integer.parseInt(rs.getString("AGE")));	// 年龄
				user.setSalary(Integer.parseInt(rs.getString("SALARY")));			// 工资
				user.setFamilyCall(rs.getString("FAMILYCALL"));			// 家中称谓
				user.setIdCard(rs.getString("IDCARD"));						// 身份证号
				if(Integer.parseInt(rs.getString("ISADMIN")) != 0) {						// 是否管理员
					user.setAdmin(true);
				} else {
					user.setAdmin(false);
				}
			}/* else {
				user = new User();
				user.setUserName("未知用户");	// 用户名
				user.setPassword("123456");		// 密码
				user.setCreditCard(null);				// 银行卡
				user.setAge(0);							// 年龄
				user.setSalary(0);						// 工资
				user.setFamilyCall(null);				// 家中称谓
				user.setIdCard(idCard);				// 身份证号
				user.setAdmin(false);					// 是否管理员
			}*/
		} catch (SQLException e) {
			throw new DaoException("Error on getting user", e);
		} finally {
			DBUtils.closeStatement(pstmt);
		}
		return user;
	}
	
	/**
	 * 查询用户
	 * @param userName 用户名
	 * @return user  用户信息
	 */
	public static User findUserByUserName(String userName) {
		// 建立数据库连接
		Connection aconnection = DBUtils.getConnection();
		
		String sql = "select * from FFM_USER where USERNAME=?";
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = aconnection.prepareStatement(sql);
			pstmt.setString(1, userName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUserName(rs.getString("USERNAME"));					// 用户名
				user.setPassword(rs.getString("PASSWORD"));					// 密码
				user.setCreditCard(rs.getString("CREDITCARD"));				// 银行卡
				user.setAge(Integer.parseInt(rs.getString("AGE")));			// 年龄
				user.setSalary(Integer.parseInt(rs.getString("SALARY")));	// 工资
				user.setFamilyCall(rs.getString("FAMILYCALL"));					// 家中称谓
				user.setIdCard(rs.getString("IDCARD"));								// 身份证号
				if(Integer.parseInt(rs.getString("ISADMIN")) != 0) {			// 是否管理员
					user.setAdmin(true);	
				} else {
					user.setAdmin(false);
				} 
			} /*else {
				user = new User();
				user.setUserName(userName);	// 用户名
				user.setPassword("123456");		// 密码
				user.setCreditCard(null);				// 银行卡
				user.setAge(0);							// 年龄
				user.setSalary(0);						// 工资
				user.setFamilyCall(null);				// 家中称谓
				user.setIdCard("none");				// 身份证号
				user.setAdmin(false);					// 是否管理员
			}*/
		} catch (SQLException e) {
			throw new DaoException("Error on getting user", e);
		} finally {
			DBUtils.closeStatement(pstmt);
		}
		return user;
	}

	/**
	 * 修改用户信息
	 * @param user 用户信息
	 */
	public static void modifyUser(User user) {
		// 建立数据库连接
		Connection aconnection = DBUtils.getConnection();
				
		String sql = "update FFM_USER set USERNAME=?, " +	// 用户名
										  "PASSWORD=?, " +						// 密码
										  "CREDITCARD=?, " + 					// 银行卡
										  "AGE=?, " +									// 年龄
										  "SALARY=?, " +							// 工资
										  "FAMILYCALL=?, " +					// 家中称谓
										  "ISADMIN=? " +							// 身份证号
										  "where IDCARD=?";						// 是否管理员
		PreparedStatement pstmt = null;
		try {
			pstmt = aconnection.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			//pstmt.setString(2, UserDao.findUserByIdCard(user.getIdCard()).getPassword());
			pstmt.setString(3, user.getCreditCard());
			pstmt.setLong(4, user.getAge());
			pstmt.setLong(5, user.getSalary());
			pstmt.setString(6, user.getFamilyCall());
			if(user.isAdmin() == true){
				pstmt.setLong(7, 1);
			} else {
				pstmt.setLong(7, 0);
			}
			pstmt.setString(8, user.getIdCard());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Error on updating user", e);
		} finally {
			DBUtils.closeStatement(pstmt);
		}
	}
	
	public static User findUserByuserName(String userName) {
		// 建立数据库连接
		Connection aconnection = DBUtils.getConnection();

		String sql = "select * from FFM_USER where USERNAME=?";
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = aconnection.prepareStatement(sql);
			pstmt.setString(1, userName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUserName(rs.getString("USERNAME"));			// 用户名
				user.setPassword(rs.getString("PASSWORD"));			// 密码
				user.setCreditCard(rs.getString("CREDITCARD"));		// 银行卡
				user.setAge(Integer.parseInt(rs.getString("AGE")));						// 年龄
				user.setSalary(Integer.parseInt(rs.getString("SALARY")));			// 工资
				user.setFamilyCall(rs.getString("FAMILYCALL"));			// 家中称谓
				user.setIdCard(rs.getString("IDCARD"));						// 身份证号
				if(Integer.parseInt(rs.getString("ISADMIN")) != 0) {						// 是否管理员
					user.setAdmin(true);
				} else {
					user.setAdmin(false);
				}
			}
		} catch (SQLException e) {
			throw new DaoException("Error on getting user", e);
		} finally {
			DBUtils.closeStatement(pstmt);
		}
		return user;
	}


}
