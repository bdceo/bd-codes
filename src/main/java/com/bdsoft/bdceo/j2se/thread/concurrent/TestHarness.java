package com.bdsoft.bdceo.j2se.thread.concurrent;

import java.util.concurrent.CountDownLatch;

// 闭锁测试
public class TestHarness {

	public long timeTasks(int nThread, final Runnable task) throws Exception {
		final CountDownLatch startGate = new CountDownLatch(1);
		final CountDownLatch endGate = new CountDownLatch(nThread);

		for (int i = 0; i < nThread; i++) {
			Thread t = new Thread() {
				public void run() {
					try {
						startGate.await();
						try {
							task.run();
						} finally {
							endGate.countDown();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			t.start();
		}

		long start = System.nanoTime();
		startGate.countDown();
		endGate.await();
		long end = System.nanoTime();
		return end - start;
	}

}
