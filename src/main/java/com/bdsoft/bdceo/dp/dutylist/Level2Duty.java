/**
 * Level1Duty.java
 * com.bdsoft.bdceo.dp.dutylist
 * Copyright (c) 2016, 北京微课创景教育科技有限公司版权所有.
*/

package com.bdsoft.bdceo.dp.dutylist;

import com.google.common.collect.Lists;

public class Level2Duty extends AbsDuty implements IDuty {

	private IDuty nextDuty = new Level3Duty();

	public Level2Duty() {
		this("周四");
	}

	public Level2Duty(String name) {
		this.name = name;
		this.myDuty = Lists.newArrayList(3, 4);
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
