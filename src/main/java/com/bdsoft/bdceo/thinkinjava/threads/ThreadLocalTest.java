package com.bdsoft.bdceo.thinkinjava.threads;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 测试：线程本地存储：ThreadLocal
 * 
 * @author 丁辰叶
 * 
 */
public class ThreadLocalTest {

	// 一般为静态类型
	static ThreadLocal<Integer> local = new ThreadLocal<Integer>() {
		private Random rn = new Random(System.currentTimeMillis() % 4);

		@Override
		protected synchronized Integer initialValue() {
			return rn.nextInt(100);
		}
	};

	// 方法未同步，由ThreadLocal保证安全性
	static void increment() {
		local.set(local.get() + 1);
	}

	// 方法未同步，由ThreadLocal保证安全性
	static Integer get() {
		return local.get();
	}

	/**
	 * 入口
	 */
	public static void main(String[] args) throws Exception {
		ExecutorService es = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			es.execute(new Accessor(i));
		}
		es.shutdown();

		TimeUnit.MILLISECONDS.sleep(100);
		System.exit(0);
	}

}

class Accessor implements Runnable {

	private final int id;

	public Accessor(int idn) {
		id = idn;
	}

	public void run() {
		while (true) {
			ThreadLocalTest.increment();
			System.out.println(this);
			Thread.yield();
		}
	}

	public String toString() {
		return String.format("线程#%d 当前值=%d", id, ThreadLocalTest.get());
	}
}
