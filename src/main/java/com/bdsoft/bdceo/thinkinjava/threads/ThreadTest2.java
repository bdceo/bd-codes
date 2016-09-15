package com.bdsoft.bdceo.thinkinjava.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 并发，线程测试:共享受限资源
 */
public class ThreadTest2 {

	/**
	 * 入口
	 */
	public static void main(String[] args) {
		// 偶数生成器，相当于是一个共享资源
		// IntGenerator eg = new EvenGenerator();
		// test(eg);

		// 手动管理显示锁
		testLock();
	}

	// 默认开启10给测试线程
	static void test(IntGenerator eg) {
		test(eg, 10);
	}

	// 指定开启线程数
	static void test(IntGenerator eg, int tcount) {
		ExecutorService es = Executors.newCachedThreadPool();
		for (int i = 0; i < tcount; i++) {
			es.execute(new EvenChecker(eg, i));
		}
		es.shutdown();
	}

	// 测试显示锁竞争
	static void testLock() {
		final AttemptLocking al = new AttemptLocking();
		al.untimed();
		al.timed();
		new Thread() {
			{
				setDaemon(true);
			}

			public void run() {
				al.lock.lock();
				System.out.println("al.lock.lock()");
			}
		}.start();
		try {
			// 故意让后续事务延迟，产生锁竞争
			TimeUnit.SECONDS.sleep(1);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		al.untimed();
		al.timed();
	}

}

// 偶数性检查
class EvenChecker implements Runnable {
	private static int countDown = 10000;
	private IntGenerator gentor;
	private final int id;

	public EvenChecker(IntGenerator g, int i) {
		gentor = g;
		id = i;
	}

	public void run() {
		while (!gentor.isCanceled()) {
			// int val = gentor.next();
			int val = gentor.safenext();
			if (val % 2 != 0) {
				System.out.println(String.format("线程#%d 发现[%d]非偶数", id, val));
				gentor.cancel();
			} else {
				System.out.println(String.format("线程#%d 发现偶数[%d]", id, val));
			}
			if (val > countDown) {
				System.out.println(String.format("线程#%d 数据已超过%d，主动停止", id,
						countDown));
				gentor.cancel();
			}
		}
	}
}

// 整数生成器
abstract class IntGenerator {

	private volatile boolean canceled = false;

	public abstract int next();

	public abstract int safenext();

	// 提供终止信号的方法
	public void cancel() {
		if (isCanceled()) {
			System.out.println("忽略重复关闭操作");
			return;
		}
		canceled = true;
		System.out.println("资源已关闭");
	}

	// 获取终止标识
	public boolean isCanceled() {
		return canceled;
	}
}

// 偶数生成器
class EvenGenerator extends IntGenerator {
	// 共享资源，将所有域设置为private，只能通过方法进行修改，保证线程安全
	private int currentEvenValue = 0;

	private AtomicInteger curEven = new AtomicInteger(0);

	// 显示锁
	private Lock lock = new ReentrantLock();

	public int next() {
		// 非线程安全，自增操作，非原子性：1-读取当前值，2-加加，3-修改当前值
		currentEvenValue++;
		// Thread.yield(); // 可提前观测到线程退出的情况
		++currentEvenValue;
		return currentEvenValue;
	}

	// 线程安全：同步方法
	public synchronized int safenext() {
		currentEvenValue++;
		++currentEvenValue;
		return currentEvenValue;
	}

	// 线程安全：显示锁控制
	public int safenextWithLock() {
		lock.lock();
		try {
			currentEvenValue++;
			++currentEvenValue;
			return currentEvenValue;
		} finally {
			lock.unlock();
		}
	}

	// 线程安全： 原子基础类型
	public int safenextWithAtoI() {
		return curEven.addAndGet(2);
	}

}

/**
 * 带时间的锁，尝试获取
 */
class AttemptLocking {
	protected ReentrantLock lock = new ReentrantLock();

	public void untimed() {
		boolean captured = lock.tryLock();
		try {
			System.out.println("tryLock=" + captured);
		} finally {
			if (captured) {
				lock.unlock();
			}
		}
	}

	public void timed() {
		boolean captured = false;
		try {
			captured = lock.tryLock(2, TimeUnit.SECONDS);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		try {
			System.out.println("tryLock(2,sec)=" + captured);
		} finally {
			if (captured) {
				lock.unlock();
			}
		}
	}
}
