package com.bdsoft.bdceo.j2se.str;

public class StringReverse {

	public static void main(String[] args) {
		String s = "A quick brown fox jumps over the lazy dog.";
		for (int i = s.length(); i > 0; i--) {
			System.out.print(s.charAt(i - 1));
		}
		System.out.println();

		String s2 = "A quick brown fox jumps over the lazy dog.";
		StringBuffer buff = new StringBuffer(s2);
		System.out.println(buff.reverse().toString());
	}
}