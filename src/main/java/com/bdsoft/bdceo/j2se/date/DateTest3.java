package com.bdsoft.bdceo.j2se.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTest3 {
	/**
	 * ��һ�ֽ�Ϊ�Ѻõķ�ʽ��ʽ������ʱ��ֵ
	 * 
	 * @param c
	 *            ����ʱ�����
	 * @return ��ʽ���������ʱ���ַ�
	 */
	public static String toFriendlyString(Calendar c) {
		if (c != null) {
			DateFormat df = new SimpleDateFormat("yyyy��MM��dd�� HH:mm:ss.SSS");
			return df.format(c.getTime());
		}
		return null;
	}

	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		System.out.println("��ǰʱ�̣�" + DateTest3.toFriendlyString(c));
		System.out.println();

		System.out.println("������ƣ�Calendar.AM_PM");
		System.out.println("��?�壺�������ʶ�����緵��Calendar.AM=0�����緵��Calendar.PM=1");
		System.out.println("���Խ��" + c.get(Calendar.AM_PM));
		System.out.println();

		System.out.println("������ƣ�Calendar.DATE");
		System.out.println("��?�壺һ�����еĵڼ��죬ͬCalendar.DAY_OF_MONTH");
		System.out.println("���Խ��" + c.get(Calendar.DATE));
		System.out.println();

		System.out.println("������ƣ�Calendar.DAY_OF_MONTH");
		System.out.println("��?�壺һ�����еĵڼ��죬ͬCalendar.DATE");
		System.out.println("���Խ��" + c.get(Calendar.DAY_OF_MONTH));
		System.out.println();

		System.out.println("������ƣ�Calendar.DAY_OF_WEEK");
		System.out.println("��?�壺���ڼ���");
		System.out.println("������:Calendar.SUNDAY=1");
		System.out.println("����һ:Calendar.MONDAY=2");
		System.out.println("���ڶ�:Calendar.TUESDAY=3");
		System.out.println("������:Calendar.WEDNESDAY=4");
		System.out.println("������:Calendar.THURSDAY=5");
		System.out.println("������:Calendar.FRIDAY=6");
		System.out.println("������:Calendar.SATURDAY=7");
		System.out.println("���Խ��" + c.get(Calendar.DAY_OF_WEEK));
		System.out.println();

		System.out.println("������ƣ�Calendar.DAY_OF_WEEK_IN_MONTH");
		System.out.println("��?�壺��һ�����Ӧ�����ڼ��ڸ������ǵڼ��γ���");
		System.out.println("���Խ��" + c.get(Calendar.DAY_OF_WEEK_IN_MONTH));
		System.out.println();

		System.out.println("������ƣ�Calendar.DAY_OF_YEAR");
		System.out.println("��?�壺һ���еĵڼ���");
		System.out.println("���Խ��" + c.get(Calendar.DAY_OF_YEAR));
		System.out.println();

		System.out.println("������ƣ�Calendar.HOUR");
		System.out.println("��?�壺12Сʱ���µ�Сʱ���������ҹ��ʾΪ0");
		System.out.println("���Խ��" + c.get(Calendar.HOUR));
		System.out.println();

		System.out.println("������ƣ�Calendar.HOUR_OF_DAY");
		System.out.println("��?�壺24Сʱ���µ�Сʱ����ҹ��ʾΪ0");
		System.out.println("���Խ��" + c.get(Calendar.HOUR_OF_DAY));
		System.out.println();

		System.out.println("������ƣ�Calendar.MILLISECOND");
		System.out.println("��?�壺������");
		System.out.println("���Խ��" + c.get(Calendar.MILLISECOND));
		System.out.println();

		System.out.println("������ƣ�Calendar.MINUTE");
		System.out.println("��?�壺����");
		System.out.println("���Խ��" + c.get(Calendar.MINUTE));
		System.out.println();

		System.out.println("������ƣ�Calendar.MONTH");
		System.out.println("��?�壺�·ݣ���0��11��ʾ12���·ݣ���ʵ���·�ֵС1");
		System.out.println("���Խ��" + c.get(Calendar.MONTH));
		System.out.println();

		System.out.println("������ƣ�Calendar.SECOND");
		System.out.println("��?�壺��");
		System.out.println("���Խ��" + c.get(Calendar.SECOND));
		System.out.println();

		System.out.println("������ƣ�Calendar.WEEK_OF_MONTH");
		System.out.println("��?�壺һ�����еĵڼ�������");
		System.out.println("���Խ��" + c.get(Calendar.WEEK_OF_MONTH));
		System.out.println();

		System.out.println("������ƣ�Calendar.WEEK_OF_YEAR");
		System.out.println("��?�壺һ���еĵڼ�������");
		System.out.println("���Խ��" + c.get(Calendar.WEEK_OF_YEAR));
		System.out.println();

		System.out.println("������ƣ�Calendar.YEAR");
		System.out.println("��?�壺���");
		System.out.println("���Խ��" + c.get(Calendar.YEAR));
	}
}