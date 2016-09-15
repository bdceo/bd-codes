package com.bdsoft.bdceo.jvm.bc;

import java.io.Serializable;

public class OverloadTest {

	// 注释sayHello(Object arg)后，执行，发生了自动装箱，变长参数，字符被看做是数组元素
	public static void sayHello(char... arg) {
		System.out.println("hello char ...");
	}

	// 注释sayHello(Serializable arg)后，执行，发生了自动装箱，转换成了实现的父类
	// public static void sayHello(Object arg) {
	// System.out.println("hello object");
	// }

	// 注释sayHello(Character arg)后，执行，发生了自动装箱，转换成了实现的父接口
	// public static void sayHello(Serializable arg) {
	// System.out.println("hello Serializable");
	// }

	// 注释sayHello(long arg)后，执行，发生了自动装箱
	// public static void sayHello(Character arg) {
	// System.out.println("hello Character");
	// }

	// 注释sayHello(int arg)后，执行，发生了两次自动类型转换
	// public static void sayHello(long arg) {
	// System.out.println("hello long");
	// }

	// 注释sayHello(char arg)后，执行，发生了一次自动类型转换
	// public static void sayHello(int arg) {
	// System.out.println("hello int");
	// }

	// 默认执行
	// public static void sayHello(char arg) {
	// System.out.println("hello char");
	// }

	// 总结：java实现方法重载的本质，是在编译期间选择“静态分派”目标方法 的过程
	public static void main(String[] args) {
		char ach = 'c';
		sayHello(ach);
	}

}
