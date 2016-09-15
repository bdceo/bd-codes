package com.bdsoft.bdceo.j2se.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateTest2 {
	/**
	 * ��һ�ֽ�Ϊ�Ѻõķ�ʽ��ʽ������ʱ��ֵ
	 * 
	 * @param c
	 *            ����ʱ�����
	 * @return ��ʽ���������ʱ���ַ�
	 */
	public static String toFriendlyString(Calendar c) {
		if (c != null) {
			DateFormat df = new SimpleDateFormat("yyyy��MM��dd�� HH:mm:ss");
			return df.format(c.getTime());			
		}
		return null;
	}

	public static void main(String[] args) {
		Calendar c1 = Calendar.getInstance();
		System.out.println("������ʽ��Calendar.getInstance()");
		System.out.println("����ʱ�䣺" + DateTest2.toFriendlyString(c1));
		System.out.println();

		Calendar c2 =new GregorianCalendar();
		System.out.println("������ʽ��new GregorianCalendar()");
		System.out.println("����ʱ�䣺" + DateTest2.toFriendlyString(c2));
		System.out.println();

		// �������4�Ϊ���ꡢ�¡���
		Calendar c3 = new GregorianCalendar(2008, 8, 8);
		System.out.println("������ʽ��new GregorianCalendar(2008, 8, 8)");
		System.out.println("����ʱ�䣺" + DateTest2.toFriendlyString(c3));
		System.out.println();

		// �������4�Ϊ���ꡢ�¡��ա�ʱ����
		Calendar c4 = new GregorianCalendar(2008, 8, 8, 6, 10);
		System.out.println("������ʽ��new GregorianCalendar(2008, 8, 8, 6, 10)");
		System.out.println("����ʱ�䣺" + DateTest2.toFriendlyString(c4));
		System.out.println();

		// �������4�Ϊ���ꡢ�¡��ա�ʱ���֡���
		Calendar c5 = new GregorianCalendar(2008, 8, 8, 18, 10, 5);
		System.out.println("������ʽ��new GregorianCalendar(2008, 8, 8, 18, 10, 5)");
		System.out.println("����ʱ�䣺" + DateTest2.toFriendlyString(c5));
	}
}