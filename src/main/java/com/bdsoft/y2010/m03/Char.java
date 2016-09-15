package com.bdsoft.y2010.m03;

public class Char {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(('A'+0)+"-"+('Z' + 0));
		for (int i = 65; i < 65 + 26; i++) {
			char c = (char) i;
			System.out.print(c);
			if (i % 8 == 0)
				System.out.println();
		}
		String a="A";
		int c=a.charAt(0);
		System.out.println(c+0);
	}
}