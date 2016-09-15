package com.bdsoft.bdceo.j2se.date;

import java.util.Date;

public class DateTest {
	public static void main(String[] args) {
		// 2008-08-08 20:00:00��Ӧ�ĺ�����
		long t2008 = 1218196800000L;
		// 1900-01-01 20:00:00��Ӧ�ĺ�����
		long t1900 = -2208945952000L;

		// ָ��������Date����
		Date d2008 = new Date(t2008);
		// ʹ��ϵͳĬ��ʱ�䴴��Date����
		Date d1900 = new Date();
		// ͨ�����ú�����ı����ں�ʱ��
		d1900.setTime(t1900);

		System.out.println("���÷�����d1900.before(d2008)");
		System.out
				.print("�ȽϽ��\"1900-01-01 20:00:00\"��\"2008-08-08 20:00:00\"");
		// ʹ��before()�����Ƚ�
		if (d1900.before(d2008)) {
			System.out.println("֮ǰ");
		} else {
			System.out.println("֮��");
		}
		
		System.out.println();
		
		System.out.println("���÷�����d2008.after(d1900)");
		System.out
				.print("�ȽϽ��\"2008-08-08 20:00:00\"��\"1900-01-01 20:00:00\"");
		// ʹ��before()�����Ƚ�
		if (d2008.after(d1900)) {
			System.out.println("֮��");
		} else {
			System.out.println("֮ǰ");
		}
		
		System.out.println();
		
		System.out.println("���÷�����d1900.compareTo(d2008)");
		System.out
				.print("�ȽϽ��\"1900-01-01 20:00:00\"��\"2008-08-08 20:00:00\"");
		// ʹ��compareTo()�����Ƚ�
		int i = d1900.compareTo(d2008);
		if (i == -1) {
			System.out.println("֮ǰ");
		} else if (i == 1) {
			System.out.println("֮��");
		} else if (i == 0) {
			System.out.println("��ͬһʱ��");
		}
	}
}