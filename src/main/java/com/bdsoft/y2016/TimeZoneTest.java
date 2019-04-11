package com.bdsoft.y2016;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 时区问题测试
 */
public class TimeZoneTest {

	public static void main(String[] args) {
		// 统一设置时区
//		TimeZone.setDefault(TimeZone.getTimeZone("GMT+08:00"));

		Date d1 = new Date();
		Long t1 = d1.getTime();// 与时区无关
		String info = String.format("日期：%s，毫秒：%d", d1.toLocaleString(), t1);
		print(info);

		Date d2 = Calendar.getInstance().getTime();
		Long t2 = d2.getTime();
		info = String.format("日期：%s，毫秒：%d", d2.toLocaleString(), t2);
		print(info);

		// 当前默认时区
		TimeZone tz = TimeZone.getDefault();
		print(tz.toString());
		Long t3 = t1 + (tz.getRawOffset());
		Date d3 = new Date(t3);
		info = String.format("日期：%s，毫秒：%d", d3.toLocaleString(), t3);
		print(info);
		
		// 指定东8区
		tz = TimeZone.getTimeZone("GMT+08:00");
		print(tz.toString());
		Long t4 = t1 + tz.getRawOffset();
		Date d4 = new Date(t4);
		info = String.format("日期：%s，毫秒：%d", d4.toLocaleString(), t4);
		print(info);
	}

	static void print(String info) {
		System.out.println(info);
	}

}
