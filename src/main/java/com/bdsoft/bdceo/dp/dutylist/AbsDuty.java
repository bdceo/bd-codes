package com.bdsoft.bdceo.dp.dutylist;

import java.util.List;

public abstract class AbsDuty implements IDuty {

	protected String name;
	protected List<Integer> myDuty;

	public String info() {
		return new StringBuffer("最近一期开奖是：").append(getName()).toString();
	}

	public String outOfDuty() {
		return new StringBuffer("开奖日期未知").toString();
	}

	public String getName() {
		return this.name;
	}
}
