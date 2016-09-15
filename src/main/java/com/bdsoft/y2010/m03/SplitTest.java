package com.bdsoft.y2010.m03;

public class SplitTest {

	public static void main(String[] as) {
		String uu = "ID_9006|ID_9005|ID_9002";
		String[] us = uu.split("\\|");
		int size = us.length;
		for (int i = 0; i < size; i++) {
			System.out.println(us[i]);
		}
	}
}
