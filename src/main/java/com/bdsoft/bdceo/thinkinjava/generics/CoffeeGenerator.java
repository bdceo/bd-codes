/**
 * CoffeeGenerator.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 泛型生成器，可迭代，泛型方法，
 * 
 * @author bdceo
 * @date 2016-8-18 上午9:13:26
 * @version V1.0
 */
public class CoffeeGenerator implements Generator<Coffee>, Iterable<Coffee> {

	private static Random rd = new Random(System.currentTimeMillis());

	private Class[] types = { Latte.class, Cappuccino.class, Mocha.class, Americano.class,
			Breve.class };
	private int len = types.length;

	private int size;

	public CoffeeGenerator() {
	}

	public CoffeeGenerator(int sz) {
		this.size = sz;
	}

	public Coffee next() {
		try {
			return (Coffee) (types[rd.nextInt(len)].newInstance());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Iterator<Coffee> iterator() {
		return new CoffeeIterator();
	}

	// 迭代器
	class CoffeeIterator implements Iterator<Coffee> {
		int count = size;

		@Override
		public boolean hasNext() {
			return count > 0;
		}

		@Override
		public Coffee next() {
			count--;
			return CoffeeGenerator.this.next();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	/**
	 * 入口
	 */
	public static void main(String[] args) {
		CoffeeGenerator cg = new CoffeeGenerator();
		for (int i = 0; i < 5; i++) {
			System.out.println(cg.next());
		}

		System.out.println("-----------------");
		for (Coffee cf : new CoffeeGenerator(5)) {
			System.out.println(cf);
		}

		System.out.println("-----------------");
		Fibonacci fib = new Fibonacci();
		for (int i = 0; i < 20; i++) {
			System.out.print(String.format("%d ", fib.next()));
		}
		System.out.println("\n-----------------");
		for (int i : new Fibonacci(20)) {
			System.out.print(String.format("%d ", i));
		}

		System.out.println("\n-----------------");
		print(1);
		print("1");
		print(true);
		print('1');
		print(1.0);

		System.out.println("方法的类型参数推断-----------------");
		// 通过方法的类型参数推断，避免新建泛型类型时的冗余代码
		List<String> strList = BDCollections.list();
		strList.add("bdceo");
		System.out.println(strList.get(0));
		Map<String, Integer> map = BDCollections.map();
		map.put("age", 28);
		System.out.println(map.get("age"));
		// 复杂参数类型推断
		Map<String, List<? extends Coffee>> difMap = BDCollections.map();
		print(difMap);
		print(BDCollections.map());

		System.out.println("泛型方法+可变参数-----------------");
		// 泛型方法+可变参数
		List<String> pl = makeList("bdceo");
		System.out.println(pl);
		pl = makeList("bdceo", "bdcfo");
		System.out.println(pl);

		System.out.println("泛型生成器-----------------");
		// 泛型生成器
		Collection<Coffee> cfList = Generators.fill(new ArrayList<Coffee>(), new CoffeeGenerator(),
				5);
		System.out.println(cfList);
		Collection<Integer> itList = Generators.fill(new ArrayList<Integer>(), new Fibonacci(20),
				20);
		System.out.println(itList);

		System.out.println("通用生成器-----------------");
		// 通用生成器
		// Generator<CountedObject> bgen = BasicGenerator.create(CountedObject.class);
		Generator<Coffee> bgen = BasicGenerator.create(Coffee.class);
		for (int i = 0; i < 5; i++) {
			System.out.println(bgen.next());
		}
		System.out.println("匿名内部类-----------------");
		// 匿名内部类
		Collection<Customer> clist = Generators.fill(new ArrayList<Customer>(),
				Customer.generator(), 5);
		System.out.println(clist);
	}

	// 泛型方法
	static <P> void print(P param) {
		System.out.println(param.getClass().getName());
	}

	// 泛型+可变参数
	static <T> List<T> makeList(T... params) {
		List<T> plist = BDCollections.list();
		for (T p : params) {
			plist.add(p);
		}
		return plist;
	}

}

// 匿名内部类使用泛型
class Customer {
	private static long count = 0;
	private final long id = count++;

	public Customer() {
	}

	public String toString() {
		return String.format("Customer %d", id);
	}

	// 匿名内部类
	public static Generator<Customer> generator() {
		return new Generator<Customer>() {
			public Customer next() {
				return new Customer();
			}
		};
	}
}

// 基本的泛型生成器
class BasicGenerator<T> implements Generator<T> {
	private Class<T> type;

	public BasicGenerator(Class<T> type) {
		this.type = type;
	}

	public static <T> BasicGenerator<T> create(Class<T> type) {
		return new BasicGenerator<T>(type);
	}

	public T next() {
		try {
			return this.type.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

class CountedObject {
	private static long count = 0;
	private final long id = count++;

	public long id() {
		return id;
	}

	public String toString() {
		return String.format("CountedObject %d", id);
	}

}

// 斐波那契数列生成器
class Fibonacci implements Generator<Integer>, Iterable<Integer> {

	private int count = 0;
	private int size = 0;

	public Fibonacci() {
	}

	public Fibonacci(int n) {
		this.size = n;
	}

	@Override
	public Integer next() {
		return fib(count++);
	}

	private int fib(int n) {
		if (n < 2) {
			return 1;
		}
		return fib(n - 2) + fib(n - 1);
	}

	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {

			@Override
			public boolean hasNext() {
				return size > 0;
			}

			@Override
			public Integer next() {
				size--;
				return Fibonacci.this.next();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}

		};
	}

}

class Coffee {
	private static int counter = 0;
	private final int id = counter++;

	public String toString() {
		return String.format("%s %d", this.getClass().getSimpleName(), id);
	}
}

class Latte extends Coffee {
}

class Cappuccino extends Coffee {
}

class Mocha extends Coffee {
}

class Americano extends Coffee {
}

class Breve extends Coffee {
}
