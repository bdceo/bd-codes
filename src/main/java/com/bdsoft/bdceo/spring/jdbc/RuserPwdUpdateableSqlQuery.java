package com.bdsoft.bdceo.spring.jdbc;

public class RuserPwdUpdateableSqlQuery {}

//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Map;
//
//import javax.sql.DataSource;
//
//import org.springframework.jdbc.object.UpdatableSqlQuery;
//
//public class RuserPwdUpdateableSqlQuery extends UpdatableSqlQuery {
//
//	public RuserPwdUpdateableSqlQuery(DataSource ds, String sql) {
//		super(ds, sql);
//		compile();
//	}
//
//	@Override
//	protected Object updateRow(ResultSet rs, int row, Map context)
//			throws SQLException {
//		String pwd = rs.getString(3);
//		rs.updateString(3, "jjjjjj");
//		return null;
//	}
//
//}
