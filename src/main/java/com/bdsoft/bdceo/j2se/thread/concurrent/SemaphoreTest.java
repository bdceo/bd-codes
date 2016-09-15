package com.bdsoft.bdceo.j2se.thread.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

	/**
	 * 信号量测试
	 */
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(4);

		// 多个线程，同时引用一个资源，资源中有信号量的实例
		SeResource res = new SeResource(10);

		SeUse u1 = new SeUse("ceo", 6, res);
		SeUse u2 = new SeUse("cto", 5, res);
		SeUse u3 = new SeUse("cfo", 7, res);

		pool.execute(u1);
		pool.execute(u2);
		pool.execute(u3);
		pool.shutdown();
	}

}

// 信号量资源
class SeUse implements Runnable {
	
	private String name;
	private SeResource ser;
	private int take;

	public SeUse(String n, int t, SeResource s) {
		this.name = n;
		this.ser = s;
		this.take = t;
	}

	public void run() {
		try {
			// 申请资源
			ser.getSe().acquire(this.take);
			System.out.println(name + " take " + take);
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			ser.getSe().release(this.take);
			System.out.println(name + " back " + take);
		}
	}
}

// 资源类，持有信号量引用
class SeResource {
	private Semaphore se;

	public SeResource(int count) {
		this.se = new Semaphore(count, true);
	}

	public Semaphore getSe() {
		return se;
	}

	public void setSe(Semaphore se) {
		this.se = se;
	}

}
