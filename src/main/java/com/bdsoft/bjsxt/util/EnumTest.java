package com.bdsoft.bjsxt.util;

public class EnumTest {

	public enum Color {
		red, yello, black
	};

	public static void main(String[] args) {
		Color c = EnumTest.Color.red;
		switch (c) {
		case red:
			System.out.println("红色！");
			break;
		case yello:
			System.out.println("黄色！");
			break;
		default:
			System.out.println("黑色！");
		}
	}

}
