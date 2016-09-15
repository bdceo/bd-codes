package com.bdsoft.y2011.m02;

import java.util.Calendar;

public class WorkDayEveryMonth {

	public static Calendar cal = Calendar.getInstance();

	public static void main(String[] args) {
		
		String str="a��s���Ƕ�ffsdfdsf";
		System.out.println(str.substring(0,4));

		System.out.println("�����ǣ�" + (cal.get(Calendar.MONTH) + 1) + "�·�");
		// cal.add(2, -7);
		System.out.println(cal.get(1) + "��" + (cal.get(2) + 1) + "��"
				+ cal.get(5) + "��");
		// System.out.println("������Ǵӣ�" + cal.getActualMinimum(5) + "��~"
		// + cal.getActualMaximum(5) + "��");
		// System.out.println(cal.get(Calendar.DATE));
		// System.out.println("�Ǳ��µĵڣ�" + cal.get(Calendar.WEEK_OF_MONTH) +
		// " ������");
		// System.out.println("���������ڣ�" + (cal.get(Calendar.DAY_OF_WEEK) - 1));
		// System.out.println("�Ǳ��µĵڣ�" + cal.get(Calendar.DAY_OF_MONTH) + " ��");
		test();
	}

	public static void test() {
		int temp = -1;
		int day_of_week = 0;
		int start = cal.getActualMinimum(5);
		int end = cal.getActualMaximum(5);
		System.out.print("����ó�" + cal.get(Calendar.YEAR) + "��"
				+ (cal.get(Calendar.MONTH) + 1) + "����:");
		for (int i = start; i <= end; i++) {
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), i);
			temp = cal.get(Calendar.DAY_OF_WEEK);
			if (temp == Calendar.SATURDAY || temp == Calendar.SUNDAY) {
				day_of_week++;
			}
		}
		System.out.println(+(end - day_of_week) + "������");
	}
}