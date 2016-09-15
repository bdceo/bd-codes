package com.bdsoft.bdceo.j2se.thread.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class SafeFastMemoizer {

}

interface Computable<A, V> {
	// 耗时很长的算法!!!!
	V compute(A arg) throws Exception;
}

// 线程安全，并发性低
class Memoizer1<A, V> implements Computable<A, V> {
	private final Map<A, V> cache = new HashMap<A, V>();
	private final Computable<A, V> com;

	public Memoizer1(Computable<A, V> com) {
		this.com = com;
	}

	public synchronized V compute(A arg) throws Exception {
		V result = cache.get(arg);
		if (result == null) {
			result = com.compute(arg);
			cache.put(arg, result);
		}
		return result;
	}
}

// 线程安全，无法避免同时两个线程都在执行耗时运算
class Memoizer2<A, V> implements Computable<A, V> {
	private final Map<A, V> cache = new ConcurrentHashMap<A, V>();
	private final Computable<A, V> com;

	public Memoizer2(Computable<A, V> com) {
		this.com = com;
	}

	public V compute(A arg) throws Exception {
		V result = cache.get(arg);
		if (result == null) {
			result = com.compute(arg);
			cache.put(arg, result);
		}
		return result;
	}
}

// 线程安全，并发性高：无法解决同时两个线程的执行耗时操作情况,复合操作
class Memoizer3<A, V> implements Computable<A, V> {
	private final Map<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
	private final Computable<A, V> com;

	public Memoizer3(Computable<A, V> com) {
		this.com = com;
	}

	public V compute(final A arg) throws Exception {
		Future<V> f = cache.get(arg);
		if (f == null) {
			Callable<V> eval = new Callable<V>() {
				public V call() throws Exception {
					return com.compute(arg);
				}
			};
			FutureTask<V> ft = new FutureTask<V>(eval);
			f = ft;
			cache.put(arg, ft);
			ft.run();// 执行耗时算法
		}
		try {
			return f.get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

// 完美实现
class Memoizer<A, V> implements Computable<A, V> {
	private final ConcurrentHashMap<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
	private final Computable<A, V> com;

	public Memoizer(Computable<A, V> com) {
		this.com = com;
	}

	public V compute(final A arg) throws Exception {
		while (true) {
			Future<V> f = cache.get(arg);
			if (f == null) {
				Callable<V> eval = new Callable<V>() {
					public V call() throws Exception {
						return com.compute(arg);
					}
				};
				FutureTask<V> ft = new FutureTask<V>(eval);
				f = cache.putIfAbsent(arg, ft);
				if (f == null) {
					f = ft;
					ft.run();
				}
			}
			try {
				return f.get();
			} catch (CancellationException ce) {
				// 取消计算时，移除
				cache.remove(arg, f);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}
}
