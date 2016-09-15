/**
 * Level1Duty.java
 * com.bdsoft.bdceo.dp.dutylist
 * Copyright (c) 2016, 北京微课创景教育科技有限公司版权所有.
*/

package com.bdsoft.bdceo.dp.dutylist;

import com.google.common.collect.Lists;

public class Level1Duty extends AbsDuty implements IDuty {

	private IDuty nextDuty = new Level2Duty();

	public Level1Duty() {
		this("周二");
	}

	public Level1Duty(String name) {
		this.name = name;
		this.myDuty = Lists.newArrayList(1, 2);
	}

	@Override
	public String next(Integer level) {
		if (myDuty.contains(level)) {
			return info();
		} else {
			return nextDuty.next(level);
		}
	}

}
