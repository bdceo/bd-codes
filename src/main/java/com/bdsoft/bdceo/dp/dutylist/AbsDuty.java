/**
 * AbsDuty.java
 * com.bdsoft.bdceo.dp.dutylist
 * Copyright (c) 2016, 北京微课创景教育科技有限公司版权所有.
*/

package com.bdsoft.bdceo.dp.dutylist;

import java.util.List;

/**
 * 
 * <p>
 *
 * @author   丁辰叶
 * @date	 2016-7-25
 * @version  1.0.0
 */

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
