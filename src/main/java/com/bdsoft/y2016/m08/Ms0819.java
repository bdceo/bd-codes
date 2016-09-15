/**
 * Ms0819.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.y2016.m08;

import java.util.Random;

/**
 * 0819面试测试：锁重入
 * 
 * @author bdceo
 * @date 2016-8-20 下午3:05:07
 * @version V1.0
 */
public class Ms0819 {

	public static Random rd = new Random(System.currentTimeMillis());

	/**
	 * 入口
	 */
	public static void main(String[] args) {
		// 测试锁的重入机制，以及递归调用时的重入
		ReinLock rl = new ReinLock();
		new Thread(new LockClient(rl)).start();
		new Thread(new LockClient(rl)).start();
		new Thread(new LockClient(rl)).start();

	}

}

class LockClient implements Runnable {

	private ReinLock rl;

	public LockClient(ReinLock rl) {
		this.rl = rl;
	}

	public void run() {
		rl.add(Ms0819.rd.nextInt(100));
	}
}

class ReinLock {

	volatile int lockCount = 0;

	public synchronized void add(int data) {
		lockCount++;
		String msg = String.format("线程=%s 进入ReinLock.add方法，参数=%d,锁计数=%d", Thread.currentThread()
				.getName(), data, lockCount);
		System.out.println(msg);
		if ((lockCount % 5) == 0) {
			System.out.println("计算结果=" + data);
			return;
		}
		sub(data + Ms0819.rd.nextInt(100));
	}

	public synchronized void sub(int data) {
		String msg = String.format("线程=%s 进入ReinLock.sub方法，参数=%d，锁计数=%d", Thread.currentThread()
				.getName(), data, lockCount);
		System.out.println(msg);
		add(data - Ms0819.rd.nextInt(100));
	}

}
