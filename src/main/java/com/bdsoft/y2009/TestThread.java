package com.bdsoft.y2009;

/**
 * 线程测试
 * 
 * @author bdceo
 * @date 2016-8-21 上午9:20:21
 */
public class TestThread {

	/**
	 * 入口
	 */
	public static void main(String[] args) {
		// 测试线程优先级
		testPriority();
	}

	static void testPriority() {
		Thread t1 = new Thread(new Imp1());
		Thread t2 = new Thread(new Imp2());
		t1.setPriority(Thread.MIN_PRIORITY);
		t2.setPriority(Thread.MAX_PRIORITY);
		t1.start();
		t2.start();
	}
}

class Imp1 implements Runnable {
	public void run() {
		for (int i = 1; i <= 150; i++) {
			if (i % 10 == 0) {
				System.out.println();
			}
			System.out.print("<" + i + ">");
		}
	}
}

class Imp2 implements Runnable {
	public void run() {
		for (int i = 1; i <= 150; i++) {
			if (i % 10 == 0) {
				System.out.println();
			}
			System.out.print("-" + i + "-");
		}
	}
}
