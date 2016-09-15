package com.bdsoft.bdceo.j2se.thread;

public class DaemonThread {

	/**
	 * 守护进程Thread实例.setDaemon(true/false)
	 */
	public static void main(String[] args) {
		Thread t1 = new Thread(new Th1());
		Thread t2 = new Thread(new Th2());

		// 设置t1线程为守护进程，无法保证其完整执行
		t1.setDaemon(true);

		t1.start();
		t2.start();
	}

}

class Th1 implements Runnable {
	public void run() {
		for (int i = 0; i < 1000; i++) {
			System.out.print("【" + i + "】");
		}
		System.out.println("Daemon Thread Over.");
	}
}

class Th2 implements Runnable {
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.print("<" + i + ">");
		}
		System.out.println("Common Thread Over.");
	}
}
