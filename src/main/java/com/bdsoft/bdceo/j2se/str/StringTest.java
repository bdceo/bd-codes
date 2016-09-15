package com.bdsoft.bdceo.j2se.str;

public class StringTest {

	public static void main(String[] args) throws Exception {
		// strMemory();
		strExam();
	}

	// 面试问题测试
	public static void strExam() {
		System.out.println("String引用测试");
		String str = "hello";
		reset(str);
		System.out.println("3=" + str);
		System.out.println("String拼接结果 = " + ("ja" + "va" == "java"));

		System.out.println("\n普通对象引用测试");
		// 普通对象引用测试
		Man man = new Man("bdceo");
		reset(man);
		System.out.println("3=" + man.name);

	}

	public static void reset(String str) {
		System.out.println("1=" + str);
		// 此时方法接受的参数str是调用时传递的字符串对象的一个拷贝，两个引用同时指向缓冲区的“hello”，内容相同
		// 但是方法内的临时str拷贝引用，修改了其引用到缓冲区的“welcome”，方法外的str引用依然是“hello”
		str = "welcome";
		System.out.println("2=" + str);
	}

	public static void reset(Man man) {
		System.out.println("1=" + man.name);
		man.name = "bdcoo";
		System.out.println("2=" + man.name);
	}

	// String 内存模型测试
	public static void strMemory() {
		String s1 = "Java";
		String s2 = "Java";
		String s3 = new String("Java");

		System.out.print("s1==s2\t");
		if (s1 == s2) {
			System.out.println(Boolean.TRUE);
		} else {
			System.out.println(Boolean.FALSE);
		}

		System.out.print("s1==s3\t");
		if (s1 == s3) {
			System.out.println(Boolean.TRUE);
		} else {
			System.out.println(Boolean.FALSE);
		}

		System.out.print("s1.equals(s3)\t");
		if (s1.equals(s3)) {
			System.out.println(Boolean.TRUE);
		} else {
			System.out.println(Boolean.FALSE);
		}

		// 将s3在堆内存中的引用，同缓冲池中的内容相同字符串对象比较
		System.out.println(s1 == s3.intern());
	}

}

class Man {
	public String name;

	public Man(String name) {
		this.name = name;
	}

}
