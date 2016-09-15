package com.bdsoft.bjsxt.util;

import java.util.Date;

public class StringTest2 {

	public static void main(String[] args) {
		String str = "I love you!";
		System.out.println(str);

		System.out.println(str.valueOf(true));
		System.out.println(str.valueOf('s'));
		System.out.println(str.valueOf(12));
		System.out.println(str.valueOf(new Date()));

		String[] sts = str.split(" ");
		for (int i = 0; i < sts.length; i++) {
			System.out.println(sts[i]);
		}

		String lS = "abcdefghigklmnopqrstuvwxyz";
		String uS = "ABCDEFGHIGKLMNOPQRSTUVWXYZ";
		String nS = "0123456789";
		String test = "aaAA22%@";
		char[] cs = test.toCharArray();
		int it = 0;
		int lc = 0;
		int up = 0;
		int ot = 0;
		for (char c : cs) {
			if (lS.indexOf(c) != -1) {
				lc++;
			} else if (uS.indexOf(c) != -1) {
				up++;
			} else if (nS.indexOf(c) != -1) {
				it++;
			} else {
				ot++;
			}
		}
		System.out.println(it + "," + lc + "," + up + "," + ot);

		Character.isDigit('c');
		Character.isUpperCase('S');

		String st = "javajavajavajajavajvilojvavjvavjava";
		String java = "java";
		int count = 0;
		int index = 0;
		while (st.length() >= java.length()) {
			index = st.indexOf(java);
			st = st.substring(java.length() + index);
			count++;
		}
		System.out.println(count);
	}
}
