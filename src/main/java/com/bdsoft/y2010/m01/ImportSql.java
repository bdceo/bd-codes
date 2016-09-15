package com.bdsoft.y2010.m01;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ImportSql {

	public static String url = "jdbc:sqlserver://localhost:1433;DatabaseName=my51aspx";
	public static String driver = "com.microsoft.jdbc.sqlserver.SQLServerDriver";

	public static void main(String[] args) {
		Connection con = ImportSql.getCon();
		impProvince(con);
		impCity(con);
		impArea(con);
	}

	public static Connection getCon() {
		try {
			Class.forName(driver).newInstance();
			return DriverManager.getConnection(url, "sa", "sa");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void impProvince(Connection con) {
		String sql = "select id,name,code from province";// id,code,name
		Statement st = null;
		ResultSet rs = null;
		try {
			StringBuffer sb = new StringBuffer();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				sb.append("insert into province(id,name,code) values("
						+ rs.getInt(1) + ",'" + rs.getString(2) + "','"
						+ rs.getString(3) + "');");
			}
			ImportSql.createSql(sb.toString(), "c:/province.sql");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void impCity(Connection con) {
		String sql = "select id,name,code,provinceId from city";// id,code,name,provinceId
		Statement st = null;
		ResultSet rs = null;
		try {
			StringBuffer sb = new StringBuffer();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				sb.append("insert into city(id,name,code,pid) values("
						+ rs.getInt(1) + ",'" + rs.getString(2) + "','"
						+ rs.getString(3) + "','" + rs.getString(4) + "');");
			}
			ImportSql.createSql(sb.toString(), "c:/city.sql");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void impArea(Connection con) {
		String sql = "select id,name,code,cityId from area";// id,code,name,cityId
		Statement st = null;
		ResultSet rs = null;
		try {
			StringBuffer sb = new StringBuffer();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				sb.append("insert into areas(id,name,code,cid) values("
						+ rs.getInt(1) + ",'" + rs.getString(2) + "','"
						+ rs.getString(3) + "','" + rs.getString(4) + "');");
			}
			ImportSql.createSql(sb.toString(), "c:/area.sql");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void createSql(String src, String fileName) {
		FileOutputStream out = null;
		try {
			byte[] data = src.getBytes();
			File file = new File(fileName);
			out = new FileOutputStream(file, false);
			out.write(data);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}