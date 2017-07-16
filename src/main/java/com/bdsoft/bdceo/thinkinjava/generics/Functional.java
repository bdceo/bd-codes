/**
 * Functional.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.generics;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 15.18--将函数对象用作策略
 * 
 * @author bdceo
 * @date 2016-12-27 下午1:36:52
 * @version V1.0
 */
public class Functional {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> li = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		Integer result = reduce(li, new IntegerAdder());
		System.out.println(result);

		List<Integer> res = filter(li, new GreaterThan<Integer>(4));
		System.out.println(res);
	}

	// 计算序列中元素的总和
	public static <T> T reduce(Iterable<T> seq, Combiner<T> combiner) {
		Iterator<T> it = seq.iterator();
		if (it.hasNext()) {
			T result = it.next();
			while (it.hasNext()) {
				result = combiner.combine(result, it.next());
			}
			return result;
		}
		return null;
	}

	// 单一功能
	public static <R, T> List<R> transform(Iterable<T> seq, UnaryFunction<R, T> func) {
		List<R> result = new ArrayList<R>();
		for (T t : seq) {
			result.add(func.function(t));
		}
		return result;
	}

	public static <T> Collector<T> forEach(Iterable<T> seq, Collector<T> func) {
		for (T t : seq) {
			func.function(t);
		}
		return func;
	}

	public static <T> List<T> filter(Iterable<T> seq, UnaryPredicate<T> pred) {
		List<T> result = new ArrayList<T>();
		for (T t : seq) {
			if (pred.test(t)) {
				result.add(t);
			}
		}
		return result;
	}
}

// 功能定义
interface Combiner<T> {
	T combine(T x, T y);
}

// 两个Integer类型的合并
class IntegerAdder implements Combiner<Integer> {
	public Integer combine(Integer x, Integer y) {
		return x + y;
	}
}

interface UnaryFunction<R, T> {
	R function(T x);
}

class BigDecimalUlp implements UnaryFunction<BigDecimal, BigDecimal> {
	public BigDecimal function(BigDecimal x) {
		return x.ulp();
	}
}

interface Collector<T> extends UnaryFunction<T, T> {
	T result();
}

class MultiplyingIntegerCollector implements Collector<Integer> {
	
	private Integer val = 1;

	@Override
	public Integer function(Integer x) {
		val *= x;
		return val;
	}

	@Override
	public Integer result() {
		return val;
	}
}

interface UnaryPredicate<T> {
	boolean test(T x);
}

class GreaterThan<T extends Comparable<T>> implements UnaryPredicate<T> {

	private T bound;

	public GreaterThan(T bound) {
		this.bound = bound;
	}

	@Override
	public boolean test(T x) {
		return x.compareTo(bound) > 0;
	}
}