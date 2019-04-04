package com.bdsoft.y2016;

public class LotteryGen {

	public static void main(String[] args) {

		// System.out.println(Runtime.getRuntime().availableProcessors());

		calc(130);

		calc(521);
		

	}

	public static void calc(int a) {
		System.out.println();
		int b = 33; // 红球数
		int c = 16; // 篮球数

		int r1 = a / b;
		int b1 = a / c;
		System.out.println("相除：" + r1 + "，" + b1);

		int r2 = a % b;
		int b2 = a % c;
		System.out.println("取余：" + r2 + "，" + b2);
	}

}
