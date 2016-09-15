package com.bdsoft.y2009.m10;

import java.util.Arrays;

public class Jiaoji {
	/**
	 * �ҳ�}�����򡱼��ϵĽ���
	 * 
	 * ǰ������������������
	 */
	public static void main(String args[]) {
		int[] a = { 2, 3, 4, 4, 4, 4, 7, 8, 8, 8, 8, 9, 100, 130, 150, 160 };
		int[] b = { 4, 6, 7, 7, 7, 7, 8, 8, 9, 10, 100, 130, 130, 140, 150 };
		int[] c = intersect(a, b);
		// ���ַ���ʽ��������е�����
		System.out.println(Arrays.toString(c));
	}

	public static int[] intersect(int[] a, int[] b) {
		// �ж�}��ϵ���β��ϵ
		if (a[0] > b[b.length - 1] || b[0] > a[a.length - 1]) {
			return new int[0];
		}
		int[] intersection = new int[Math.max(a.length, b.length)];
		int offset = 0;
		for (int i = 0, j = i; i < a.length && j < b.length; i++) {
			while (a[i] > b[j]) {
				j++;
			}
			if (a[i] == b[j]) {
				intersection[offset] = b[j];
				offset++;
				j++;
			}
			while (i < (a.length - 1) && a[i] == a[i + 1]) {
				i++;
			}
		}
		if (intersection.length == offset) {
			return intersection;
		}
		int[] duplicate = new int[offset];
		System.arraycopy(intersection, 0, duplicate, 0, offset);
		return duplicate;
	}
}