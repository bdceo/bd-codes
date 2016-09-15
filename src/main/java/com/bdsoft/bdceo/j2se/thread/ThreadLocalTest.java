/**
 * ThreadLocalTest.java
 * com.bdsoft.bdceo.j2se.thread
 * Copyright (c) 2014, 北京微课创景教育科技有限公司版权所有.
*/
package com.bdsoft.bdceo.j2se.thread;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author	丁辰叶
 * @date	2015-10-8
 */
public class ThreadLocalTest {

	static ThreadLocal<HashMap> threadLocal = new ThreadLocal<HashMap>() {
		@Override
		protected HashMap initialValue() {
			System.out.println(Thread.currentThread().getName() + ">initialValue");
			return new HashMap();
		}
	};

	public static class T1 implements Runnable {

		private final static Map map = new HashMap();

		int id;

		public T1(int id) {
			this.id = id;
		}

		public void run() {
			Map map = threadLocal.get();
			for (int i = 0; i < 20; i++) {
				map.put(i, i + id * 100);
				try {
					Thread.sleep(100);
				} catch (Exception ex) {
				}
			}
			System.out.println(Thread.currentThread().getName() + "# map.size()=" + map.size() + " # " + map);
		}
	}

	public static void main(String[] args) {
		Thread[] runs = new Thread[20];
		T1 t = new T1(1);
		for (int i = 0; i < runs.length; i++) {
			runs[i] = new Thread(t);
		}
		for (int i = 0; i < runs.length; i++) {
			runs[i].start();
		}
	}
}
