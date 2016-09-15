package com.bdsoft.y2011.m02;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderNo {

	public static void main(String[] args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SS");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date a = sdf.parse("2009-11-11 23:20:56:458");
		Date b = sdf.parse("2008-11-11 23:20:56:458");
		Date c = sdf.parse("2009-10-11 23:20:56:458");
		Date d = sdf.parse("2009-11-10 23:20:56:458");
		Date e = sdf.parse("2009-11-11 22:20:56:458");
		Date f = sdf.parse("2009-11-11 23:19:56:458");
		Date g = sdf.parse("2009-11-11 23:20:55:458");
		System.out.println("ԭ��" + a.getTime());
		System.out.println("�꣺" + b.getTime());
		System.out.println("�£�" + c.getTime());
		System.out.println("�գ�" + d.getTime());
		System.out.println("ʱ��" + e.getTime());
		System.out.println("�֣�" + f.getTime());
		System.out.println("�룺" + g.getTime());
		System.out.println(1000 * 60 * 60);
		System.out.println(1000 * 60 * 60 * 24);
		test();
	}

	public static void test() {
		// Math.random((int)(new Date().getTime()))*
		Random rd = new Random(new Date().getTime());
		System.out.println(rd.nextDouble() * 100000);
	}
}