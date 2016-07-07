package com.team30.dao;

import com.team30.model.SecuritiesUser;
import com.team30.model.Stock;
import com.team30.model.User;
import com.team30.utils.DBUtils;
import com.team30.utils.DaoException;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by yangxu on 16/7/1.
 */
public class StockDao {
    public static boolean addStock(Stock stock) throws SQLException {

        // 返回影响的行数
        int row = 0;

        // 建立数据库连接
        Connection aconnection = DBUtils.getConnection();

        // 要执行的SQL语句
        String sql = "insert into FFM_STOCK(STOCKOWNERID, " +	// 股票ID
                "STOCKID, " +			                    // 股票名称
                "STOCKNAME, " +		                	// 股票持有者
                "STOCKCOUNT, " +				    		// 持股数
                "STOCKPRICE) " +				        	// 股票单价
                "values(?, ?, ?, ?, ?)";

        // 用PreparedStatement语句执行对象执行sql语句
        PreparedStatement pstmt = aconnection.prepareStatement(sql);

        try {
            pstmt.setString(3, stock.getStockId());
            pstmt.setString(2, stock.getStockName());
            pstmt.setString(1, stock.getStockOwner().getSecuritiesUserId());
            pstmt.setInt(4, stock.getStockCount());
            pstmt.setInt(5, stock.getStockPrice());
            row = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error on adding stock", e);
        } finally {
            DBUtils.closeStatement(pstmt);
        }

        // 返回结果
        System.out.println("增加了" + row + "行！");
        return (row > 0 ? true : false);

    }

    /**
     * 删除用户
     * @param stockIDList 用户身份证号的集合
     */
    public static void deleteStocks(String[] stockIDList) {
        // 建立数据库连接
        Connection aconnection = DBUtils.getConnection();

        StringBuffer sbfSql = new StringBuffer();
        for (int i = 0; i < stockIDList.length; i++) {
            sbfSql.append("'").append(stockIDList[i]).append("'").append(",");
        }
        String sql = "delete from FFM_STOCK where STOCKID in ("
                + sbfSql.substring(0, sbfSql.length() - 1) + ")";
        Statement stmt = null;
        try {
            stmt = aconnection.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DaoException("Error on deleting stock", e);
        } finally {
            DBUtils.closeStatement(stmt);
        }
    }

    /**
     * 查询所有用户
     * @return Stock 所有用户信息的集合
     */
    public static ArrayList<Stock> findAllStockList() {
        // 建立数据库连接
        Connection aconnection = DBUtils.getConnection();
        String sql = "select * from FFM_Stock ";
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Stock> userList = new ArrayList<Stock>();
        try {
            stmt = aconnection.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Stock stock = new Stock();
                stock.setStockId(rs.getString("STOCKID"));			  
                stock.setStockName(rs.getString("STOCKNAME"));			
                stock.setStockCount(Integer.parseInt(rs.getString("STOCKCOUNT")));		    // 银行卡
                stock.setStockPrice(Integer.parseInt(rs.getString("STOCKPRICE")));			// 年龄
                stock.setStockOwner(SecuritiesUserDao.findSecuritiesUserBySecuritiesUserID(rs.getString("STOCKOWNER")));			// 工资
            }
        } catch (SQLException e) {
            throw new DaoException("Error on finding stock", e);
        } finally {
            DBUtils.closeStatement(stmt);
        }
        return userList;
    }

    /**
     * 查询用户
     * @param stockId 用户身份证号
     * @return user  用户信息
     */
    public static Stock findStockByStockId(String stockId) {
        // 建立数据库连接
        Connection aconnection = DBUtils.getConnection();

        String sql = "select * from FFM_STOCK where STOCKID=?";
        Stock stock = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = aconnection.prepareStatement(sql);
            pstmt.setString(1, stockId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                stock = new Stock();
                stock.setStockName(rs.getString("STOCKNAME"));			    // 股票名称
                stock.setStockId(rs.getString("STOCKID"));			        // 股票ID
                stock.setStockPrice(rs.getInt("STOCKPRICE"));		        // 股票价格
                stock.setStockCount(rs.getInt("STOCKCOUNT"));				// 股票数量
                stock.setStockOwner(SecuritiesUserDao.findSecuritiesUserBySecuritiesUserID(rs.getString("STOCKOWNER")));			// 股票持有者
            }
        } catch (SQLException e) {
            throw new DaoException("Error on getting stock", e);
        } finally {
            DBUtils.closeStatement(pstmt);
        }
        return stock;
    }

    /**
     * 修改用户信息
     * @param stock 用户信息
     */
    public static void modifyStock(Stock stock) {
        // 建立数据库连接
        Connection aconnection = DBUtils.getConnection();

        String sql = "update FFM_STOCK set STOCKNAME=?, " +	// 股票名
                "STOCKPRICE=?, " + 					// 股票价格
                "STOCKCOUNT=?, " +					// 股票持有数目
                "STOCKOWNER=?" +					//股票持有人
                "where STOCKID=?";					// 股票ID

        PreparedStatement pstmt = null;
        try {
            pstmt = aconnection.prepareStatement(sql);
            pstmt.setString(5, stock.getStockId());
            pstmt.setString(1, stock.getStockName());
            pstmt.setInt(2, stock.getStockPrice());
            pstmt.setInt(3, stock.getStockCount());
            pstmt.setString(4, stock.getStockOwner().getSecuritiesUserId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error on updating stock", e);
        } finally {
            DBUtils.closeStatement(pstmt);
        }
    }

}
