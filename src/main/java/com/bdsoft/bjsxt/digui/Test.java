package com.bdsoft.bjsxt.digui;

public class Test {

	public static void main(String[] args) {
		System.out.println(Test.jieCheng(10));
		System.out.println(Test.fibonaaic(10));
		System.out.println(Test.cal(9));
	}

	public static long jieCheng(int n) {
		if (n == 1) {
			return 1;
		} else {
			return n * jieCheng(n - 1);
		}
	}

	public static long fibonaaic(int n) {
		if (n == 1 || n == 2) {
			return 1;
		} else {
			return fibonaaic(n - 1) + fibonaaic(n - 2);
		}
	}

	public static long cal(int index) {
		// 1,1,2,3,5,8,13,21,34,55...
		int first = 1;
		int second = 1;
		int third = 0;
		if (index < 1) {
			System.err.println("非法参数！");
			return -1;
		}
		for (int i = 1; i <= index; i++) {
			if (i == 1 || i == 2) {
				third = 1;
			} else if (i == 3) {
				third = first + second;
			} else {
				first = second;
				second = third;
				third = third + first;
			}
		}
		return third;
	}
}
