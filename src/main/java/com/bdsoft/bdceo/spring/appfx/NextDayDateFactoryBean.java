package com.bdsoft.bdceo.spring.appfx;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.FactoryBean;

public class NextDayDateFactoryBean implements FactoryBean {

	@Override
	public Object getObject() throws Exception {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DATE, 1);
		return now.getTime();
	}

	@Override
	public Class getObjectType() {
		return Date.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}
