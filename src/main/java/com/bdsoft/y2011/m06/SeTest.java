package com.bdsoft.y2011.m06;

public class SeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// floatEQdouble();
		// opeAdd();
		// opeAdd2();
		// opeDev();
		// opeMod();
		// opeEq();
		opeMb();
		// opeIf();
	}

	/**
	 * 整数部分相同，且小数都为0，则float等于double
	 * 
	 * 整数部分相同，且小数部分不为0但相等，则float不等于double
	 */
	public static void floatEQdouble() {
		float f1 = 10.0f, f2 = 10.44f;
		double d1 = 10.0, d2 = 10.44;
		System.out.println(f1 == d1);// true
		System.out.println(f2 == d2);// false
	}

	// 加法运算，结果至少是int型，结果赋值给byte会出错
	// 减法运算，同上
	public static void opeAdd() {
		byte m = 3, n = 5;
		// byte t = m + n;需要如下强转赋值
		byte t2 = (byte) (m + n);

		byte x = -4;
		// byte y=-x;
		byte y = (byte) -x;
	}

	// 加法运算，自动将数字作为字符串处理
	// 如果先遇到字符串再加数字，会直接按照字符串拼接处理
	// 如果先遇到数字相加再加字符串，则会先进行数字运算再拼接字符串
	public static void opeAdd2() {
		String a = "3+7=";
		String b = "是结果。";
		int x = 3, y = 7;
		System.out.println(a + x + y);
		System.out.println(a + (x + y));
		System.out.println(x + y + b);
	}

	// 0 不能当除数
	// 浮点数除以0，可以获得正无穷大和负无穷大或非实数
	public static void opeDev() {
		int a = 10, b = 5, c = 100;
		System.out.println("int a = 10, b = 5, c = 100;");
		System.out.println("a / b = " + (a / b));
		System.out.println("a / c = " + (a / c));

		double x = 10.0, y = 5.0;
		System.out.println("double x=10.0,y=5.0;int b=5;");
		System.out.println("x / y = " + (x / y));
		System.out.println("x / b = " + (x / b));

		double m = -10.0;
		System.out.println("double x=10.0,m = -10.0;");
		System.out.println("x / 0 = " + (x / 0));
		System.out.println("m / 0 = " + (m / 0));

		System.out.println("0 / a = " + (0 / a));

		// java.lang.ArithmeticException: / by zero
		// System.out.println("a / 0 = " + (a / 0));
		// System.out.println("0 / 0 = " + (0 / 0));

		System.out.println("0.0 / 0 = " + (0.0 / 0));
	}

	// 取模运算，结果符号由左边数决定
	// 有一个浮点型，结果就为浮点型
	// 左边数小于右边，结果为左边数自身
	public static void opeMod() {
		System.out.println("15 % 4 = " + (15 % 4));
		System.out.println("-15 % 4 = " + (-15 % 4));

		System.out.println("6.8 % 4.3 = " + (6.8 % 4.3));
		System.out.println("6.8 % 9.3 = " + (6.8 % 9.3));
		System.out.println("0 % 12.9 = " + (0 % 12.9));// 0.0
		System.out.println("12.9 % 0 = " + (12.9 % 0));// NaN
	}

	public static void opeEq() {
		System.out.println(12.0 == 12);
		System.out.println('a' == 97.0);
		System.out.println("A -> Z = " + (int) 'A' + "-" + (int) 'Z');
		System.out.println("a -> z = " + (int) 'a' + "-" + (int) 'z');
	}

	// 移位运算特例
	// 先计算要移动的位数，是否超过数据类型的宽度，超了，不做移位操作，结果等于原值
	public static void opeMb() {
		int i = 88 >> 32;
		long ln = 100 >> 64;
		System.out.println("88 >> 32 = " + i);
		System.out.println("100 >> 64 = " + ln);
		// 解释：在移位运算前，java系统首先把要移的位数与被移数的数据类型位数求余
		// --> i = 88>>32 --> 32(要移的位数)%32(int类型88所占的位数)
		// --> ln= 100>>64 --> 64(要移的位数)%64(long类型100所占的位数)
		// 然后移动余数个位数
	}

	public static void opeIf() {
		boolean b = false;
		if (b = true) {// b=true 表达式返回b的值
			System.out.println("b = true");
		}
	}

}
