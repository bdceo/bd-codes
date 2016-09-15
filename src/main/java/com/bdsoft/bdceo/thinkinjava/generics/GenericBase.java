/**
 * GenericBase.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.generics;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * GenericBase
 * 
 * @author bdceo
 * @date 2016-8-29 上午10:10:52
 * @version V1.0
 */
public class GenericBase<T> {
	private T val;

	public void set(T arg) {
		val = arg;
	}

	public T get() {
		return val;
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Generic2 g2 = new Generic2();
		g2.set("dd"); // 会触发编译器警告

	}
}

class ArrayMaker<T> {
	private Class<T> kind;

	public ArrayMaker(Class<T> kind) {
		this.kind = kind;
	}

	// 警告，可能有类型转换问题
	public T[] create(int length) {
		return (T[]) Array.newInstance(kind, length);
	}
}

class ListMaker<T> {

	// 泛型类型约束一致
	public List<T> create() {
		return new ArrayList<T>();
	}

	// 警告：与上方法，缺少泛型标识
	public List<T> create2() {
		return new ArrayList();
	}

	public List<T> create3(T t, int n) {
		List<T> res = new ArrayList<T>(n);
		for (int i = 0; i < n; i++) {
			res.add(t);
		}
		return res;
	}
}

class Generic1<T> extends GenericBase<T> {
}

class Generic2 extends GenericBase {

}
