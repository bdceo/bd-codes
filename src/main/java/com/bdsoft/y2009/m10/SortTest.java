package com.bdsoft.y2009.m10;

public class SortTest {

	public static void main(String[] args) {
		mpTest();// ð������
		efTest(66);// ���ֲ���
	}

	public static void efTest(int num) {
		System.out.println("\n--------���ֲ���-------");
		int[] test = { 12, 20, 32, 60, 66, 88 };
		int len = test.length;
		int start = 0;
		int end = len - 1;
		int mid = (start + end) / 2;
		while (start <= end) {
			if (test[mid] > num) {
				end = mid;
				mid = (end + start) / 2;
			} else if (test[mid] < num) {
				start = mid;
				mid = (end + start) / 2;
			} else if (test[mid] == num) {
				System.out.println(num + "������λ���ǣ�" + mid);
				break;
			}
		}
	}

	public static void mpTest() {
		System.out.println("--------ð������-------");
		int[] test = { 52, 20, 92, 66, 45, 38 };
		int len = test.length;
		for (int i = 0; i < len; i++) {
			int flag = 0;
			for (int j = 0; j < len - i - 1; j++) {
				if (test[j] < test[j + 1]) {
					int inner = test[j];
					test[j] = test[j + 1];
					test[j + 1] = inner;
					flag = 1;
				}
			}
			if (flag == 0) {
				System.out.println("���ѭ����ִ����" + i + "�Σ�");
				break;
			}
		}
		System.out.println("*******************");
		for (int i = 0; i < len; i++) {
			System.out.print(test[i]);
			if (i != test.length - 1) {
				System.out.print(" > ");
			}
		}
	}
}
