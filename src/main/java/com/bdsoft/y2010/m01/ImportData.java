package com.bdsoft.y2010.m01;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ImportData {

	public static void main(String[] args) {
		Connection con = getMySqlCon();
		tableProvince(con);
		tableCity(con);
		tableArea(con);
	}

	public static void tableProvince(Connection con) {
		Statement st = null;
		ResultSet rs = null;
		StringBuffer sbf = new StringBuffer();
		try {
			String sql = "select * from province order by id";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				String insert = "insert into province(id,name,code) values("
						+ rs.getInt(1) + ",'" + rs.getString(3) + "','"
						+ rs.getInt(2) + "');\n";
				sbf.append(insert);
				System.out.println(insert);
			}
			ImportData.createSql(sbf.toString(), "c:/province.sql");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void tableCity(Connection con) {
		Statement st = null;
		ResultSet rs = null;
		StringBuffer sbf = new StringBuffer();
		try {
			String sql = "select * from city order by id";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				String insert = "insert into city(id,name,pid,code) values("
						+ rs.getInt(1) + ",'" + rs.getString(3) + "','"
						+ rs.getInt(4) + "','" + rs.getInt(2) + "');\n";
				sbf.append(insert);
				System.out.println(insert);
			}
			ImportData.createSql(sbf.toString(), "c:/city.sql");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void tableArea(Connection con) {
		Statement st = null;
		ResultSet rs = null;
		StringBuffer sbf = new StringBuffer();
		try {
			String sql = "select * from areas order by id";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				String insert = "insert into areas(id,name,pid,code) values("
						+ rs.getInt(1) + ",'" + rs.getString(3) + "','"
						+ rs.getInt(4) + "','" + rs.getInt(2) + "');\n";
				sbf.append(insert);
				System.out.println(insert);
			}
			ImportData.createSql(sbf.toString(), "c:/areas.sql");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createSql(String src, String path) {
		FileOutputStream out = null;
		try {
			byte[] data = src.getBytes();
			File file = new File(path);
			out = new FileOutputStream(file, false);
			out.write(data);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getMsSqlCon() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
					.newInstance();
			String url = "jdbc:sqlserver://localhost:1433;databasename=traindata";
			return DriverManager.getConnection(url, "sa", "sa");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Connection getMySqlCon() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost:3306/test";
			return DriverManager.getConnection(url, "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Connection getOraCon() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			return DriverManager.getConnection(url, "scott", "tiger");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}