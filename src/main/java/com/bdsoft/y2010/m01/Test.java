package com.bdsoft.y2010.m01;

public class Test {

	public static void main(String[] args) throws Exception {

		String str = "2,3,1,";
		String[] ids = str.split(",");
		System.out.println(ids.length);
		for (int i = 0; i < ids.length; i++) {
			System.out.println(i+":"+ids[i]);
		}
	}
}