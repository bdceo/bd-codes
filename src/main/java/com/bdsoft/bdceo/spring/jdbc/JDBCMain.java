package com.bdsoft.bdceo.spring.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.object.BatchSqlUpdate;
import org.springframework.jdbc.object.SqlFunction;

import com.bdsoft.bdceo.spring.SpringUtils;
import com.bdsoft.bdceo.spring.jdbc.bean.Ruser;
import com.bdsoft.bdceo.spring.jdbc.pattern.templatemethod.JdbcTemplate;
import com.bdsoft.bdceo.spring.jdbc.pattern.templatemethod.StatementCallback;

public class JDBCMain {

	public static void main(String[] args) {
		// JDBCTemplate模拟实现
		// moni();

		// 数据访问测试：
		// testQuery();
		// testUpdate();
		// otherJdbcTemp();
		objectJdbc();

		System.out.println("exit");
	}

	public static void objectJdbc() {
//		DataSource ds = getDataSource();
//		System.out.println("MappingSqlQueryWithParameters-->");
//		RuserQueryWithParameters query = new RuserQueryWithParameters(ds);
//		List us = query.execute("%y%");
//		for (int i = 0, size = us.size(); i < size; i++) {
//			System.out.println(((Ruser) us.get(i)).getName());
//		}
//
//		System.out.println("MappingSqlQuery-->");
//		RuserMappingSqlQuery query2 = new RuserMappingSqlQuery(ds);
//		us = query2.execute("%y%");
//		for (int i = 0, size = us.size(); i < size; i++) {
//			System.out.println(((Ruser) us.get(i)).getName());
//		}
//
//		System.out.println("SqlFunction-->");
//		SqlFunction query3 = new SqlFunction(ds,
//				"select count(*) from dou_book");
//		query3.compile();
//		int result = query3.run();
//		System.out.println(result);
//		query3 = new SqlFunction(ds,
//				"select current_timestamp() from dou_book limit 1");
//		query3.compile();
//		Object res = query3.runGeneric();
//		System.out.println(res instanceof java.sql.Timestamp);
//
//		System.out.println("UpdatableSqlQuery-->");
//		RuserPwdUpdateableSqlQuery query4 = new RuserPwdUpdateableSqlQuery(ds,
//				"select * from t_ruser");
//		query4.execute();
//
//		System.out.println("SqlUpdate-->");
//		RuserPwdSqlUpdate query5 = new RuserPwdSqlUpdate(ds);
//		result = query5.updatePwdByName("kkkkkk", "%y%");
//		System.out.println(result);
//
//		System.out.println("BatchSqlUpdate-->");
//		BatchSqlUpdate query6 = new BatchSqlUpdate(ds,
//				"insert into t_ruser(name,pwd,role,eid) values(?,?,?,?)");
//		query6.declareParameter(new SqlParameter(Types.VARCHAR));
//		query6.declareParameter(new SqlParameter(Types.VARCHAR));
//		query6.declareParameter(new SqlParameter(Types.VARCHAR));
//		query6.declareParameter(new SqlParameter(Types.INTEGER));
//		query6.compile();
//		List<Ruser> rus = new ArrayList<Ruser>();
//		for (Ruser u : rus) {
//			query6.update(new Object[] { u.getName(), u.getPwd(), u.getRole(),
//					u.getEid() });
//		}
//		query6.flush();

	}

	public static void otherJdbcTemp() {
//		NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(
//				getDataSource());
//		MapSqlParameterSource paramMap = new MapSqlParameterSource();
//		paramMap.addValue("blen", "10");
//		int result = jdbc.queryForInt(
//				"select count(id) from dou_book where length(book_name)<:blen",
//				paramMap);
//		System.out.println(result);
//
//		Ruser userParam = new Ruser();
//		userParam.setEid(3);
//		BeanPropertySqlParameterSource paramBean = new BeanPropertySqlParameterSource(
//				userParam);
//		result = jdbc.queryForInt(
//				"select count(id) from t_ruser where eid=:eid", paramBean);
//		System.out.println(result);
	}

	public static void testUpdate() {
//		org.springframework.jdbc.core.JdbcTemplate jdbc = getJdbc(null);
//
//		// jdbc.update("insert into t_ruser(name,pwd,role,eid) values('coo','coo','coo',6)");
//
//		int affectRows = 0;
//		// affectRows = jdbc.update("update t_ruser set name=? where eid=6",
//		// new Object[] { new String("coo") });
//		// System.out.println("affectRows = " + affectRows);
//
//		affectRows = jdbc.update("delete from t_ruser where eid=10");
//		System.out.println("affectRows = " + affectRows);
//
//		// 基于回调的使用:PreparedStatementSetter
//		affectRows = jdbc.update("update t_ruser set name=? where eid=?",
//				new PreparedStatementSetter() {
//					public void setValues(PreparedStatement ps)
//							throws SQLException {
//						ps.setString(1, "XXOO");
//						ps.setInt(2, 444);
//					}
//				});
//
//		// 基于回调的使用:PreparedStatementCreator
//		final String sql = "update t_ruser set name=? where eid=?";
//		affectRows = jdbc.update(new PreparedStatementCreator() {
//			public PreparedStatement createPreparedStatement(Connection con)
//					throws SQLException {
//				PreparedStatement ps = con.prepareStatement(sql);
//				ps.setString(1, "XXOO");
//				ps.setInt(2, 444);
//				return ps;
//			}
//		});
//
//		// 不需要返回值的操作
//		// jdbc.execute("drop table xx");
//
//		// 批处理
//		final List list = new ArrayList();
//		jdbc.batchUpdate("insert into t_ruser values(?,?,?,?)",
//				new BatchPreparedStatementSetter() {
//					public void setValues(PreparedStatement ps, int i)
//							throws SQLException {
//						Ruser u = (Ruser) list.get(i);
//						ps.setString(1, u.getName());
//						ps.setString(2, u.getPwd());
//						ps.setString(3, u.getPwd());
//						ps.setInt(4, u.getEid());
//					}
//
//					public int getBatchSize() {
//						return list.size();
//					}
//				});
	}

	public static void testQuery() {
//		org.springframework.jdbc.core.JdbcTemplate jdbc = getJdbc(null);
//		// 设置自定义异常
//		// SQLErrorCodeSQLExceptionTranslator sqlEx = new
//		// ToySqlExceptionTranslator();
//		// jdbc.setExceptionTranslator(sqlEx);
//
//		String sql = "select count(*) from t_ruser";
//		int count = jdbc.queryForInt(sql);
//		System.out.println("count = " + count);
//
//		sql = "select * from t_ruser limit 1";
//		Map map = jdbc.queryForMap(sql);
//		System.out.println(map.toString());
//		System.out.println(map.get("role"));
//
//		sql = "select * from t_ruser";
//		List list = jdbc.queryForList(sql);
//		System.out.println(list.toString());
//
//		// 基于回调接口的查询
//		list = (List) jdbc.query(sql, new ResultSetExtractor() {
//			// 自实现数据解析获取组品并返回
//			public Object extractData(ResultSet rs) throws SQLException,
//					DataAccessException {
//				List list = new ArrayList();
//				while (rs.next()) {
//					Ruser u = new Ruser();
//					u.setId(rs.getInt(1));
//					u.setName(rs.getString(2));
//					list.add(u);
//				}
//				return list;
//			}
//		});
//		list = jdbc.query(sql, new RowMapper() {
//			public Object mapRow(ResultSet rs, int rowNumber)
//					throws SQLException {
//				Ruser u = new Ruser();
//				u.setId(rs.getInt(1));
//				u.setName(rs.getString(2));
//				return u;
//			}
//		});
//		final List flist = new ArrayList();
//		jdbc.query(sql, new RowCallbackHandler() {
//			public void processRow(ResultSet rs) throws SQLException {
//				Ruser u = new Ruser();
//				u.setId(rs.getInt(1));
//				u.setName(rs.getString(2));
//				flist.add(u);
//			}
//		});
//		// 自定义RowCallbackHandler，实现集合封装返回
//		GenericRowCallbackHandler handler = new GenericRowCallbackHandler();
//		jdbc.query(sql, handler);
//		list = handler.getList();
	}

	// 获取Spring的jdbcTem实例
	public static org.springframework.jdbc.core.JdbcTemplate getJdbc(
			BasicDataSource ds) {
		// 基于手动获取数据源方式
		if (ds != null) {
			return new org.springframework.jdbc.core.JdbcTemplate(ds);
		}
		// 基于IOC容器的配置获取
		ApplicationContext app = SpringUtils.appIocStart();
		return (org.springframework.jdbc.core.JdbcTemplate) app
				.getBean("jdbcTemplate");
	}

	// 基于JakartaCommons DBCP数据源
	public static BasicDataSource getDataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://127.0.0.1:3306/bdceo?useUnicode=true&amp;characterEncoding=utf-8");
		ds.setUsername("root");
		ds.setPassword("root");
		return ds;
	}

	// 模拟Spring的JDBCTemplate实现
	public static void moni() {
		JdbcTemplate jdbc = new JdbcTemplate();
		final String sql = "update ...";
		// 内部类实现
		StatementCallback callback = new StatementCallback() {
			@Override
			public Object doWithStatement(Statement stmt) {
				int flag = -1;
				try {
					flag = stmt.executeUpdate(sql);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return new Integer(flag);
			}
		};
		jdbc.execute(callback);
	}
}
