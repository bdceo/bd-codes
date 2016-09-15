package com.bdsoft.bjsxt.util;

public class StringTest {

	public static void main(String[] args) {
		String s1 = "hello";
		String s2 = "world";
		String s3 = "hello";
		System.out.println(s1 == s2);
		System.out.println(s1.equals(s2));
		System.out.println(s1 == s3);
		System.out.println(s1.equals(s3));

		char[] c = { 'q', 'a', 'w', 'f', ' ', 'd', 'z', ' ' };
		String s4 = new String(c);
		String s5 = new String(c, 0, 4);
		System.out.println(s4);
		System.out.println(s5);

		System.out.println(s4.charAt(1));
		System.out.println(s5.length());
		System.out.println(s4.indexOf("w"));
		System.out.println(s4.replace('a', 'A'));

		System.out.println(s4.startsWith("a"));
		System.out.println(s4.endsWith("z"));
		System.out.println(s4.toUpperCase());
		System.out.println(s4.toLowerCase());

		System.out.println(s4.substring(2));
		System.out.println(s4.substring(2, 5));
		System.out.println(s4.trim());

	}
}
