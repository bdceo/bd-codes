/**
 * ConcurrentHashMapTest.java
 * com.bdsoft.bdceo.j2se.thread.concurrent
 * Copyright (c) 2014, 北京微课创景教育科技有限公司版权所有.
*/
package com.bdsoft.bdceo.j2se.thread.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 并发容器测试
 * 
 * @author	丁辰叶
 * @date	2015-10-9
 */
public class ConcurrentHashMapTest {

	public static void main(String[] args) {
		// 普通hashmap的并发问题测试
		testHashMapMutiThread();
		
		/**
		 * 锁分段锁：将数据分成一段一段的，每一段数据分配一把锁
		 * 			当一个线程访问其中一段数据时，其他段数据也能被其他线程访问
		 * 
		 */
		// ConcurrentHashMap：锁
	}
	
	static void testConcurrentHashMap(){
		final Map<String, String> map = new ConcurrentHashMap<String, String>();
		
	}

	static void testHashMapMutiThread() {
		final Map<String, String> map = new HashMap<String, String>(2);

		Thread t = new Thread(new Runnable() {
			public void run() {
				int subThread = 10000;
				for (int i = 0; i < subThread; i++) {

					new Thread(new Runnable() {
						public void run() {
							map.put(UUID.randomUUID().toString(), "");
						}
					}, "ftf" + i).start();

				}
			}
		}, "ftf");

		t.start();
		try {
			t.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
