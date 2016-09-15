package com.bdsoft.bdceo.thinkinjava.container;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

/**
 * 容器类测试
 */
public class ContainerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Collection<String> datas = Arrays.asList("bdceo", "google", "sina",
				"douban");

		// Collection 集合的顶层接口，直接继承有：List，Set，Queue
		Collection<String> col = new ArrayList<String>();

		// Map 是键值对类型容器的顶层接口，直接继承有：HashMap，HashTable
		Map<String, String> map = new HashMap<String, String>();

		// 已过时的接口，不再推荐程序中使用：Vector，HashTable，Stack

		List<String> lt = new ArrayList<String>();
		lt = new LinkedList<String>();
		lt = Arrays.asList("aa");
		lt = Arrays.<String> asList("bb");

		// 迭代器，单向只能向后遍历元素
		Iterator<String> ite = lt.iterator();
		// 双向迭代
		ListIterator<String> lite = lt.listIterator();

		// 通过栈的后进先出特性，模拟实现字符串反转功能
		String str = "123456789abcdefghijklmn"; // new StringBuffer().reverse();
		Stack<String> stc = new Stack<String>();
		for (char c : str.toCharArray()) {
			stc.push("" + c);
		}
		while (!stc.empty()) {
			System.out.print(stc.pop());
		}
		// 右移操作
		int len = str.length();
		System.out.println(String
				.format("\nlen=%d, 右移=%d", len, (len - 1) >> 1));

		// 新建随机数时，指定一个随机因子，减少系统调用
		new Random(33);

		String s1 = "bdceo";
		String s2 = "bdceo";
		String s3 = new String("bdceo");
		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
		System.out.println(s3.hashCode());
		System.out.println(s1 == s2);
		System.out.println(s1 == s3);
		System.out.println(s1 == s3.intern());

	}

	public static Collection<String> init(Collection<String> col) {
		col.add("bdsoft");
		col.add("google");
		col.add("douban");
		col.add("alibaba");
		col.add("jd");
		col.add("apple");
		return col;
	}

}
