package com.bdsoft.bdceo.spring.jdbc;

public class RuserMappingSqlQuery{}

//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import javax.sql.DataSource;
//
//import org.springframework.jdbc.core.SqlParameter;
//import org.springframework.jdbc.object.MappingSqlQuery;
//
//import com.bdsoft.bdceo.spring.jdbc.bean.Ruser;
//
//public class RuserMappingSqlQuery extends MappingSqlQuery {
//	private static final String QUERY_SQL = "select * from t_ruser where name like ?";
//
//	public RuserMappingSqlQuery(DataSource ds) {
//		super(ds, QUERY_SQL);
//		declareParameter(new SqlParameter(java.sql.Types.VARBINARY));
//		compile();
//	}
//
//	@Override
//	protected Object mapRow(ResultSet rs, int row) throws SQLException {
//		Ruser u = new Ruser();
//		u.setId(rs.getInt(1));
//		u.setName(rs.getString(2));
//		return u;
//	}
//
//}
