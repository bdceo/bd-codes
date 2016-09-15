/**
 * NewReleasePrice.java
 * com.bdsoft.bdceo.refactor.movie
 * Copyright (c) 2014, 北京微课创景教育科技有限公司版权所有.
*/
package com.bdsoft.bdceo.refactor.movie;

/**
 * 
 * @author	丁辰叶
 * @date	2014-11-5
 */
public class NewReleasePrice extends Price {

	@Override
	int getPriceCode() {
		return Movie.NEE_RELEASE;
	}

	@Override
	double getCharge(int daysRented) {
		return daysRented * 3;
	}

	/**
	 * 子类特殊化
	 * 
	 * @param daysRented 租天数
	 * @return
	 */
	int getFrequentRenterPoints(int daysRentaled) {
		return (daysRentaled > 1) ? 2 : 1;
	}
}
