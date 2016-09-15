package com.bdsoft.y2011.m05;

public class Yiwei {

	public static void main(String[] args) {
		// 1000 0000
		int i = 0x80;
		
		// 0000 0001 & 0000 0001
		int j = (i >> 7) & 0x01;

		System.out.println(j);// 1
	}

}
