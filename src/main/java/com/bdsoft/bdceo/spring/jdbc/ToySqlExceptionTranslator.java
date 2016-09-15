package com.bdsoft.bdceo.spring.jdbc;

public class ToySqlExceptionTranslator{}

//import java.sql.SQLException;
//
//import org.springframework.dao.DataAccessException;
//import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
//
//public class ToySqlExceptionTranslator extends
//		SQLErrorCodeSQLExceptionTranslator {
//
//	@Override
//	protected DataAccessException customTranslate(String task, String sql,
//			SQLException sqlEx) {
//		if (sqlEx.getErrorCode() == 4444) {
//			String msg = "bdceo exception";
//			
//			return new UnexpectedDataAccessException(msg, sqlEx);
//		}
//		return null;
//	}
//
//}
