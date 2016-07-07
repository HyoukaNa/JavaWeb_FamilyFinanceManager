package com.team30.dao;

import com.team30.model.SecuritiesUser;
import com.team30.model.User;
import com.team30.utils.DBUtils;
import com.team30.utils.DaoException;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by yangxu on 16/7/1.
 */
public class SecuritiesUserDao {
    public static boolean addSecuritiesUser(SecuritiesUser securitiesUser) throws SQLException {

        // 返回影响的行数
        int row = 0;

        // 建立数据库连接
        Connection aconnection = DBUtils.getConnection();

        // 要执行的SQL语句
        String sql = "insert into FFM_SECURITIESUSER(SECURITIESUSERID, " +	// 证券号码
                "SECURITIESUSERTYPE, " +			// 证券账户类型
                "SECURITIESUSERREST, " +			// 账户余额
                "SECURITIESUSEROWNER) " +			// 证券账户持有者
                "values(?, ?, ?, ?)";

        // 用PreparedStatement语句执行对象执行sql语句
        PreparedStatement pstmt = aconnection.prepareStatement(sql);

        try {
            pstmt.setString(1, securitiesUser.getSecuritiesUserId());
            pstmt.setInt(2, securitiesUser.getSecuritiesUserType());
            pstmt.setInt(3, securitiesUser.getSecuritiesUserRest());
            pstmt.setString(4, securitiesUser.getSecuritiesUserOwner().getIdCard());
            row = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error on adding securitiesUser", e);
        } finally {
            DBUtils.closeStatement(pstmt);
        }

        // 返回结果
        System.out.println("增加了" + row + "行！");
        return (row > 0 ? true : false);

    }

    /**
     * 删除用户
     * @param securitiesUserIdList 用户身份证号的集合
     */
    public static void deleteSecuritiesUsers(String[] securitiesUserIdList) {
        // 建立数据库连接
        Connection aconnection = DBUtils.getConnection();

        StringBuffer sbfSql = new StringBuffer();
        for (int i = 0; i < securitiesUserIdList.length; i++) {
            sbfSql.append("'").append(securitiesUserIdList[i]).append("'").append(",");
        }
        String sql = "delete from FFM_SECURITIESUSER where SECURITIESUSERID in ("
                + sbfSql.substring(0, sbfSql.length() - 1) + ")";
        Statement stmt = null;
        try {
            stmt = aconnection.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DaoException("Error on deleting securitiesUser", e);
        } finally {
            DBUtils.closeStatement(stmt);
        }
    }

    /**
     * 查询所有用户
     * @return userList 所有用户信息的集合
     */
    public static ArrayList<SecuritiesUser> findSecuritiesUserList() {
        // 建立数据库连接
        Connection aconnection = DBUtils.getConnection();
        String sql = "select * from FFM_SECURITIESUSER ";
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<SecuritiesUser> userList = new ArrayList<SecuritiesUser>();
        try {
            stmt = aconnection.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                SecuritiesUser securitiesUser = new SecuritiesUser();
                securitiesUser.setSecuritiesUserId(rs.getString("SECURITIESUSERID"));			    // 证券账户ID
                securitiesUser.setSecuritiesUserType( rs.getInt("SECURITIESUSERTYPE"));       // 证券种类
                securitiesUser.setSecuritiesUserRest(rs.getInt("SECURITIESUSERREST"));		        // 账户余额
                securitiesUser.setSecuritiesUserOwner(UserDao.findUserByIdCard(rs.getString("SECURITIESUSEROWNER")));  // 账户持有者
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
     * @param securitiesUserID 用户身份证号
     * @return user  用户信息
     */
    public static SecuritiesUser findSecuritiesUserBySecuritiesUserID(String securitiesUserID) {
        // 建立数据库连接
        Connection aconnection = DBUtils.getConnection();

        String sql = "select * from FFM_SECURITIESUSER where SECURITIESUSERID=?";
        SecuritiesUser securitiesUser = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = aconnection.prepareStatement(sql);
            pstmt.setString(1, securitiesUserID);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                securitiesUser = new SecuritiesUser();
                securitiesUser.setSecuritiesUserId(rs.getString("SECURITIESUSERID"));			// 证券账户ID
                securitiesUser.setSecuritiesUserType(rs.getInt("SECURITIESUSERTYPE"));			// 证券账户种类
                securitiesUser.setSecuritiesUserRest(rs.getInt("SECURITIESUSERREST"));		    // 账户余额
                securitiesUser.setSecuritiesUserOwner( UserDao.findUserByIdCard(rs.getString("SECURITIESUSEROWNER")));	// 账户持有者
            }
        } catch (SQLException e) {
            throw new DaoException("Error on getting user", e);
        } finally {
            DBUtils.closeStatement(pstmt);
        }
        return securitiesUser;
    }

    /**
     * 修改用户信息
     * @param securitiesUser 用户信息
     */
    public static void modifySecuritiesUser(SecuritiesUser securitiesUser) {
        // 建立数据库连接
        Connection aconnection = DBUtils.getConnection();

        String sql = "update FFM_SECURITIESUSER set SECURITIESUSERTYPE=?, " +	 // 种类	
                "SECURITIESUSERREST=?, " +						         		// 余额
                "SECURITIESUSEROWNER=? " +										// 持有者
                "where SECURITIESUSERID=?";								        // ID
        PreparedStatement pstmt = null;
        try {
            pstmt = aconnection.prepareStatement(sql);
            pstmt.setString(4, securitiesUser.getSecuritiesUserId());
            pstmt.setInt(1, securitiesUser.getSecuritiesUserType());
            pstmt.setInt(2, securitiesUser.getSecuritiesUserRest());
            pstmt.setString(3, securitiesUser.getSecuritiesUserOwner().getIdCard());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error on updating user", e);
        } finally {
            DBUtils.closeStatement(pstmt);
        }
    }

    public static ArrayList<SecuritiesUser> findSecuritiesUserByOwner(String owner) {
        // 建立数据库连接
        Connection aconnection = DBUtils.getConnection();

        String sql = "select * from FFM_SECURITIESUSER where SECURITIESUSEROWNER=?";
        SecuritiesUser securitiesUser = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<SecuritiesUser> result = new ArrayList<SecuritiesUser>();
        try {
            pstmt = aconnection.prepareStatement(sql);
            pstmt.setString(1, owner);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                securitiesUser = new SecuritiesUser();
                securitiesUser.setSecuritiesUserId(rs.getString("SECURITIESUSERID"));			// 证券账户ID
                securitiesUser.setSecuritiesUserType(rs.getInt("SECURITIESUSERTYPE"));			// 证券账户种类
                securitiesUser.setSecuritiesUserRest(rs.getInt("SECURITIESUSERREST"));		    // 账户余额
                securitiesUser.setSecuritiesUserOwner( UserDao.findUserByIdCard(rs.getString("SECURITIESUSEROWNER")));// 账户持有者
                result.add(securitiesUser);
            }
        } catch (SQLException e) {
            throw new DaoException("Error on getting user", e);
        } finally {
            DBUtils.closeStatement(pstmt);
        }
        return result;
    }
}
