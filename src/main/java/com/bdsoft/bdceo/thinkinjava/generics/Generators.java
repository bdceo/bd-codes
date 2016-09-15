/**
 * Generators.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.generics;

import java.util.Collection;

/**
 * Generators
 * 
 * @author bdceo
 * @date 2016-8-19 上午4:30:28
 * @version V1.0
 */
public class Generators {
	
	// 泛型生成器
	public static <T> Collection<T> fill(Collection<T> col, Generator<T> gen, int n) {
		for (int i = 0; i < n; i++) {
			col.add(gen.next());
		}
		return col;
	}
	
	public static void main(String[] args) {
		
	}
}
