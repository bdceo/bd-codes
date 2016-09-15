package com.bdsoft.bdceo.j2se.thread.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {

	// 读写资源封锁
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(4);

		// 多线程，持有同一资源引用,实例化资源，资源类拥有读写锁控制实例
		Resource res = new Resource();

		Use u1 = new Use("ceo", 0, "", res);
		Use u2 = new Use("cto", 0, "", res);
		Use u3 = new Use("cfo", 1, "destory", res);

		pool.execute(u1);
		pool.execute(u2);
		pool.execute(u3);
		pool.shutdown();
	}

}

// 读写锁，线程操作资源
class Use implements Runnable {
	
	private String name;
	private int code;
	private String msg;
	private Resource res;

	public Use(String n, int c, String m, Resource r) {
		this.name = n;
		this.code = c;
		this.msg = m;
		this.res = r;
	}

	public void run() {
		if (this.code == 0) {
			try {
				// 获取读锁
				this.res.getRwLock().readLock().lock();
				System.out.println(name + " read " + res.getMsg());
				Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// 释放锁
				this.res.getRwLock().readLock().unlock();
			}
		} else {
			try {
				// 获取写锁
				this.res.getRwLock().writeLock().lock();
				this.res.setMsg(this.msg);
				System.out.println(name + " write " + res.getMsg());
				Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// 释放锁
				this.res.getRwLock().writeLock().unlock();
			}
		}
	}

}

// 读写锁，持有读写锁实例引用
class Resource {

	private String msg = "init";
	private ReadWriteLock rwLock = new ReentrantReadWriteLock(true);

	public Resource() {
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ReadWriteLock getRwLock() {
		return rwLock;
	}

	public void setRwLock(ReadWriteLock rwLock) {
		this.rwLock = rwLock;
	}

}