package com.bdsoft.bdceo.j2se.thread.concurrent;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BarrierTest {

	// 障碍器测试
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(10);

		int size = 4;
		CyclicBarrier cb = new CyclicBarrier(size, new FinTask());

		for (int i = 0; i < size; i++) {
			pool.execute(new SubTask("T-" + i, 1000, cb));
		}

		pool.shutdown();
	}

}

// 子任务
class SubTask implements Runnable {

	private CyclicBarrier cb;
	private String tname;
	private long tdur;

	public SubTask(String n, long t, CyclicBarrier c) {
		this.cb = c;
		this.tname = n;
		this.tdur = t;
	}

	public void run() {
		try {
			System.out.println("task " + tname + " execute");
			Thread.sleep(tdur);
			System.out.println("task " + tname + " finish");
			// 通知障碍器，计数
			cb.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

// 最终要执行的任务
class FinTask implements Runnable {

	public void run() {
		System.out.println("\t all task finished!");
	}
}