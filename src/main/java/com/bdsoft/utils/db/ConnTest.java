package com.bdsoft.utils.db;

import java.sql.Connection;
import java.sql.DriverManager;
 
public class ConnTest {

	private final static String NAME = "db2admin";
	private final static String PWD = "db2admin";

	public static void main(String[] as) throws Exception {
		Connection con = null;
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance(); 
			String url = "jdbc:db2://192.168.1.173:50000/db_dcs";
			con = DriverManager.getConnection(url, NAME, PWD);
			System.out.println(con != null ? "OK" : "BAD");
			System.out.println("Connection is ok ...");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

}
