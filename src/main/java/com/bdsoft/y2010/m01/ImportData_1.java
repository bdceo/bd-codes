package com.bdsoft.y2010.m01;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ImportData_1 {

	public static void main(String[] args) {
		Connection con = getMySqlCon();
		// tableProvince(con);
		// tableCity(con);
		// tableArea(con);
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
		int[] pids = { 110000, 120000, 310000, 500000 };
		int[] cids = { 500100, 500200, 500300 };
		Statement st = null;
		ResultSet rs = null;
		StringBuffer sbf = new StringBuffer();
		int id = 400;
		try {
			String sql = "";
			for (int i = 0; i < cids.length; i++) {
				sql = "select * from areas where fatherID=" + cids[i];
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					id++;
					String insert = "insert into city(id,name,pid,code) values("
							+ id
							+ ",'"
							+ rs.getString(3)
							+ "','"
							+ pids[3]
							+ "','" + rs.getInt(2) + "');\n";
					sbf.append(insert);
					System.out.println(insert);
				}
			}
			ImportData.createSql(sbf.toString(), "c:/ch.sql");
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

	public static void insert(Connection mysql, int pid, String name) {
		String sql = "insert into city(pid,name) values(?,?)";
		PreparedStatement ps = null;
		try {
			ps = mysql.prepareStatement(sql);
			ps.setInt(1, pid);
			ps.setString(2, name);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("��ţ�" + pid + "����ƣ�" + name);
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