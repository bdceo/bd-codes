package com.bdsoft.bdceo.jvm.cl;

public class InitializationTest {

	// 被动使用类字段，不会导致类加载初始化
	public static void main(String[] args) {
		// 1，子类引用父类的静态字段，不会导致子类初始化
		System.out.println(SubClass.value);

		// 2，通过数组定义来引用类，不会触发此类的初始化
		SuperClass[] spca = new SuperClass[10];

		// 3，常量在编译阶段会存入相应类的常量池中，本质上并没有直接引用到定义常量的类，所以不会触发类的初始化
		System.out.println(ConstClass.STR);
	}

}

class ConstClass {
	public static final String STR = "hello world";
	static {
		System.out.println("ConstClass init!");
	}
}

class SuperClass {
	public static int value = 123;
	static {
		System.out.println("SuperClass init!");
	}
}

class SubClass extends SuperClass {
	static {
		System.out.println("SubClass init!");
	}
}