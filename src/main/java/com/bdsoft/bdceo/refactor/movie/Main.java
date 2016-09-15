/**
 * Main.java
 * com.bdsoft.bdceo.refactor.movie
 * Copyright (c) 2014, 北京微课创景教育科技有限公司版权所有.
*/
package com.bdsoft.bdceo.refactor.movie;

/**
 * 测试入口
 * @author	丁辰叶
 * @date	2014-11-5
 */
public class Main {

	public static void main(String[] args) {
		Movie m1 = new Movie("喜洋洋", Movie.CHILDRENS);
		Movie m2 = new Movie("我是谁", Movie.REGULAR);
		Movie m3 = new Movie("心花怒放", Movie.NEE_RELEASE);
		
		Rental r1 = new Rental(m1, 4);
		Rental r2 = new Rental(m2, 3);
		Rental r3 = new Rental(m3, 5);
		
		Customer c1 = new Customer("bdceo");
		c1.addRental(r1);
		c1.addRental(r2);
		c1.addRental(r3);
		
		String result = c1.statement();
		System.out.println(result);;
	}

}
