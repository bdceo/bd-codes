package com.bdsoft.y2011.m05;

public class TestJinzhi {
 
	public static void main(String[] args) {
		// 0001 1001 （16+8+1）
		int num = 25;
		// & 0111 1111
		sp("" + (num & 0x7f));
		// = 0001 1001 25
	}

	public static void sp(String str) {
		System.out.println(str);
	}

}
