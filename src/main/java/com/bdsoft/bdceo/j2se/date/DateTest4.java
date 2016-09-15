package com.bdsoft.bdceo.j2se.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest4 {
	public static void main(String[] args) {
		// ʹ��ϵͳ��ǰ����ʱ��ֵ����һ��Date����
		Date now = new Date();

		// ����һ�����ڸ�ʽ���ʽ
		String pattern = "���:G;���:y;�·�:M;��:d;ʱ(1~12):h;ʱ(0~23):H;��:m;��:s;����:S;����:E;��/����:a;ʱ��:z";
		// ʹ�����ڸ�ʽ���ʽ����һ��SimpleDateFormat����
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		// ����SimpleDateFormat���format(Date date)������Date������и�ʽ���������ظ�ʽ������ַ�
		// �÷����̳���java.text.DateFormat��
		System.out.println("1λ��" + df.format(now));

		// ����һ���µ����ڸ�ʽ���ʽ
		pattern = "���:GG;���:yy;�·�:MM;��:dd;ʱ(1~12):hh;ʱ(0~23):HH;��:mm;��:ss;����:SS;����:EE;��/����:aa;ʱ��:zz";
		// ����SimpleDateFormat��applyPattern(String pattern)�������´��������ڸ�ʽ���ʽ�滻��ԭ�е�
		df.applyPattern(pattern);
		System.out.println("2λ��" + df.format(now));

		pattern = "���:GGG;���:yyy;�·�:MMM;��:ddd;ʱ(1~12):hhh;ʱ(0~23):HHH;��:mmm;��:sss;����:SSS;����:EEE;��/����:aaa;ʱ��:zzz";
		df.applyPattern(pattern);
		System.out.println("3λ��" + df.format(now));

		pattern = "���:GGGG;���:yyyy;�·�:MMMM;��:dddd;ʱ(1~12):hhhh;ʱ(0~23):HHHH;��:mmmm;��:ssss;����:SSSS;����:EEEE;��/����:aaaa;ʱ��:zzzz";
		df.applyPattern(pattern);
		System.out.println("4λ��" + df.format(now));

		pattern = "���:GGGGG;���:yyyyy;�·�:MMMMM;��:ddddd;ʱ(1~12):hhhhh;ʱ(0~23):HHHHH;��:mmmmm;��:sssss;����:SSSSS;����:EEEEE;��/����:aaaaa;ʱ��:zzzzz";
		df.applyPattern(pattern);
		System.out.println("5λ��" + df.format(now));

		pattern = "���:GGGGGG;���:yyyyyy;�·�:MMMMMM;��:dddddd;ʱ(1~12):hhhhhh;ʱ(0~23):HHHHHH;��:mmmmmm;��:ssssss;����:SSSSSS;����:EEEEEE;��/����:aaaaaa;ʱ��:zzzzzz";
		df.applyPattern(pattern);
		System.out.println("6λ��" + df.format(now));
	}
}