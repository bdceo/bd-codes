/**
 * Rental.java
 * com.bdsoft.bdceo.refactor.movie
 * Copyright (c) 2014, 北京微课创景教育科技有限公司版权所有.
*/
package com.bdsoft.bdceo.refactor.movie;

/**
 * 一个租凭项
 * @author	丁辰叶
 * @date	2014-11-5
 */
public class Rental {

	private Movie _movie;//电影
	private int _daysRented; // 租天数

	public Rental(Movie movie, int daysRented) {
		this._movie = movie;
		this._daysRented = daysRented;
	}

	public Movie getMovie() {
		return _movie;
	}

	public int getDaysRented() {
		return _daysRented;
	}

	/**
	 * 计算消费金额
	 * @return
	 */
	public double getCharge() {
		return _movie.getCharge(_daysRented);
	}

	/**
	 * 计算积分
	 * @return
	 */
	public int getFrequentRenterPoints() {
		return _movie.getFrequentRenterPoints(_daysRented);
	}

}
