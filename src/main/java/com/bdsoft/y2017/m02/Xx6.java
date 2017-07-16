/**
 * Xx6.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.y2017.m02;

/**
 * Xx6
 * 
 * @author bdceo
 * @date 2017-2-6 下午9:54:50
 * @version V1.0
 */
public class Xx6 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int sum = 176; // 标本总数
		int pad = 13; // 展板总数
		int big = 20; // 大展板
		int sml = 8; // 小展板

		int max = sum / (big + sml);
		int bigMax = sum / big;
		
		for (int i = max; i < bigMax; i++) {
			int tmp = big * i + sml * (pad - i);
			if (tmp == sum) {
				System.out.println(String.format("大展板%d块，小展板%d块", i, (pad - i)));
				break;
			}
		}
	}

}
