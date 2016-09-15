package com.bdsoft.y2010.m03;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bdsoft.utils.FileUtil;
import com.bdsoft.utils.db.Db2;

public class DbBackup {
	private static final String EXPORT_PATH = "c:/export.txt";
	private static final String IMPORT_PATH = "c:/import.txt";
	private static final String XP_PATH = "C:\\DATA\\";
	private static final String LINUX_PATH = "/home/db2inst1/data/";
	private static final String DB_USER = "DB2ADMIN";

	public static void main(String[] args) throws Exception {
		Connection con = Db2.getCon();
		createExportFile(con);
		createLoadFile(con);
		System.exit(4);
	}

	public static void createExportFile(Connection con) throws Exception {
		System.out.println("导出。。。");
		Statement st = con.createStatement();
		String sql = "select distinct name from sysibm.systables where creator='"
				+ DB_USER + "'";
		ResultSet rs = st.executeQuery(sql);
		List list = new ArrayList();
		while (rs.next()) {
			String table = rs.getString(1);
			if (table.startsWith("DCS_")) {
				list.add(table);
			}
		}
		StringBuffer sb = new StringBuffer();
		String enterFlag = "\r\n";
		for (int i = 0; i < list.size(); i++) {
			String tableName = (String) list.get(i);
			sb.append("export to " + XP_PATH + tableName
					+ ".ixf of ixf select * from " + tableName + "");
			sb.append(enterFlag);
		}
		FileUtil.writeFile(EXPORT_PATH, sb.toString(), false);
	}

	public static void createLoadFile(Connection con) throws Exception {
		System.out.println("导入。。。");
		Statement st = con.createStatement();
		String sql = "select name from sysibm.systables where creator='"
				+ DB_USER + "'";
		ResultSet rs = st.executeQuery(sql);
		List list = new ArrayList();
		while (rs.next()) {
			String table = rs.getString(1);
			if (table.startsWith("DCS_")) {
				list.add(table);
			}
		}
		StringBuffer sb = new StringBuffer();
		String enterFlag = "\r\n";
		for (int i = 0; i < list.size(); i++) {
			String tableName = (String) list.get(i);
			sb.append("db2 load from " + LINUX_PATH + tableName
					+ ".ixf of ixf insert into " + tableName
					+ " COPY NO without prompting");
			sb.append(enterFlag);
		}
		FileUtil.writeFile(IMPORT_PATH, sb.toString(), false);
	}
}