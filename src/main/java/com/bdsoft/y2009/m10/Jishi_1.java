package com.bdsoft.y2009.m10;

import java.util.Scanner;

public class Jishi_1 {

	/*
	 * ������:
	 * 
	 * 1-26:A-Z�� 27-52:AA-AZ��53-78:BA-BZ
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int i, m, s, model = 0;
		char mc, sc;
		do {
			System.out.print("\n\n\n������ݣ�");
			i = input.nextInt();// 27
			System.out.print("������");
			m = i % 26;// ��ģ
			s = i / 26;// ����
			if (s > 0) {
				if (m == 0) {
					if (s == 1) {
					} else {
						sc = (char) ('A' + s - 2);
						System.out.print(sc);
					}
				} else {
					sc = (char) ('A' + s - 1);
					System.out.print(sc);
				}
			}
			if (m == 0) {
				System.out.print("Z");
			} else {
				mc = (char) ('A' + m - 1);
				System.out.print(mc);
			}
			System.out.print("\n\n�Ƿ����0-����/1-�˳�:");
			model = input.nextInt();
		} while (model == 0);
	}
}
