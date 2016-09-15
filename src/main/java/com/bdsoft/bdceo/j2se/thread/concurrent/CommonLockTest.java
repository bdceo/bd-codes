package com.bdsoft.bdceo.j2se.thread.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CommonLockTest {

	/**
	 * 普通资源封锁
	 */
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(2);

		// 多个用户，同时操作一个账户
		Account acc = new Account(50);
		// 多线程，同时持有同一个账户和同一个锁
		Lock lock = new ReentrantLock(true);

		User u1 = new User("ceo", -100, acc, lock);
		User u2 = new User("cfo", 100, acc, lock);

		pool.execute(u1);
		pool.execute(u2);
		pool.shutdown();
	}

}

class User implements Runnable {

	private Lock lock;
	private Account acc;
	private int take;
	private String name;

	public User(String name, int take, Account acc, Lock lock) {
		this.name = name;
		this.take = take;
		this.acc = acc;
		this.lock = lock;
	}

	public void run() {
		boolean flag = false;
		while (!flag) {
			try {
				// 获取锁
				lock.lock();
				int balance = this.acc.getBalance();
				if (take < 0) {// 取款
					if (balance + take > 0) {
						this.acc.setBalance(balance + take);
						flag = true;
						System.out.println(name + " take " + take
								+ ", balance is " + this.acc.getBalance());
					}
				} else {// 存款
					this.acc.setBalance(balance + take);
					flag = true;
					System.out.println(name + " save " + take + ", balance is "
							+ this.acc.getBalance());
				}
			} finally {
				// 释放所
				lock.unlock();
			}
			if (flag) {
				break;
			}
			System.out.println(this.name + ",balance is not enough for take");
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

class Account {

	private int balance;

	public Account(int blan) {
		this.balance = blan;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
}
