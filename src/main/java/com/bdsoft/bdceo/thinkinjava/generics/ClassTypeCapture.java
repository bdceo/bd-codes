/**
 * ClassTypeCapture.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.generics;

/**
 * 类型擦出的补偿，通过类型标签来对擦出进行补偿
 * 
 * @author bdceo
 * @date 2016-8-29 上午11:23:08
 * @version V1.0
 */
public class ClassTypeCapture<T> {

	// class类型标签
	private Class<T> kind;

	public ClassTypeCapture(Class<T> kind) {
		this.kind = kind;
	}

	public boolean is(Object obj) {
		return kind.isInstance(obj);
	}

	/**
	 * 入口
	 */
	public static void main(String[] args) {
		ClassTypeCapture<Building> cb = new ClassTypeCapture<Building>(Building.class);
		System.out.println(cb.is(new Building()));// true
		System.out.println(cb.is(new House())); // true

		ClassTypeCapture<House> cb2 = new ClassTypeCapture<House>(House.class);
		System.out.println(cb2.is(new Building()));// false
		System.out.println(cb2.is(new House())); // true

		// 测试类型检测
		System.out.println("---------------------------");
		Room r = new Room();
		House h = new House();
		House h2 = new Room();
		Building b = new Building();

		// instanceof 操作符，判断左边的实例是否为右边的类型，包括继承关系判断
		// isInstance 只是判断当前实例的具体类型是否相等，不判断继承关系
		System.out.println(r instanceof Room);// true
		System.out.println(r instanceof House);// true
		System.out.println(r instanceof Building);// true
		System.out.println("-----");
		System.out.println(h instanceof Room); // false
		System.out.println(h2 instanceof Room);// true
		System.out.println(h instanceof House); // true
		System.out.println(h instanceof Building);// true
		System.out.println("-----");
		System.out.println(r.getClass().isInstance(r));// true
		System.out.println(r.getClass().isInstance(h));// false
		System.out.println(r.getClass().isInstance(h2));// true
		System.out.println(r.getClass().isInstance(b));// false

	}
}

class Building {
}

class House extends Building {
}

class Room extends House {
}
