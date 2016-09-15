package com.bdsoft.bdceo.spring.appfx;

import java.util.Date;

public class BarImpl implements IBar {

	private Date nextDay;

	public void say() {
		if (this.nextDay == null) {
			nextDay = new Date();
		}
		System.out.println(nextDay.toLocaleString());
	}

	public void setNextDay(Date nextDay) {
		this.nextDay = nextDay;
	}

	public Date getNextDay() {
		return nextDay;
	}

}
