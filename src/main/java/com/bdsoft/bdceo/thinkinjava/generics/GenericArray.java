/**
 * GenericArray.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.generics;

import java.lang.reflect.Array;

/**
 * 泛型数组：参照3的实现，在创建数组时指定具体类型，当返回时无需强转
 * 
 * @author bdceo
 * @date 2016-8-29 下午1:18:39
 * @version V1.0
 */
public class GenericArray<T> {

	private T[] arr;

	// 警告：类型擦除后，运行时数组无法确定具体类型
	@SuppressWarnings("unchecked")
	public GenericArray(int size) {
		arr = (T[]) new Object[size];
	}

	public void put(int i, T item) {
		arr[i] = item;
	}

	public T get(int i) {
		return arr[i];
	}

	public T[] rep() {
		return arr;
	}

	/**
	 * 入口
	 */
	public static void main(String[] args) {
		GenericArray<Integer> gai = new GenericArray<Integer>(10);
		gai.put(4, 28);
		System.out.println(gai.get(4));
		// ClassCastException，类型擦出后，实际是Object[]
		// Integer[] cp = gai.rep();
		Object[] cp = gai.rep();

		GenericArray2<Integer> gai2 = new GenericArray2<Integer>(10);
		for (int i = 0; i < 10; i++) {
			gai2.put(i, i);
		}
		for (int i = 0; i < 10; i++) {
			System.out.print(gai2.get(i) + " ");
		}
		System.out.println();
		try {
			Integer[] cp2 = gai2.rep();
		} catch (Exception e) {
			System.out.println(e);
		}

		// 通过类型标签还原泛型擦除
		GenericArray3<Integer> gai3 = new GenericArray3<Integer>(Integer.class, 10);
		Integer[] cp3 = gai3.rep();
		System.out.println("nice");
	}

}

class GenericArray2<T> {

	private Object[] arr;

	// 警告：类型擦除后，运行时数组无法确定具体类型
	public GenericArray2(int size) {
		arr = new Object[size];
	}

	public void put(int i, T item) {
		arr[i] = item;
	}

	@SuppressWarnings("unchecked")
	public T get(int i) {
		return (T) arr[i];
	}

	@SuppressWarnings("unchecked")
	public T[] rep() {
		return (T[]) arr;
	}

}

class GenericArray3<T> {

	private T[] arr;

	// 警告：类型擦除后，运行时数组无法确定具体类型
	@SuppressWarnings("unchecked")
	public GenericArray3(Class<T> t, int size) {
		arr = (T[]) Array.newInstance(t, size);
	}

	public void put(int i, T item) {
		arr[i] = item;
	}

	public T get(int i) {
		return arr[i];
	}

	// 无需转型，外部即可赋值给创建类时指定的类型数组
	public T[] rep() {
		return arr;
	}

}
