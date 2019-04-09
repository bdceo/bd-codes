package com.bdsoft.bdceo.dp.dutylist;

import com.google.common.collect.Lists;

public class Level3Duty extends AbsDuty {

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
