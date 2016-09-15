package com.bdsoft.bdceo.thinkinjava.threads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试目的：<br>
 * 
 * 1，同步代码块，同步方法的执行效率 <br>
 * 2，一个非线程安全的类，通过额外的管理类，实现其安全
 */
public class SynchronizedSection {

	/**
	 * 入口
	 */
	public static void main(String[] args) throws Exception {

		// 共享资源，实现非线程安全类的管理代理
		PairManager pm1 = new PairManager1();
		PairManager pm2 = new PairManager2();

		// 两个线程共享同一个资源管理器，模拟资源竞争，锁状态
		PairManipulator tpm1 = new PairManipulator(pm1);
		PairChecker tpc1 = new PairChecker(pm1);

		PairManipulator tpm2 = new PairManipulator(pm2);
		PairChecker tpc2 = new PairChecker(pm2);

		ExecutorService es = Executors.newCachedThreadPool();
		es.execute(tpm1);
		es.execute(tpm2);
		es.execute(tpc1);
		es.execute(tpc2);
		es.shutdown();

		TimeUnit.SECONDS.sleep(1);
		System.out.println(String.format("tpm1=%s \ntpm2=%s", tpm1, tpm2));
		System.exit(0);
	}

}

/**
 * 测试同步代码块及同步方法
 */
class PairManipulator implements Runnable {
	private PairManager pm;

	public PairManipulator(PairManager pm) {
		this.pm = pm;
	}

	public void run() {
		while (true) {
			pm.increment();
		}
	}

	public String toString() {
		return String.format("Pair=%s, checkCount=%d", pm.getPair(),
				pm.checkCounter.get());
	}
}

/**
 * 模拟资源竞争
 */
class PairChecker implements Runnable {
	private PairManager pm;

	public PairChecker(PairManager pm) {
		this.pm = pm;
	}

	public void run() {
		while (true) {
			pm.checkCounter.incrementAndGet();
			pm.getPair().checkStatus();
		}
	}
}

class PairManager1 extends PairManager {

	@Override
	public synchronized void increment() {
		p.incrementX();
		p.incrementY();
		store(getPair());
	}

}

class PairManager2 extends PairManager {

	@Override
	public void increment() {
		Pair tmp = null;
		synchronized (this) {
			p.incrementX();
			p.incrementY();
			tmp = getPair();
		}
		store(tmp);
	}

}

abstract class PairManager {
	AtomicInteger checkCounter = new AtomicInteger(0);
	protected Pair p = new Pair();
	private List<Pair> storage = Collections
			.synchronizedList(new ArrayList<Pair>());

	public abstract void increment();

	public synchronized Pair getPair() {
		return new Pair(p.getX(), p.getY());
	}

	public void store(Pair p) {
		storage.add(p);
		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (Exception e) {
			throw new RuntimeException("吵醒了");
		}
	}
}

class Pair {
	private int x, y;

	public Pair() {
		this(0, 0);
	}

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void checkStatus() {
		if (x != y) {
			throw new RuntimeException("x!=y");
		}
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void incrementX() {
		x++;
	}

	public void incrementY() {
		y++;
	}

	public String toString() {
		return String.format("x=%d, y=%d", x, y);
	}
}
