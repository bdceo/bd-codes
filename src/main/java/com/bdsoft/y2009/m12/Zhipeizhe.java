package com.bdsoft.y2009.m12;

public class Zhipeizhe {

	public static void main(String[] args) {
		/*
		 * һ�����飬��֧���ߡ����������г���Ƶ�ʳ���һ������� ����[3,4,3,2,-1,3,3,3]��ֵ��3�����ֹ�5�Σ�5����8����0.5
		 * ������ֵ��3����һ��֧���ߣ� ������������е�֧���߳����������±�[0,2,4,6,7]
		 * дһ�����ڸ�������������ҳ�֧�������ڵ�����һ�������±꣬���һ��������û��֧���߷���-1��
		 */
		int[] it = { 4, 2, 4, 3, 5, 4, 2, 4, 4, 1 ,2,4};
		System.out.print("���±�ţ�");
		for (int i = 0; i < it.length; i++) {
			System.out.print(it[i] + " ");
		}
		int times = 0;// ���ִ���
		int num = it[0];
		for (int i = 0; i < it.length - 5; i++) {
			for (int j = 0; j < it.length; j++) {
				if (it[i] == it[j]) {
					times++;
				}
			}
			if (times >= 5) {
				num = it[i];
				break;
			} else {
				times = 0;
				return;
			}
		}
		if (times < 5) {
			System.out.print("\nû��֧���ߣ�");
		} else {
			System.out.print("\n֧�����У�");
			for (int i = 0; i < it.length; i++) {
				if (it[i] == num) {
					System.out.print(i + " ");
				}
			}
		}
	}

}
