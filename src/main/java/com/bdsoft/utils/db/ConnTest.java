package com.bdsoft.utils.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnTest {

	private final static String NAME = "db2admin";
	private final static String PWD = "db2admin";

	public static void main(String[] as) throws Exception {
		Connection con = getMySqlCon();
		System.out.println(con.toString());
	}

	public static Connection getMSSqlCon() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			String url = "jdbc:sqlserver://120.55.182.199:3306;databasename=traindata";
			return DriverManager.getConnection(url, "sa", "sa");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Connection getMySqlCon() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://120.55.182.199:3306/elian";
			return DriverManager.getConnection(url, "root", "521");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Connection getOracleCon() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			return DriverManager.getConnection(url, "scott", "tiger");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Connection getDb2Con() {
		Connection con = null;
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			String url = "jdbc:db2://192.168.1.173:50000/db_dcs";
			con = DriverManager.getConnection(url, NAME, PWD);
			System.out.println(con != null ? "OK" : "BAD");
			System.out.println("Connection is ok ...");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

}
