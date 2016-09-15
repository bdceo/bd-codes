package com.bdsoft.y2011.m04;

import java.math.BigDecimal;

public class IntBitCalc {

	/**
	 * java中关于整数类型的位运算
	 * 
	 * 例子代码参见博客地址： http://blog.csdn.net/gdweijin/archive/2010/08/02/5782642.aspx
	 */
	public static void main(String[] args) {
		byte b = 1;
		System.out.println(b >> 7 & 0x01);//0
		
		pt("" + 0xff); // 255
		pt("" + 0x2);// 2

		jo(2);
		jo(-2);

		pt("2.00 - 1.10=" + (2.00 - 1.10));
		pt("200 - 110=" + (200 - 110));
		pt("BigDecimal(2.0-1.10)="
				+ (new BigDecimal("2.0").subtract(new BigDecimal("1.10"))));

		long microsPerDay = 24 * 60 * 60 * 1000 * 1000;
		pt("" + microsPerDay);
		long microsPerDay2 = 24L * 60 * 60 * 1000 * 1000;
		pt("" + microsPerDay2);

	}

	public static void jo(int i) {
		if (i % 2 == 0) {
			pt(i + "是偶数");
		} else {
			pt(i + "不是偶数");
		}
		if (i % 2 == 1) {
			pt(i + "是奇数");
		} else {
			pt(i + "不是奇数");
		}
		if (i % 2 != 0) {
			pt(i + "是奇数");
		} else {
			pt(i + "不是奇数");
		}
		if ((i & 1) != 0) {
			pt(i + "是奇数");
		} else {
			pt(i + "不是奇数");
		}

	}

	public static void pt(String sr) {
		System.out.println(sr);
	}

}
