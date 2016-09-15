/**
 * BDSets.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.generics;

import java.util.HashSet;
import java.util.Set;

/**
 * 实用Sets工具类，计算交集，差集，合集
 * 
 * @author bdceo
 * @date 2016-8-19 上午4:34:31
 * @version V1.0
 */
public class BDSets {

	/**
	 * 合并集合
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static <T> Set<T> union(Set<T> a, Set<T> b) {
		Set<T> result = new HashSet<T>(a);
		result.addAll(b);
		return result;
	}

	/**
	 * 交集元素
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static <T> Set<T> both(Set<T> a, Set<T> b) {
		Set<T> result = new HashSet<T>(a);
		result.retainAll(b);
		return result;
	}

	/**
	 * 差集元素
	 * 
	 * @param superSet 父容器
	 * @param subSet 子容器
	 * @return
	 */
	public static <T> Set<T> dif(Set<T> superSet, Set<T> subSet) {
		Set<T> result = new HashSet<T>(superSet);
		result.removeAll(subSet);
		return result;
	}

	/**
	 * 除交集外的所有元素
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static <T> Set<T> difBoth(Set<T> a, Set<T> b) {
		return dif(union(a, b), both(a, b));
	}
}
