/**
 * TupleTest.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.generics;

/**
 * 泛型——元组，将一组对象直接打包存储于其中的一个单一对象，可以读取对象，但是不允许修改其对象
 * 
 * @author bdceo
 * @date 2016-8-17 上午8:27:45
 * @version V1.0
 */
public class TupleTest {

	/**
	 * 入口
	 */
	public static void main(String[] args) {
		System.out.println(f());
		System.out.println(g());

		System.out.println("**************************");

		// 参数类型推导
		System.out.println(tuple("bdceo", 28));
		System.out.println(tuple("bdceo", 28, true));
	}

	static TwoTuple<String, Integer> f() {
		return new TwoTuple<String, Integer>("bdceo", 28);
	}

	static TwoTuple f2() {
		return tuple("bdceo", 28);
	}

	static ThreeTuple<String, Integer, Boolean> g() {
		return new ThreeTuple<String, Integer, Boolean>("bdceo", 28, true);
	}

	static ThreeTuple<String, Integer, Boolean> g2() {
		return tuple("bdceo", 28, true);
	}

	// 参数类型推断
	static <A, B> TwoTuple<A, B> tuple(A a, B b) {
		return new TwoTuple<A, B>(a, b);
	}

	static <A, B, C> ThreeTuple<A, B, C> tuple(A a, B b, C c) {
		return new ThreeTuple<A, B, C>(a, b, c);
	}
}

class TwoTuple<A, B> {

	public final A first;
	public final B second;

	public TwoTuple(A a, B b) {
		this.first = a;
		this.second = b;
	}

	public String toString() {
		return String.format("(%s, %s)", first, second);
	}
}

class ThreeTuple<A, B, C> extends TwoTuple<A, B> {

	public final C three;

	public ThreeTuple(A a, B b, C c) {
		super(a, b);
		this.three = c;
	}

	public String toString() {
		return String.format("(%s, %s, %s)", first, second, three);
	}
}

class FourTuple<A, B, C, D> extends ThreeTuple<A, B, C> {

	public final D four;

	public FourTuple(A a, B b, C c, D d) {
		super(a, b, c);
		this.four = d;
	}

	public String toString() {
		return String.format("(%s, %s, %s, %s)", first, second, three, four);
	}
}