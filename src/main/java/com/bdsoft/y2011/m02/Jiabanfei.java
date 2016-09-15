package com.bdsoft.y2011.m02;

import java.util.Hashtable;

public class Jiabanfei {

	public static double DAY_WORK_TIME = 8;// 每天工作时间
	public static double DAY_OF_MONTH = 21.75;// 每月工资天数

	public static void main(String[] args) {
		// double chenchao = 18.5 + 25 + 20.5 + 14.5 + 27.5 + 19 + 25 + 22.5 +
		// 33
		// + 26 + 31 + 17;
		// double dingchenye = 24.5 + 29 + 27 + 20.5 + 30.5 + 24 + 27 + 32 + 26
		// + 42 + 48 + 39 + 37 + 34 + 10;
		// double caopengxiang = 6 + 30.5 + 40 + 42 + 16;
		// double linan = 14 + 12;
		// System.out.println("陈超加班时间：" + chenchao + " 小时");
		// System.out.println("丁辰叶加班时间：" + dingchenye + " 小时，加班费："
		// + (dingchenye / DAY_WORK_TIME) * (3000 / DAY_OF_MONTH));
		// System.out.println("曹鹏翔加班时间：" + caopengxiang + " 小时");
		// System.out.println("李楠加班时间：" + linan + " 小时");

		Jdi2 j2 = new Jdi2();
		j2.test();
	}
}

interface I1 {
	void test();
}

interface I2 {
	int sub() throws Exception;
}

class Jdi extends Number implements I1, I2 {
	public void test() {
	}

	public int sub() {
		return 1;
	}

	@Override
	public double doubleValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float floatValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int intValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long longValue() {
		// TODO Auto-generated method stub
		return 0;
	}
}

class Jdi2 extends ClassLoader {

	public void test() {
		Hashtable<String, String> ht = new Hashtable<String, String>();
		ht.put("1", "bdceo");
		ht.put("2", "youknowce");
		ht.put("3", "jdi");
		System.out.println(ht.size());

	}

}