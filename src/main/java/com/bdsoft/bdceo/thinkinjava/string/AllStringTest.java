package com.bdsoft.bdceo.thinkinjava.string;

import java.util.Random;

/**
 * 字符串测试
 */
public class AllStringTest {

	

	public static void main(String[] args) {
		// String不可变，任何看似修改的函数，都会创建新的String对象
		String s = "bdceo";
		System.out.println(s);

		// 字符串的拼接操作，编译器会自动优化改为StringBuilder去执行
		// 但是append方法内的拼接，同样会创建一个StringBuilder，编译器掉入陷阱
		StringBuilder sb = new StringBuilder(100);
		for (int i = 0; i < 10; i++) {
			sb.append(new Random().nextInt(100));
			sb.append(",");
		}
		// 删除末尾逗号
		sb.delete(sb.length() - 1, sb.length());
		System.out.println(sb.toString());

		// 【线程安全】的字符串拼接，内部方法都有synchronized关键字
		StringBuffer sbf = new StringBuffer(100);
		sbf.append("当前时间戳:");
		sbf.append(System.currentTimeMillis());
		System.out.println(sbf.toString());

		System.out.println(new AllStringTest());
	}

	/**
	 * 将对象转换成字符串
	 */
	public String toString() {
		// 无意识的递归调用，会导致栈溢出，一直在尝试将this转换成字符串，从而调用自身的toString方法
		// return "my loc > "+this;
		// 正确的方式应该是调用父类Object的方法
		return "my loc > " + super.toString();
	}

}
