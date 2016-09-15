package com.bdsoft.bdceo.spring.jdbc.pattern.templatemethod;

import java.sql.Connection;
import java.sql.Statement;

// 模拟Spring的JDBCTemplate
public class JdbcTemplate {

	// 模板方法
	public final Object execute(String sql) {
		Connection con = null;
		Statement stmt = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			// 处理具体数据逻辑
			Object value = executeWithStatement(stmt, sql);
			return value;
		} catch (Exception e) {
			throw new RuntimeException();			
		} finally {
			closeStatement(stmt);
			closeConnection(con);
		}
	}

	// 基于回调的内部类实现
	public final Object execute(StatementCallback callback) {
		Connection con = null;
		Statement stmt = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			// 转交给回调接口处理
			Object value = callback.doWithStatement(stmt);
			return value;
		} catch (Exception e) {
			throw new RuntimeException();	
		} finally {
			closeStatement(stmt);
			closeConnection(con);
		}
	}

	// protected abstract Object executeWithStatement(Statement stmt, String
	// sql);

	private Object executeWithStatement(Statement stmt, String sql) {
		return null;
	}

	private void closeConnection(Connection con) {

	}

	private void closeStatement(Statement stmt) {

	}

	private Connection getConnection() {
		return null;
	}

}
