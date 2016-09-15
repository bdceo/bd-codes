package com.bdsoft.y2009;

public class TestForLoop {

	public static void main(String[] args) {
		// 计算素数
		sushu();
		
		// 测试打印效果
		printView(10);

		// for循环执行逻辑
		int i = 0;
		for (pt('A'); pt('B') && i < 2; pt('C')) {
			i++;
			pt('D');
			System.out.println();
		}
	}

	public static void sushu() {
		int[] su = new int[70];
		int size = 0;
		boolean flag;
		for (int i = 100; i < 500; i++) {
			flag = true;
			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					flag = false;
					break;
				}
			}
			if (flag) {
				su[size] = i;
				size++;
			}
		}
		for (int i = 0; i < su.length; i++) {
			System.out.print(su[i] + " ");
			if (i % 9 == 0)
				System.out.println();
		}
		System.out.println("\n\n100-500之间素数个数：" + size);
	}

	public static void printView(int len) {
		for (int i = 1; i <= len; i++) {
			int k = 0;
			for (; k < i; k++) {
				System.out.print(k);
			}
			for (; k < len; k++) {
				System.out.print(i - 1);
			}
			System.out.println("");
		}
	}

	public static boolean pt(char ch) {
		System.out.print(ch);
		return true;
	}
}
