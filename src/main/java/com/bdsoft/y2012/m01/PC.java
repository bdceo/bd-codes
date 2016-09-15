package com.bdsoft.y2012.m01;

import java.math.BigDecimal;

/**
 * 排列组合 - 测试
 * 
 * http://baike.baidu.com/view/738955.htm
 */
public class PC {

	public static void main(String[] args) {
		P p = new P(33, 6);
		System.out.println(p);

		C c = new C(16, 1);
		System.out.println(c);

		C c1 = new C(33, 6);
		System.out.println(c1);

		System.out.println("\n双色球：33红+16蓝 -> 6红+1蓝为1注，2元");
		System.out.println("选8红+3蓝，多少钱？");
		C cred = new C(8, 6);
		C cblu = new C(3, 1);
		System.out.println(cred + "\n" + cblu);
		// int money = cred.getC().intValue() * cblu.getC().intValue() * 2;
		int money = cred.getC().multiply(cblu.getC())
				.multiply(new BigDecimal(2)).intValue();
		System.out.println("共计：" + money);

	}

	/**
	 * 计算阶乘
	 * 
	 * @param n
	 * @return
	 */
	public static BigDecimal Jie(long n) {
		if (n <= 1) {
			return new BigDecimal(1);
		}
		return new BigDecimal(n).multiply(Jie(n - 1));
	}
}
