package com.bdsoft.bjsxt.thread;

public class SyncTest implements Runnable {

	Timmer tm = new Timmer();

	public void run() {
		tm.add(Thread.currentThread().getName());
	}

	public static void main(String[] args) {
		SyncTest st = new SyncTest();
		Thread t1 = new Thread(st);
		t1.setName("a");

		Thread t2 = new Thread(st);
		t2.setName("b");

		t1.start();
		t2.start();
	}
}

class Timmer {
	
	private static int num = 0;

	public synchronized void add(String name) {
		// 互斥锁：
		// synchronized (this) {
		num++;
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(name + ": 你是第" + num + "个线程！");
		// }
	}
	
}
