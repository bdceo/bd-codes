package com.bdsoft.bdceo.j2se.thread.concurrent;

import java.util.concurrent.atomic.AtomicLong;

public class ConcurrentBaseTest {

	private static AtomicLong atLong = new AtomicLong(4);

	/**
	 * 多线程，并发基础概念测试
	 */
	public static void main(String[] args) {
		safeThread();
	}

	public static void safeThread() {
		long c = atLong.incrementAndGet();
		System.out.println(c);
	}

}
