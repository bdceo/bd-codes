/**
 * PassObject.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.operators;

/**
 * 3.4.1--方法调用中的别名问题
 * 
 * @author bdceo
 * @date 2017-1-4 下午4:03:35
 * @version V1.0
 */
public class PassObject {

	/**
	 * 修改了方法之外的引用内容
	 */
	public static void main(String[] args) {
		Letter l = new Letter();
		l.c = 'f';
		System.out.println(l.c);
		change(l);
		System.out.println(l.c);

		System.out.println(0xff);

		System.out.println(4e2); // 4 * (10 * 10) = 4*10的2次方
		System.out.println(4e-2);// 0.04
		
		Integer ii = Integer.valueOf(100);
		Integer jj = Integer.valueOf(100);
		
		System.out.println(ii == jj);
		System.out.println(ii.equals(jj));
	}

	static void change(Letter o) {
		o.c = 'd';
	}

}

class Letter {
	char c;
}
