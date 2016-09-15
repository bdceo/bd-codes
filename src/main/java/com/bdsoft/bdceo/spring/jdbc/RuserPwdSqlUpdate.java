package com.bdsoft.bdceo.spring.jdbc;

public class RuserPwdSqlUpdate{}

//import java.sql.Types;
//
//import javax.sql.DataSource;
//
//import org.springframework.jdbc.core.SqlParameter;
//import org.springframework.jdbc.object.SqlUpdate;
//
//public class RuserPwdSqlUpdate extends SqlUpdate {
//
//	private static final String UPDATE_SQL = "update t_ruser set pwd=? where name like ?";
//
//	public RuserPwdSqlUpdate(DataSource ds) {
//		super(ds, UPDATE_SQL);
//		declareParameter(new SqlParameter(Types.VARCHAR));
//		declareParameter(new SqlParameter(Types.VARCHAR));
//		compile();
//	}
//
//	public int updatePwdByName(String pwd, String key) {
//		return update(new Object[] { pwd, key });
//	}
//}
