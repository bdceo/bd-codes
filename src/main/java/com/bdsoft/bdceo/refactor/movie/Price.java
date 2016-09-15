/**
 * Price.java
 * com.bdsoft.bdceo.refactor.movie
 * Copyright (c) 2014, 北京微课创景教育科技有限公司版权所有.
*/
package com.bdsoft.bdceo.refactor.movie;

/**
 * 价格计算抽象定义
 * @author	丁辰叶
 * @date	2014-11-5
 */
public abstract class Price {

	/**
	 * 获取价格类型
	 * @return
	 */
	abstract int getPriceCode();

	/**
	 * 获取价格
	 * @param daysRented 租天数
	 * @return
	 */
	abstract double getCharge(int daysRented);

	/**
	 * 父类，定义默认的计算积分规则
	 * 
	 * @param daysRented 租天数
	 * @return
	 */
	int getFrequentRenterPoints(int daysRented) {
		return 1;
	}
}
