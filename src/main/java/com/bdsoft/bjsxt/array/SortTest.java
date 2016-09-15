package com.bdsoft.bjsxt.array;

public class SortTest {

	public static void main(String[] args) {
		// 使用数组之前必须初始化...
		// 动态初始化
		int[] its = new int[3];
		its[0] = 1;
		// 静态初始化
		int[] itd = { 1, 3, 4, 8, 9, 7, 5, 2, 6, 0 };
		print(itd);
		sort(itd);
		sort_2(itd);
	}

	public static void print(int[] itd) {
		for (int i = 0; i < itd.length; i++) {
			System.out.print(itd[i] + " ");
		}
		System.out.println();
	}

	public static void sort(int[] itd) {
		for (int i = 0; i < itd.length; i++) {
			int flag = 0;
			for (int j = 0; j < itd.length - 1 - i; j++) {
				if (itd[j] > itd[j + 1]) {
					int temp = itd[j];
					itd[j] = itd[j + 1];
					itd[j + 1] = temp;
					flag = 1;
				}
			}
			if (flag == 0) {
				break;
			}
		}
		print(itd);
	}

	public static void sort_2(int[] a) {
		int k, temp;
		for (int i = 0; i < a.length; i++) {
			k = i;
			for (int j = k + 1; j < a.length; j++) {
				if (a[j] < a[k]) {
					k = j;
				}
			}
			if (k != i) {
				temp = a[i];
				a[i] = a[k];
				a[k] = temp;
			}
		}
		print(a);
	}
}