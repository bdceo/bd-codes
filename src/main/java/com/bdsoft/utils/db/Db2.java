package com.bdsoft.utils.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class Db2 {
	private static final String UNAME = "db2admin";
	private static final String PWD = "123456";
	private static final String IP = "192.168.1.173";
	private static final String DB = "db_test";

	public static void main(String[] seg) throws Exception {
		Db2.getCon();
	}

	public static Connection getCon() throws Exception {
		Connection con = null;
		Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
		String url = "jdbc:db2://" + IP + ":50000/" + DB;
		con = DriverManager.getConnection(url, UNAME, PWD);
		return con;
	}

	public static void close(Connection con) throws Exception {
		if (con != null) {
			con.close();
		}
	}
}
