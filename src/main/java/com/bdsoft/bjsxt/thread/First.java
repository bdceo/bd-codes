package com.bdsoft.bjsxt.thread;

import java.util.Date;

public class First {

	/**
	 * 线程就是一个程序里的不同执行路径
	 */
	public static void main(String[] args) {
		Runner1 rn = new Runner1();
		Thread td = new Thread(rn);
		td.start();
		
		Runner2 rn2 = new Runner2();
		rn2.start();
		
		for (int i = 0; i < 100; i++) {
			System.out.println("mainThread_" + i);
		}

		MyThread th = new MyThread();
		th.start();
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		th.shutdown();
		
		// th.interrupt();
	}
}

class Runner1 implements Runnable {

	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("Runner1_" + i);
		}
	}
}

class Runner2 extends Thread {

	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("Runner2_" + i);
		}
	}
}

class MyThread extends Thread {
	private boolean flag = true;

	public void run() {
		while (flag) {
			System.out.println("sssssss" + new Date() + "ssssssss");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				flag = false;
			}
		}
	}

	public void shutdown() {
		flag = false;
		System.out.println("线程已结束！");
	}
}