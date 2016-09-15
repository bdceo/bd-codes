package com.bdsoft.bdceo.j2se.thread.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLongArray;

public class AtomicTest {

	// 原子变量
	// 单变量线程安全的访问操作：java.util.concurrent.atomic
	public static void main(String[] args) {
		long[] data = new long[] { 1, 22, 333, 4444, 55555, 0, 1, 22, 333 };
		AtomicLongArray ala = new AtomicLongArray(data);

		ExecutorService pool = Executors.newFixedThreadPool(3);

		for (int i = 0; i < 3; i++) {
			pool.execute(new Ato(ala));
		}

		pool.shutdown();

		for (int i = 0; i < ala.length(); i++) {
			System.out.print(ala.get(i) + " ");
		}
	}

}

// 线程安全的单变量操作
class Ato implements Runnable {
	// 原子操作变量引用
	private AtomicLongArray aa;

	public Ato(AtomicLongArray a) {
		this.aa = a;
	}

	public void run() {
		aa.set(4, 13);

		long ld = aa.addAndGet(3, 4);

		aa.set(5, ld);
	}
}
