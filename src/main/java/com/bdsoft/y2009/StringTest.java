package com.bdsoft.y2009;

/**
 * 测试字符串及基础：<br>
 * 1，
 * 
 * @author bdceo
 * @date 2016-9-8 下午13:55
 */
public class StringTest {

	public static void main(String[] args) {
		String s = "abc";
		String s2 = s;
		System.out.println(s == s2);// true，s和s2指向同一内存地址
		s = s + "";
		System.out.println(s == s2);// false，s修改后，指向新的内存地址

		System.out.println("---------------------------");
		// StringBuffer ，自动扩容
		StringBuilder str = new StringBuilder();
		str.append(true);
		System.out.println(str);
		System.out.println(str.capacity());
		str.append(System.currentTimeMillis());
		System.out.println(str);
		System.out.println(str.capacity());

	}

}
