package com.bdsoft.bdceo.spring.jdbc.pattern.templatemethod;

import java.sql.Statement;

public abstract interface StatementCallback {

	public Object doWithStatement(Statement stmt);
}
