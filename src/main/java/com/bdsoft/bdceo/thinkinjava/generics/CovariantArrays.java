/**
 * CovariantArrays.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 泛型——通配符
 * 
 * 数组：向导出类型的数组赋予基类型的数组引用
 * 
 * @author bdceo
 * @date 2016-12-26 上午10:53:20
 * @version V1.0
 */
public class CovariantArrays {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 15.10——通配符
		Fruit ana = new Apple();
		// 数组中支持存放Apple及其子类
		Fruit[] fruit = new Apple[10];
		fruit[0] = new Apple();
		fruit[1] = new Jonthan();
		// 编译没问题，运行时异常
		// fruit[0] = new Fruit(); // java.lang.ArrayStoreException:Fruit
		// fruit[0] = new Orange(); // java.lang.ArrayStoreException:Orange

		// 容器泛型，容器的类型问题：Fruit的List允许放任何Fruit及其子类型包括Apple，但是Apple的List只允许放Apple及其子类型
		// List<Fruit> flist = new ArrayList<Apple>();
		List<? extends Fruit> flist = new ArrayList<Apple>();
		// flist.add(new Apple()); // 编译失败：通配符引用的是明确的类型，但此时不知道List持有什么类型，没法安全的向其添加元素
		// flist.add(new Orange());// 编译失败
		// flist.add(new Object());// 编译失败
		flist.add(null);
		ana = flist.get(0);

		// 15.10.1——编译器有多聪明
		flist = Arrays.asList(new Apple()); // 初始化一个元素
		Apple ap = (Apple) flist.get(0); // 返回 [? extends fruit]，需要强转
		flist.contains(new Apple()); // 接受参数：object
		flist.indexOf(new Apple());// 接受参数：object

		Holder<Apple> apple = new Holder<Apple>(new Apple());
		Apple apa = apple.get();
		apple.set(apa);

		// Holder<Fruit> fruith = apple; // 不能向上转型
		Holder<? extends Fruit> fruith = apple;// 转型成功

		Fruit f = fruith.get();
		apa = (Apple) fruith.get();
		// Orange org = (Orange) fruith.get();// java.lang.ClassCastException: 不能将apple转换成orange

		// fruith.set(new Apple()); // 编译失败，无法调用set：只接受 [? extends fruit]
		// fruith.set(new Fruit()); //

		System.out.println(fruith.equals(apa));// 接受object类型参数运算

		// 15.10.2——逆变：超类型通配符：[? super Type]
		List<Apple> aps = new ArrayList<Apple>();
		List<Fruit> fts = new ArrayList<Fruit>();
		writeTo(aps);

		writeExact(aps, new Apple());
		writeExact(fts, new Apple());// p393,示例中提示会出错，实际在jdk7环境编译运行都没问题？
		writeWithWildcard(aps, new Apple());
		writeWithWildcard(fts, new Apple());

		aps = Arrays.asList(new Apple());
		fts = Arrays.asList(new Fruit());
		// 精确类型读取
		Apple a1 = readExact(aps);
		Fruit f1 = readExact(fts);
		f1 = readExact(aps);

		// 通过泛型类读取：只接受Fruit的list
		Reader<Fruit> freader = new Reader<Fruit>();
		f1 = freader.readExact(fts);
		// f1 = freader.readExact(aps); 编译失败：只接受List<Fruit>类型参数

		// 通过带边界的泛型类读取
		CovariantReader<Fruit> cfreader = new CovariantReader<Fruit>();
		f1 = cfreader.readCovariant(fts);
		f1 = cfreader.readCovariant(aps);// 编译运行都成功

		Holder hd = new Holder();
		hd.set(new Apple());
		hd.get();
		Holder<?> hd2 = new Holder<Long>();
//		hd2.set(new Long(1));
		hd2.get();
	}

	static <T> T readExact(List<T> list) {
		return list.get(0);
	}

	static class Reader<T> {
		T readExact(List<T> list) {
			return list.get(0);
		}
	}

	static class CovariantReader<T> {
		T readCovariant(List<? extends T> list) {
			return list.get(0);
		}
	}

	static <T> void writeExact(List<T> list, T item) {
		list.add(item);
	}

	static <T> void writeWithWildcard(List<? super T> list, T item) {
		list.add(item);
	}

	static void writeTo(List<? super Apple> apples) {
		apples.add(new Apple());
		apples.add(new Jonthan());
		// apples.add(new Fruit()); // 编译失败：只接受apple及其子类型
	}

}

// 泛型类
class Holder<T> {
	private T value;

	public Holder() {
	}

	public Holder(T val) {
		value = val;
	}

	public void set(T val) {
		value = val;
	}

	public T get() {
		return value;
	}

	public boolean equals(Object obj) {
		return value.equals(obj);
	}
}

class Fruit {
}

class Apple extends Fruit {
}

class Jonthan extends Apple {
}

class Orange extends Fruit {
}
