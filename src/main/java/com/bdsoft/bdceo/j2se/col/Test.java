package com.bdsoft.bdceo.j2se.col;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Test implements Comparable {

	public String name;
	public int id;
	public int age;

	public Test() {
	}

	public Test(String n, int i) {
		this.name = n;
		this.id = i;
	}

	public Test(String n, int i, int a) {
		this.name = n;
		this.id = i;
		this.age = a;
	}

	public static void main(String[] args) {

		// Map 的 遍历
		Map<String, String> map = new HashMap<String, String>();
		map.put("luckNum", "4");
		map.put("nickName", "bdceo");
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " - " + entry.getValue());
		}

		// 将数组转换成列表
		String[] strs = new String[] { "bdceo", "bdcoo", "bdcfo" };
		List list = Arrays.asList(strs);
		System.out.println(list);

		// 无序
		Set set = new HashSet();
		set.add(String.valueOf(1));
		set.add(null);
		set.add(String.valueOf(3));
		set.add(new Test("ceo", 4));
		set.add(String.valueOf(1));
		set.add(new Test("ceo", 4));
		System.out.println(set);

		// 有序的Set - SortedSet - TreeSet
		TreeSet treeSet = new TreeSet();
		treeSet.add("1");
		treeSet.add("3");
		treeSet.add("9");
		treeSet.add("5");
		treeSet.add("7");
		treeSet.remove("5");
		treeSet.add("6");
		System.out.println(treeSet);

		// 指定默认排序和指定比较器
		TreeSet<Test> s1 = new TreeSet<Test>();
		TreeSet<Test> s2 = new TreeSet<Test>(new AgeCompare());
		s1.add(new Test("ceo", 1, 24));
		s1.add(new Test("coo", 2, 20));
		s1.add(new Test("cfo", 3, 31));
		System.out.println(s1);
		s2.add(new Test("bdceo", 1, 23));
		s2.add(new Test("bdcoo", 1, 13));
		s2.add(new Test("bdcfo", 1, 33));
		System.out.println(s2);
	}

	// 重写equals和hashCode方法示例
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (!(o instanceof Test)) {
			return false;
		}
		Test t = (Test) o;
		if (this.id == t.id && this.name.equals(t.name)) {
			return true;
		} else {
			return false;
		}
	}

	public int hashCode() {
		return 3 * this.name.hashCode() + 7 * Integer.valueOf(id).hashCode();
	}

	public int compareTo(Object o) {
		Test t = (Test) o;
		return this.id - t.id;
	}

	public String toString() {
		return id + "->" + name + "@" + age;
	}
}

class AgeCompare implements Comparator {
	public int compare(Object o1, Object o2) {
		Test t1 = (Test) o1;
		Test t2 = (Test) o2;
		return t1.age - t2.age;
	}
}
