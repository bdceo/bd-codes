package com.bdsoft.bdceo.thinkinjava.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * volatile 属性标识的属性，进行递增操作时，也需要加锁进行强制同步
 */
public class SerialNumberTest {

	// 并发线程数
	private static final int SIZE = 10;
	// 初始化序列号数组
	private static CircularSet serials = new CircularSet(10);

	/**
	 * 入口
	 */
	public static void main(String[] args) {
		ExecutorService es = Executors.newCachedThreadPool();
		for (int i = 0; i < SIZE; i++) {
			es.execute(new SerialNumberChecker(serials));
		}
		es.shutdown();
	}

}

/**
 * 检测序列号是否重复
 */
class SerialNumberChecker implements Runnable {
	private CircularSet serials;

	public SerialNumberChecker(CircularSet se) {
		serials = se;
	}

	public void run() {
		while (true) {
			// 生成序列号，比较
			int val = SerialNumberGenrator.next();
			if (serials.contains(val)) {
				System.out.println("发现重复数值：" + val);
				System.exit(0);
			}
			// 未发现重复，覆盖序列号
			serials.add(val);
		}
	}
}

/**
 * 可循环利用的数组，线程安全的
 */
class CircularSet {
	private int[] array;
	private int len;
	private int index = 0;

	public CircularSet(int size) {
		array = new int[size];
		len = size;
		for (int i = 0; i < size; i++) {
			array[i] = -1;
		}
	}

	public synchronized void add(int i) {
		array[index] = i;
		index = ++index % len;
	}

	public synchronized boolean contains(int val) {
		for (int i = 0; i < len; i++) {
			if (array[i] == val) {
				return true;
			}
		}
		return false;
	}
}

/**
 * 产生递增的序列号：即使volatile修饰的属性，在其进行递增过程中，也需要加锁保证安全
 */
class SerialNumberGenrator {

	private static volatile int serialNumber = 0;

	public static int next() {
		// public synchronized static int next() {
		return serialNumber++;
	}
}
