package com.bdsoft.bdceo.j2se.col;

import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

public class MapTest {

	public static void main(String[] args) {
		// 20140218:有序的map测试
		// SortedMap 是接口，定义关于键的排序
		// TreeMap，直接实现

		SortedMap<String, Integer> map = null;

		// 基本使用
		map = new TreeMap<String, Integer>();
		map.put("bdceo", 404);
		map.put("bdcto", 200);
		map.put("bdcoo", 500);
		map.put("bdcfo", 304);
		map.put("bdcmo", 550);
		System.out.println("firstKey = " + map.firstKey());
		System.out.println("lastKey = " + map.lastKey());

		/*
		 * 输出： bdceo >> 404 bdcfo >> 304 bdcmo >> 550 bdcoo >> 500 bdcto >> 200
		 */
		for (Entry<String, Integer> en : map.entrySet()) {
			System.out.println(en.getKey() + " >> " + en.getValue());
		}
		System.out.println("-------------------------------------");

		SortedMap<String, Integer> tmp = null;

		// 测试方法：headMap(key),返回小于指定键值的键值对集合
		String key = "bdcmo";
		tmp = map.headMap(key);
		/*
		 * 输出： bdceo >> 404 bdcfo >> 304
		 */
		System.out.println(String.format("map.headMap(%s)", key));
		for (Entry<String, Integer> en : tmp.entrySet()) {
			System.out.println(en.getKey() + " >> " + en.getValue());
		}
		System.out.println("-------------------------------------");

		// 测试方法：tailMap(key),返回大于指定键值的键值对集合
		tmp = map.tailMap(key);
		/*
		 * 输出： bdcmo >> 550 bdcoo >> 500 bdcto >> 200
		 */
		System.out.println(String.format("map.tailMap(%s)", key));
		for (Entry<String, Integer> en : tmp.entrySet()) {
			System.out.println(en.getKey() + " >> " + en.getValue());
		}
		System.out.println("-------------------------------------");

		// 测试方法：subMap(from,to),返回指定键值取件的键值对集合，不包含to指定的可以
		String fKey = "bdcfo", tKey = "bdcoo";
		tmp = map.subMap(fKey, tKey);
		/*
		 * 输出： bdcfo >> 304 bdcmo >> 550
		 */
		System.out.println(String.format("map.subMap(%s, %s)", fKey, tKey));
		for (Entry<String, Integer> en : tmp.entrySet()) {
			System.out.println(en.getKey() + " >> " + en.getValue());
		}

	}

}
