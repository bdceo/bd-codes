package com.bdsoft.bdceo.spring.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;

import com.bdsoft.bdceo.spring.jdbc.bean.Ruser;

public class GenericRowCallbackHandler implements RowCallbackHandler {

	private List list = new ArrayList();

	@Override
	public void processRow(ResultSet rs) throws SQLException {
		Ruser u = new Ruser();
		u.setId(rs.getInt(1));
		u.setName(rs.getString(2));
		list.add(u);
	}

	public List getList() {
		return list;
	}

}
