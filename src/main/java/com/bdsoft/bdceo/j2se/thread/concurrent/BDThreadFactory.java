package com.bdsoft.bdceo.j2se.thread.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

// 扩展线程工厂，添加调试信息
public class BDThreadFactory implements ThreadFactory {

	private final String POOL_NAME;

	public BDThreadFactory(String name) {
		this.POOL_NAME = name;
	}

	public Thread newThread(Runnable r) {
		return new BDThread(r, POOL_NAME);
	}

}

// 定制线程池：添加运行统计信息
class BDThreadPool extends ThreadPoolExecutor {

	private final ThreadLocal<Long> startTime = new ThreadLocal<Long>();
	private final Logger log = Logger.getLogger("BDThreadPool");
	private final AtomicLong numTasks = new AtomicLong();
	private final AtomicLong totalTime = new AtomicLong();

	public BDThreadPool(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	protected void beforeExecute(Thread t, Runnable r) {
		super.beforeExecute(t, r);
		log.fine(String.format("Thread %s: start %s", t, r));
		startTime.set(System.nanoTime());
	}

	protected void afterExecute(Runnable r, Throwable e) {
		try {
			long endTime = System.nanoTime();
			long taskTime = endTime - startTime.get();
			numTasks.incrementAndGet();
			totalTime.addAndGet(taskTime);
			log.fine(String.format("线程；%s,结束：%s,耗时：%nds", r, endTime, taskTime));
		} finally {
			super.afterExecute(r, e);
		}
	}

	protected void terminated() {
		try {
			log.info(String.format("任务执行平均时间=&dns",
					totalTime.get() / numTasks.get()));
		} finally {
			super.terminated();
		}
	}
}

// 定制Thread基类
class BDThread extends Thread {

	private static final String DEFAULT_NAME = "BDThread";

	private static volatile boolean debugLifecycle = false;
	private static final AtomicInteger created = new AtomicInteger();
	private static final AtomicInteger alive = new AtomicInteger();
	private static final Logger log = Logger.getAnonymousLogger();

	public BDThread(Runnable r) {
		this(r, DEFAULT_NAME);
	}

	public BDThread(Runnable r, String name) {
		super(r, name + "#" + created.incrementAndGet());
		setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			public void uncaughtException(Thread t, Throwable e) {
				log.log(Level.SEVERE, "异常线程：" + t.getName(), e);
			}
		});
	}

	public void run() {
		boolean debug = debugLifecycle;
		if (debug) {
			log.log(Level.FINE, "线程创建：" + getName());
		}
		try {
			alive.incrementAndGet();
			super.run();
		} finally {
			alive.decrementAndGet();
			if (debug) {
				log.log(Level.FINE, "线程销毁：" + getName());
			}
		}
	}

	public static int getThreadCreated() {
		return created.get();
	}

	public static int getThreadAlive() {
		return alive.get();
	}

	public static boolean getDebug() {
		return debugLifecycle;
	}

	public static void setDebug(boolean b) {
		debugLifecycle = b;
	}

}