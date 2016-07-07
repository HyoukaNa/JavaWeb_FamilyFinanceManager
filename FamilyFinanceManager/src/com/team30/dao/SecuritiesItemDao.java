package com.team30.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.team30.model.SecuritiesItem;
import com.team30.model.SecuritiesUser;
import com.team30.model.User;
import com.team30.utils.DBUtils;
import com.team30.utils.DaoException;
/**
 * 用户信息表的操作（增删改查）
 */
public class SecuritiesItemDao {
	
	/**
	 * 增加用户
	 * @param user 用户信息对象
	 * @throws SQLException 
	 */
	public static boolean addSecuritiesItem(SecuritiesItem securitiesItem) throws SQLException {
		
		// 返回影响的行数
		int row = 0;
		
		// 建立数据库连接
		Connection aconnection = DBUtils.getConnection();
		
		// 要执行的SQL语句
		String sql = "insert into FFM_SECURITIESITEM(ITEMID, " +						// 账ID
			    									"ITEMOWNER, " +						// 账所属人
			    									"ITEMSTOCKNAME, " +					// 账所属股票名
			    									"ITEMSTOCKPRICE, " +				// 账交易股价
			    									"ITEMSTOCKCOUNT, " +				// 账交易数
			    									"ITEMTRADEDATE, " +					// 账交易日期			
			    									"ITEMTRADETYPE) " + 				// 账交易类型
			    		"values(?, ?, ?, ?, ?, ?, ?)";

		// 用PreparedStatement语句执行对象执行sql语句
		PreparedStatement pstmt = aconnection.prepareStatement(sql);
		try {
			pstmt.setString(1, securitiesItem.getItemId());
			pstmt.setString(2, securitiesItem.getItemOwner().getSecuritiesUserId());
			pstmt.setString(3, securitiesItem.getItemStockName());
			pstmt.setLong(4, securitiesItem.getItemStockPrice());
			pstmt.setLong(5, securitiesItem.getItemStockCount());
			pstmt.setLong(6, SecuritiesItemDao.getLongFromString(securitiesItem.getItemTradeDate()));
			pstmt.setInt(7, securitiesItem.getItemTradeType());
			
			row = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException("Error on adding securities item", e);
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
	public static void deleteSecuritiesItem(String[] itemIdList) {
		// 建立数据库连接
		Connection aconnection = DBUtils.getConnection();
				
		StringBuffer sbfSql = new StringBuffer();
		for (int i = 0; i < itemIdList.length; i++) {
			sbfSql.append("'").append(itemIdList[i]).append("'").append(",");
		}
		String sql = "delete from FFM_SECURITIESITEM where ITEMID in ("
				+ sbfSql.substring(0, sbfSql.length() - 1) + ")";
		Statement stmt = null;
		try {
			stmt = aconnection.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			throw new DaoException("Error on deleting securities item", e);
		} finally {
			DBUtils.closeStatement(stmt);
		}
	}

	/**
	 * 查询所有用户
	 * @return userList 所有用户信息的集合
	 */
	public static ArrayList<SecuritiesItem> findAllItemList() {
		// 建立数据库连接
		Connection aconnection = DBUtils.getConnection();
		String sql = "select * from FFM_SECURITIESITEM ";
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<SecuritiesItem> securitiesItemList = new ArrayList<SecuritiesItem>();
		try {
			stmt = aconnection.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				SecuritiesItem securitiesItem = new SecuritiesItem();
				securitiesItem.setItemId(rs.getString("ITEMID"));			// 用户名
				securitiesItem.setItemOwner(SecuritiesUserDao.findSecuritiesUserBySecuritiesUserID(rs.getString("ITEMOWNER")));			// 密码
				securitiesItem.setItemStockName(rs.getString("ITEMSTOCKNAME"));		// 银行卡
				securitiesItem.setItemStockPrice(Integer.parseInt(rs.getString("ITEMSTOCKPRICE")));						// 年龄
				securitiesItem.setItemStockCount(Integer.parseInt(rs.getString("ITEMSTOCKCOUNT")));			// 工资
				securitiesItem.setItemTradeDate(rs.getString("ITEMTRADEDATE"));			// 家中称谓
				securitiesItem.setItemTradeType(Integer.parseInt(rs.getString("ITEMTRADETYPE")));						// 身份证号
				securitiesItemList.add(securitiesItem);
			}
		} catch (SQLException e) {
			throw new DaoException("Error on finding Securities item", e);
		} finally {
			DBUtils.closeStatement(stmt);
		}
		return securitiesItemList;
	}


	/**
	 * 查询item
	 * @param itemid 用户身份证号
	 * @return securitiesItem  item信息
	 */
	public static SecuritiesItem findSecuritiesItemByItemId(String itemId) {
		// 建立数据库连接
		Connection aconnection = DBUtils.getConnection();
		
		String sql = "select * from FFM_SECURITIESITEM where ITEMID=?";
		SecuritiesItem securitiesItem = new SecuritiesItem();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = aconnection.prepareStatement(sql);
			pstmt.setString(1, itemId);
			rs = pstmt.executeQuery();
			if (rs.next()) {				
				securitiesItem.setItemId(rs.getString("ITEMID"));			// itemId
				securitiesItem.setItemOwner(SecuritiesUserDao.findSecuritiesUserBySecuritiesUserID(rs.getString("ITEMOWNER")));			// item名字
				securitiesItem.setItemStockName(rs.getString("ITEMSTOCKNAME"));		// item所属股票名
				securitiesItem.setItemStockPrice(Integer.parseInt(rs.getString("ITEMSTOCKPRICE")));						// 所属股票价格
				securitiesItem.setItemStockCount(Integer.parseInt(rs.getString("ITEMSTOCKCOUNT")));			// 所属股票数目
				securitiesItem.setItemTradeDate(rs.getString("ITEMTRADEDATE"));			// item交易时间
				securitiesItem.setItemTradeType(Integer.parseInt(rs.getString("ITEMTRADETYPE")));						// item交易类型
			}
		} catch (SQLException e) {
			throw new DaoException("Error on getting securities item", e);
		} finally {
			DBUtils.closeStatement(pstmt);
		}
		return securitiesItem;
	}

	/**
	 * 修改用户信息
	 * @param user 用户信息
	 */
	public static void modifySecuritiesItem(SecuritiesItem securitiesItem) {
		// 建立数据库连接
		Connection aconnection = DBUtils.getConnection();

		String sql = "update FFM_SECURITIESITEM set ITEMOWNER=?,"	+	// 账所属人
				"ITEMSTOCKNAME=?, " +				// 账所属股票名
				"ITEMSTOCKPRICE=?, " +				// 账交易股价
				"ITEMSTOCKCOUNT=?, " +				// 账交易数
				"ITEMTRADEDATE=?, " +				// 账交易日期			
				"ITEMTRADETYPE=? "	+				// 账交易类型
				"where ITEMID=?";					// 账ID 				
		PreparedStatement pstmt = null;
		try {
			pstmt = aconnection.prepareStatement(sql);
			pstmt.setString(7, securitiesItem.getItemId());
			pstmt.setString(1, securitiesItem.getItemOwner().getSecuritiesUserId());
			pstmt.setString(2, securitiesItem.getItemStockName());
			pstmt.setLong(3, securitiesItem.getItemStockPrice());
			pstmt.setLong(4, securitiesItem.getItemStockCount());
			pstmt.setLong(5, SecuritiesItemDao.getLongFromString( securitiesItem.getItemTradeDate()));
			pstmt.setInt(6, securitiesItem.getItemTradeType());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Error on updating securities item", e);
		} finally {
			DBUtils.closeStatement(pstmt);
		}
	}

	
	public static ArrayList<SecuritiesItem> findItemListWithDateAndOwner(String date, String owner, int type) {
		// 建立数据库连接
		Connection aconnection = DBUtils.getConnection();
		String sql = "select * from FFM_SECURITIESITEM ";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		ArrayList<SecuritiesItem> securitiesItemList = new ArrayList<SecuritiesItem>();
		Long start = SecuritiesItemDao.getLongFromMonthString(date);
		Long end = start+3600*24*30;
		try {
			if (type != 3) {
				sql = "select * from FFM_SECURITIESITEM  where ITEMOWNER = ? AND ITEMTRADETYPE = ? AND   ITEMTRADEDATE BETWEEN ? AND ?";
				pstmt = aconnection.prepareStatement(sql);
				pstmt.setString(1, owner);
				pstmt.setInt(2, type);
				pstmt.setLong(3, start);
				pstmt.setLong(4,end);
			} else {
				System.out.println(owner);
				sql = "select * from FFM_SECURITIESITEM  where ITEMOWNER = ?  AND  ITEMTRADEDATE BETWEEN ? AND ?";
				pstmt = aconnection.prepareStatement(sql);
				pstmt.setString(1, owner);
				pstmt.setLong(2, start);
				pstmt.setLong(3,end);
			}

			rs = pstmt.executeQuery();
			while (rs.next()) {
				SecuritiesItem securitiesItem = new SecuritiesItem();
				securitiesItem.setItemId(rs.getString("ITEMID"));            // 用户名
				securitiesItem.setItemOwner(SecuritiesUserDao.findSecuritiesUserBySecuritiesUserID(rs.getString("ITEMOWNER")));            // 密码
				securitiesItem.setItemStockName(rs.getString("ITEMSTOCKNAME"));        // 银行卡
				securitiesItem.setItemStockPrice(Integer.parseInt(rs.getString("ITEMSTOCKPRICE")));                        // 年龄
				securitiesItem.setItemStockCount(Integer.parseInt(rs.getString("ITEMSTOCKCOUNT")));            // 工资
				securitiesItem.setItemTradeDate(SecuritiesItemDao.getStringFromLong(rs.getLong("ITEMTRADEDATE")));            // 家中称谓
				securitiesItem.setItemTradeType(Integer.parseInt(rs.getString("ITEMTRADETYPE")));                        // 身份证号
				securitiesItemList.add(securitiesItem);
			}
		} catch (SQLException e) {
			throw new DaoException("Error on finding Securities item", e);
		} finally {
			DBUtils.closeStatement(pstmt);
		}
		return securitiesItemList;
	}

	public static ArrayList<SecuritiesItem> findItemListWithDateAndItemIDAndType(String startDate, String endDate, String ItemId, int type) {
		// 建立数据库连接
		Connection aconnection = DBUtils.getConnection();
		String sql = "select * from FFM_SECURITIESITEM ";
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		ArrayList<SecuritiesItem> securitiesItemList = new ArrayList<SecuritiesItem>();
		try {
			System.out.println(ItemId);
			System.out.println(type);
			System.out.println(SecuritiesItemDao.getLongFromString(endDate));
			System.out.println(SecuritiesItemDao.getLongFromString(startDate));
			if (type != 3) {
				sql = "select * from FFM_SECURITIESITEM where ITEMOWNER =? AND ITEMTRADETYPE = ?  AND ITEMTRADEDATE BETWEEN ? AND ?";
				pstmt = aconnection.prepareStatement(sql);
				pstmt.setString(1, ItemId);
				pstmt.setInt(2, type);
				pstmt.setLong(3 ,SecuritiesItemDao.getLongFromString(startDate));
				pstmt.setLong(4 ,SecuritiesItemDao.getLongFromString(endDate));
			} else {
				sql = "select * from FFM_SECURITIESITEM where ITEMOWNER =? AND ITEMTRADEDATE BETWEEN ? AND ?";
				pstmt = aconnection.prepareStatement(sql);
				pstmt.setString(1, ItemId);
				pstmt.setLong(2 ,SecuritiesItemDao.getLongFromString(startDate));
				pstmt.setLong(3 ,SecuritiesItemDao.getLongFromString(endDate));
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SecuritiesItem securitiesItem = new SecuritiesItem();
				securitiesItem.setItemId(rs.getString("ITEMID"));            // 用户名
				securitiesItem.setItemOwner(SecuritiesUserDao.findSecuritiesUserBySecuritiesUserID(rs.getString("ITEMOWNER")));            // 密码
				securitiesItem.setItemStockName(rs.getString("ITEMSTOCKNAME"));        // 银行卡
				securitiesItem.setItemStockPrice(rs.getInt("ITEMSTOCKPRICE"));                        // 年龄
				securitiesItem.setItemStockCount(rs.getInt("ITEMSTOCKCOUNT"));            // 工资
				securitiesItem.setItemTradeDate(SecuritiesItemDao.getStringFromLong( rs.getLong("ITEMTRADEDATE")));            // 家中称谓
				securitiesItem.setItemTradeType(rs.getInt("ITEMTRADETYPE"));                        // 身份证号
				securitiesItemList.add(securitiesItem);
			}
		} catch (SQLException e) {
			throw new DaoException("Error on finding Securities item", e);
		} finally {
			DBUtils.closeStatement(stmt);
		}
		return securitiesItemList;
	}
	
	public static String getStringFromLong(long timeStamp) {
		Date date = new Date(timeStamp * 1000);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(date);
	}

	private static long getLongFromString(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = null;
		long datelong = 0;
		try {
			datelong = sdf.parse(dateStr).getTime() / 1000;

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return datelong;
	}
	
	private static long getLongFromMonthString(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date date = null;
		long datelong = 0;
		try {
			datelong = sdf.parse(dateStr).getTime() / 1000;

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return datelong;
	}
	
}
