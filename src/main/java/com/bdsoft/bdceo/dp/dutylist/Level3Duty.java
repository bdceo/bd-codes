/**
 * Level1Duty.java
 * com.bdsoft.bdceo.dp.dutylist
 * Copyright (c) 2016, 北京微课创景教育科技有限公司版权所有.
*/

package com.bdsoft.bdceo.dp.dutylist;

import com.google.common.collect.Lists;

public class Level3Duty extends AbsDuty implements IDuty {

	public Level3Duty() {
		this("周日");
	}

	public Level3Duty(String name) {
		this.name = name;
		this.myDuty = Lists.newArrayList(5, 6, 7);
	}

	@Override
	public String next(Integer level) {
		if (myDuty.contains(level)) {
			return info();
		} else {
			return outOfDuty();
		}
	}

}
