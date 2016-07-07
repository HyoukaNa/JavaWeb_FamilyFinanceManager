package com.team30.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DBUtils {
	private static DBUtils dBUtils;
	private final static String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private final static String UserName = "C##FFM";
	private final static String PassWD = "7474174";

	private DBUtils() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			throw new ServiceException("Can not for name", e);
		}
	}

	public static Connection getConnection() {
		Connection conn = null;
		if (dBUtils == null) {
			dBUtils = new DBUtils();
		}
		try {
			conn = DriverManager.getConnection(url, UserName, PassWD);
			
		} catch (SQLException e) {
			throw new ServiceException("Can not get connection", e);
		}
		return conn;
	}

	public static void beginTransaction(Connection conn) {
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			throw new ServiceException("Can not begin transaction", e);
		}
	}

	public static void commit(Connection conn) {
		try {
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			throw new ServiceException("Can not commit transaction", e);
		}
	}

	public static void rollback(Connection conn) {
		try {
			conn.rollback();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			throw new ServiceException("Can not rollback transaction", e);
		}
	}

	public static void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeStatement(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			throw new ServiceException("Can not close statement", e);
		}
	}
}
