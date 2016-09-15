/**
 * LostInformation.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.generics;

import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bdsoft.bdceo.thinkinjava.object.HelloObject;

/**
 * 泛型：类型擦出
 * 
 * @author bdceo
 * @date 2016-8-19 上午4:52:33
 * @version V1.0
 */
public class LostInformation {

	/**
	 * 入口
	 */
	public static void main(String[] args) {
		List<HelloObject> list = new ArrayList<HelloObject>();
		TypeVariable[] x = list.getClass().getTypeParameters(); // 反省声明的类型参数信息
		System.out.println(Arrays.toString(x)); // [E]

		Class c1 = new ArrayList<String>().getClass();
		Class c2 = new ArrayList<Integer>().getClass();
		System.out.println(c1 == c2); // true，运行时都将是原始/原生类型

		// 泛型类型参数将擦出到它的第一个边界
		new Mainpulator2<HasF>(new HasF()).mainpulator();
	}

}

// 泛型擦除
class Mainpulator3 {
	private HasF obj;

	public Mainpulator3(HasF x) {
		this.obj = x;
	}

	public void mainpulator() {
		obj.f(); // 该类模拟了，类型擦出后的实际效果
	}
}

// 泛型边界，擦出到第一个边界，HasF
class Mainpulator2<T extends HasF> {
	private T obj;

	public Mainpulator2(T x) {
		this.obj = x;
	}

	public void mainpulator() {
		obj.f(); // 通过泛型参数继承，设定泛型边界，该方法将可以被正确调用
	}
}

// 通用泛型类
class Mainpulator<T> {
	private T obj;

	public Mainpulator(T x) {
		this.obj = x;
	}

	public void mainpulator() {
		// obj.f(); // 无法编译，类型擦出后，无法得知泛型类型具体参数，也就无法调用其具体某一方法了
	}
}

class HasF {
	public void f() {
		System.out.println("HasF.f()");
	}
}
