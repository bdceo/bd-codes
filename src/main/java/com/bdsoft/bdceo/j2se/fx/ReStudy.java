/**
 * ReStudy.java
 * com.bdsoft.bdceo.j2se.fx
 * Copyright (c) 2016, 北京微课创景教育科技有限公司版权所有.
*/

package com.bdsoft.bdceo.j2se.fx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * 泛型复习
 * 参考：https://segmentfault.com/a/1190000002646193
 * 		 http://www.infoq.com/cn/articles/cf-java-generics
 *
 * @author   丁辰叶
 * @date	 2016-6-13
 * @version  1.0.0
 */
public class ReStudy<S> {

	// 泛型不可用于静态变量
	//	static S vr;

	public static void main(String[] args) {
		// 泛型类
		Container<String, String> name = new Container<String, String>("老丁", "bdceo");
		System.out.println(name.getKey());
		System.out.println(name.getValue());
		Container<String, Integer> people = new Container<String, Integer>("老丁", 28);
		System.out.println(people.getKey());
		System.out.println(people.getValue());

		// 泛型方法
		print(new Date().toLocaleString());
		print(20160613);
		print(13.58);

		// 泛型--强转
		Collection<Long> list = Lists.newArrayList();
		mergecol(list);
		List<Integer> list2 = Lists.newArrayList();
		// 不接受String的集合，存在隐性类型转换
		mergecol(list2);
		// List<String> 是 List<?> 的子类型，所以支持调用
		mergecol2(list2);

		// 泛型类型不允许实例化
		//		S s = new S();

		Object[] ia = new Integer[3];
		// 泛型类型数组无法实例化
		List<String>[] lsa = null;//  new List<String>[3];
		// 泛型类型为通配符，可以实例化
		List<?>[] lsa2 = new ArrayList<?>[3];
	}

	public static void mergecol(Collection<? extends Number> os) {

	}

	public static void mergecol2(List<?> os) {

	}

	/**
	 * 泛型通配符，及边界
	 */
	public static void wildcard(List<?> c1, List<? extends Number> c2, List<? super Number> c3) {
		// 编译错误：对带有通配符的泛型类进行操作时，编译都会出错
		//		c1.add("22");
	}

	/**
	 * 泛型方法：无论何时，只要你能做到，你就应该尽量使用泛型方法
	 */
	public static <T> void print(T info) {
		System.out.println(info);
	}

}

class StringCompare implements Comparable<String> {

	@Override
	public int compareTo(String o) {
		return 0;
	}

}

/**
 * 泛型接口机器实现类：用于定义生成器
 */
interface Generator<T> {
	public T getObject();
}

class XxFactoryBean implements Generator<ReStudy> {

	public ReStudy getObject() {
		return new ReStudy();
	}
}

/**
 * 泛型类：有许多原因促成了泛型的出现，而最引人注意的一个原因，就是为了创建容器类
 */
class Container<K, V> {
	private K key;
	private V value;

	public Container(K k, V v) {
		this.key = k;
		this.value = v;
	}

	public K getKey() {
		return this.key;
	}

	public V getValue() {
		return this.value;
	}
}
