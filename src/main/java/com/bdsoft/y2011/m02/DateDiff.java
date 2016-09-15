package com.bdsoft.y2011.m02;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDiff {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public static void main(String[] args) {
		comp1();
	}

	public static void comp1() {
		int days = 0;
		try {
			Date d1 = sdf.parse("2009-10-12");
			Date d3=sdf.parse("2009-11-11"); 
			days = (int) ((d3.getTime() -d1.getTime()) / 1000 / 60 / 60 / 24)	;
			/**
			 * 2009-11-11 23:20    2674879508 
			 * 2009-11-11 23:20    2674882582 
			 * 
			 * 2009-10-12 21:49    2522052023
			 * 2009-10-12 21:31    2521920198
			 * 
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("��4����������Ѿ���" + days + "���ˡ�");
	}
}