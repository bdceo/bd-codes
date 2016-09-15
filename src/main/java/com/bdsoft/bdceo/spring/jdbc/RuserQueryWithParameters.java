package com.bdsoft.bdceo.spring.jdbc;
   
public class RuserQueryWithParameters{}

//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Map;
//
//import javax.sql.DataSource;
//
//import org.springframework.jdbc.core.SqlParameter;
//import org.springframework.jdbc.object.MappingSqlQueryWithParameters;
//
//import com.bdsoft.bdceo.spring.jdbc.bean.Ruser;
// 
//public class RuserQueryWithParameters extends MappingSqlQueryWithParameters {
//
//	private static final String QUERY_SQL = "select * from t_ruser where name like ?";
//
//	public RuserQueryWithParameters(DataSource ds) { 
//		super(ds, QUERY_SQL);
//		declareParameter(new SqlParameter(java.sql.Types.VARBINARY));
//		compile();
//	}
//
//	@Override
//	protected Object mapRow(ResultSet rs, int row, Object[] params, Map context)
//			throws SQLException {
//		Ruser u = new Ruser();
//		u.setId(rs.getInt(1));
//		u.setName(rs.getString(2));
//		return u;
//	}
//
//}
