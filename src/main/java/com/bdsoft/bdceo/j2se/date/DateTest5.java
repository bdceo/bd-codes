package com.bdsoft.bdceo.j2se.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest5 {
	public static void main(String[] args) throws ParseException {
		String s = "2008-08-08";
		System.out.println("ԭʼ�ַ�" + s);
		String pattern = "yyyy-MM-dd";
		System.out.println("��Ӧ���ʽ��" + pattern);
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		Date date = df.parse(s);
		System.out.println("ת�����ֵ��" + date);
		System.out.println();

		s = "05��2��12�� 18:04:33";
		System.out.println("ԭʼ�ַ�" + s);
		pattern = "yy��M��d�� HH:mm:ss";
		System.out.println("��Ӧ���ʽ��" + pattern);
		df.applyPattern(pattern);
		date = df.parse(s);
		System.out.println("ת�����ֵ��" + date);
		System.out.println();

		s = "16/5/2004 20:7:2.050";
		System.out.println("ԭʼ�ַ�" + s);
		pattern = "d/M/yyyy HH:m:s.SSS";
		System.out.println("��Ӧ���ʽ��" + pattern);
		df.applyPattern(pattern);
		date = df.parse(s);
		System.out.println("ת�����ֵ��" + date);
	}
}