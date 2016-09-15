/**
 * Movie.java
 * com.bdsoft.bdceo.refactor.movie
 * Copyright (c) 2014, 北京微课创景教育科技有限公司版权所有.
*/
package com.bdsoft.bdceo.refactor.movie;

/**
 * 电影实体
 * @author	丁辰叶
 * @date	2014-11-5
 */
public class Movie {

	public static final int CHILDRENS = 2;// 儿童片
	public static final int REGULAR = 0;// 普通片
	public static final int NEE_RELEASE = 1; // 新片

	private String _title;
	private Price _price;

	public Movie(String title, int priceCode) {
		this._title = title;
		setPriceCode(priceCode);
	}

	public String getTitle() {
		return _title;
	}

	/**
	 * 获取价格类型
	 * @return
	 */
	public int getPriceCode() {
		return _price.getPriceCode();
	}

	/**
	 * 设置价格类型
	 * @param priceCode
	 */
	public void setPriceCode(int priceCode) {
		switch (priceCode) {
		case REGULAR:
			_price = new RegularPrice();
			break;
		case NEE_RELEASE:
			_price = new NewReleasePrice();
			break;
		case CHILDRENS:
			_price = new ChildrensPrice();
			break;
		default:
			throw new IllegalAccessError("Incorrect Price Code");
		}
	}

	/**
	 * 计算租凭价格 
	 * 
	 * @param daysRented 租天数
	 * @return
	 */
	public double getCharge(int daysRented) {
		return _price.getCharge(daysRented);
	}

	/**
	 * 计算积分
	 * @return
	 */
	public int getFrequentRenterPoints(int daysRentaled) {
		if ((getPriceCode() == Movie.NEE_RELEASE) && daysRentaled > 1) {
			return 2;
		}
		return 1;
	}

}
