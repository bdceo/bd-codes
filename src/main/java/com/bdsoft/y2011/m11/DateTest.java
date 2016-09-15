package com.bdsoft.y2011.m11;

import java.util.Date;

public class DateTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Date d = new Date();

		long t = d.getTime();

		System.out.println(t+" / "+(t+"").length());
		
		Date d2 = new Date(t + 11);
 
		System.out.println(d.compareTo(d2));
		
		System.out.println(new Date().toGMTString());
		System.out.println(new Date().toLocaleString());
	}

}
