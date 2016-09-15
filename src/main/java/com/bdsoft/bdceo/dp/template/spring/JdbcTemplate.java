/**
 * JdbcTemplate.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.dp.template.spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * JdbcTemplate
 * 
 * @author bdceo
 * @date 2016-8-21 下午4:19:00
 * @version V1.0
 */
public class JdbcTemplate {

	public int update(String sql, Object... args) throws Exception {
		return update(sql, new ArgPreparedStatementSetter(args));
	}

	public int update(String sql, PreparedStatementSetter pss) throws Exception {
		return update(new SimplePreparedStatementCreator(sql), pss);
	}

	public int update(final PreparedStatementCreator psc, final PreparedStatementSetter pss)
			throws Exception {
		return execute(psc, new PreparedStatementCallback<Integer>() {
			@Override
			public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException {
				// 设置值
				pss.setValues(ps);
				// 执行
				return ps.executeUpdate();
			}
		});
	}

	public <T> T execute(PreparedStatementCreator psc, PreparedStatementCallback<T> action)
			throws SQLException {
		Connection con = null; // 获取数据库连接
		try {
			// TODO 初始化数据库连接
			PreparedStatement ps = psc.createPreparedStatement(con);
			T result = action.doInPreparedStatement(ps);
			return result;
		} catch (Exception e) {
			// TODO 处理事务回滚
			throw new RuntimeException("");
		} finally {
			// TODO 释放资源，关闭连接
		}
	}
}

interface PreparedStatementCallback<T> {
	T doInPreparedStatement(PreparedStatement ps) throws SQLException;
}

interface PreparedStatementCreator {
	PreparedStatement createPreparedStatement(Connection con) throws SQLException;
}

class SimplePreparedStatementCreator implements PreparedStatementCreator {

	private final String sql;

	public SimplePreparedStatementCreator(String sql) {
		this.sql = sql;
	}

	public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
		return con.prepareStatement(sql);
	}
}

interface PreparedStatementSetter {
	void setValues(PreparedStatement ps) throws SQLException;
}

class ArgPreparedStatementSetter implements PreparedStatementSetter {

	private final Object[] args;

	public ArgPreparedStatementSetter(Object[] args) {
		this.args = args;
	}

	public void setValues(PreparedStatement ps) throws SQLException {
		if (this.args != null) {
			for (int i = 0; i < this.args.length; i++) {
				// TODO 根据参数实际类型赋值
				ps.setString(i + 1, (String) args[i]);
			}
		}
	}
}
