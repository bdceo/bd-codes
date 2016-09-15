/**
 * Customer.java
 * com.bdsoft.bdceo.refactor.movie
 * Copyright (c) 2014, 北京微课创景教育科技有限公司版权所有.
*/
package com.bdsoft.bdceo.refactor.movie;

import java.util.Enumeration;
import java.util.Vector;

/**
 * 客户的租凭清单
 * @author	丁辰叶
 * @date	2014-11-5
 */
public class Customer {

	private String _name;
	private Vector _rentals = new Vector(); // 租的电影

	public Customer(String name) {
		_name = name;
	}

	public void addRental(Rental arg) {
		_rentals.add(arg);
	}

	/**
	 * 生成租凭详单
	 * @return
	 */
	public String statement() {
		Enumeration rentals = _rentals.elements();
		String result = "Rental Record for " + getName() + "\n";
		while (rentals.hasMoreElements()) {
			Rental each = (Rental) rentals.nextElement();
			result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(each.getCharge()) + "\n";
		}
		// 添加页脚
		result += "Amount owed is " + String.valueOf(getTotalCharge()) + "\n";
		result += "You earned " + String.valueOf(getTotalFrequentRenterPoints()) + " frequent renter points";

		return result;
	}

	/**
	 * 生成租凭详单-HTML版
	 * @return
	 */
	public String htmlStatement() {
		Enumeration rentals = _rentals.elements();
		String result = "<H1>Rental Record for <EM>" + getName() + "</EM></H1><P>\n";
		while (rentals.hasMoreElements()) {
			Rental each = (Rental) rentals.nextElement();
			result += each.getMovie().getTitle() + String.valueOf(each.getCharge()) + "<BR>\n";
		}
		// 添加页脚
		result += "<P>Amount owe <EM> " + String.valueOf(getTotalCharge()) + "</EM><P>\n";
		result += "On this rental you earned <EM>" + String.valueOf(getTotalFrequentRenterPoints())
				+ "</EM> frequent renter points<P>";

		return result;
	}

	/**
	 * 计算总消费
	 * @return
	 */
	private double getTotalCharge() {
		double result = 0;
		Enumeration rentals = _rentals.elements();
		while (rentals.hasMoreElements()) {
			Rental each = (Rental) rentals.nextElement();
			result += each.getCharge();
		}
		return result;
	}

	/**
	 * 计算总积分
	 * @return
	 */
	private int getTotalFrequentRenterPoints() {
		int result = 0;
		Enumeration rentals = _rentals.elements();
		while (rentals.hasMoreElements()) {
			Rental each = (Rental) rentals.nextElement();
			result += each.getFrequentRenterPoints();
		}
		return result;
	}

	public String getName() {
		return _name;
	}
}
