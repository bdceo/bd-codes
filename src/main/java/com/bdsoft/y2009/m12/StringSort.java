package com.bdsoft.y2009.m12;

public class StringSort {

	public static void main(String[] args) {
		int a = (int) 'a';
		int A = (int) 'A';
		System.out.println("A��ASCII�룺" + A);
		System.out.println("a��ASCII�룺" + a);

		String[] str = { "dad", "bood", "bada", "Admin", "Good", "aate", "cc",
				"Ko", "Beta", "Could" };
		// ���㷨ֻ�Ƚ��ַ������ĸ
		sort(str);
	}

	public static void sort(String str[]) {
		for (int i = 0; i < str.length; i++) {
			for (int j = 0; j < str.length - i - 1; j++) {
				if (compare(str[j], str[j + 1]) > 0) {
					String temp = str[j];
					str[j] = str[j + 1];
					str[j + 1] = temp;
				}
			}
		}
		for (int i = 0; i < str.length; i++) {
			System.out.print(str[i] + "  ");
		}
	}

	public static int compare(String st1, String st2) {
		int x = 0;
		for (int i = 0, j = 0; (i < st1.length()) && (j < st2.length()); i++, j++) {
			int s1 = (int) st1.charAt(i);// ��ȡ�ַ�ͬ�����ַ��ASCII��
			int s2 = (int) st2.charAt(j);
			if (s1 >= 97) {
				s1 -= 32;
			}
			if (s2 >= 97) {
				s2 -= 32;
			}
			if (s1 == s2) {// ��дСд��ͬ,��һ���ж�
				if ((int) st1.charAt(i) > (int) st2.charAt(j)) {
					x = 1;
					break;
				} else {
					x = 0;
					break;
				}
			} else if (s1 > s2) {
				x = 1;
				break;
			} else {
				x = 0;
				break;
			}
		}
		return x;
	}
}
