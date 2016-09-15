package com.bdsoft.y2011.m12;

public class TestChar {

	public static void main(String[] eeeStrings) {
		int i = 010;
		System.out.println(i);
		String s = "123";
		int j = (int) s.charAt(0);
		System.out.println(j);
		j = Integer.parseInt(Character.toString(s.charAt(0)));
		System.out.println(j); 
		System.out.println(s.substring(1));
	}

}
