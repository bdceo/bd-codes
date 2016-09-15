package com.bdsoft.y2012.m04;

public class Test {

	public static void main(String[] args) {
		try {
			int r = 12 / 0;
			System.out.println(r);
		} catch (Exception e) {
			System.out.println(e.getMessage());

			StackTraceElement[] st = e.getStackTrace();
			for (StackTraceElement s : st) {
				System.out.println(s.toString());
			}
		}
	}

}
