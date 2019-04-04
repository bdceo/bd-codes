/**
 * TestMutilParam.java
 * com.bdsoft.y2014.m11
 * Copyright (c) 2014, 北京微课创景教育科技有限公司版权所有.
*/
package com.bdsoft.y2014;

/**
 * 可变长参数，方法重载测试
 * 参考：http://www.cnblogs.com/lanxuezaipiao/p/3190673.html
 * 
 * @author	丁辰叶
 * @date	2014-11-21
 */
public class TestMutilParam {

	/**
	 * 避免带有可变长参数的方法重载
	 * 别让null值和空值威胁到变长方法
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		m1(1);

		m1(1, 2); 
	}

	/**
	 *  在调用方法的时候，如果能够和固定参数的方法匹配，也能够与可变长参数的方法匹配，则选择固定参数的方法
	 */
	public static void m1(int i) {
		System.out.println("m1");
		System.out.println(i);
	}

	/**
	 *  如果要调用的方法可以和两个可变参数匹配，编译失败
	 */
	public static void m1(int i, int... ints) {
		System.out.println("m2");
		System.out.println(i);
		for (int ii : ints) {
			System.out.print(ii);
		}
	}

	/**
	 * 该方法可与方法1重载
	 * 
	 * 不可与方法2重载
	 * 
	 * 每个方法只能有一个可变长参数，并且必须是该方法的最后一个参数
	 */
	//	public static void m1(int ... ints){
	//		System.out.println("m3");
	//		for (int ii : ints) {
	//			System.out.print(ii);
	//		}
	//	}

}
