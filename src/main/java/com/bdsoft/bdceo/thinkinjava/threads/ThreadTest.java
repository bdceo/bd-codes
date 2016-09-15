package com.bdsoft.bdceo.thinkinjava.threads;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 并发，线程测试:基本的线程机制
 */
public class ThreadTest {

	/**
	 * 入口
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("-----------------1-------------------");
		// 原始的java主线程运行，即方法调用
		LiftOff lo = new LiftOff();
		lo.run();

		System.out.println("\n\n-----------------2-------------------");
		// 由main主线程，开启一个独立的线程运行
		Thread td = new Thread(new LiftOff());
		td.start();
		Thread.sleep(100L);

		System.out.println("\n\n-----------------3-------------------");
		// 开启多个线程
		int tn = 5;
		for (int i = 0; i < tn; i++) {
			new Thread(new LiftOff()).start();
		}
		Thread.sleep(500L);

		System.out.println("\n\n-----------------4-------------------");
		// 使用线程池，管理线程/任务执行的调度
		// ExecutorService es = Executors.newCachedThreadPool();
		// ExecutorService es = Executors.newFixedThreadPool(tn);
		ExecutorService es = Executors.newSingleThreadExecutor();
		for (int i = 0; i < tn; i++) {
			es.execute(new LiftOff());
		}
		es.shutdown();
		// es.execute(new LiftOff()); // 拒绝提交任务
		Thread.sleep(500L);

		System.out.println("\n\n-----------------5-------------------");
		// 从任务中产生返回值
		List<Future<Long>> cbs = new ArrayList<Future<Long>>();
		es = Executors.newCachedThreadPool();
		for (int i = 0; i < tn; i++) {
			// 使用submit提交任务，而不是execute执行
			Future<Long> cb = es.submit(new TaskWithResult());
			cbs.add(cb);
		}
		es.shutdown();
		// 获取任务返回值
		for (Future<Long> cb : cbs) {
			System.out.println(String.format("cb.isDone=%b", cb.isDone()));
			Long t = cb.get();// 阻塞方法
			System.out.println("执行完毕：" + new Date(t).toLocaleString());
		}

		System.out.println("\n-----------------6-------------------");
		// 指定线程工厂模型，则创建出的线程都将是后台线程
		es = Executors.newCachedThreadPool(new DaemonThreadFactory());
		for (int i = 0; i < tn; i++) {
			// es.execute(new LiftOff(50));
		}
		es.shutdown();

		System.out.println("\n-----------------7-------------------");
		// 自管理线程
		new SelfManaged();
	}

}

// 自管理的线程方式，构造方法里直接启动运行
class SelfManaged implements Runnable {
	int countDown = 5;
	private Thread t = new Thread(this);

	public SelfManaged() {
		t.start();
	}

	public String toString() {
		return Thread.currentThread().getName() + "(" + countDown + "), ";
	}

	public void run() {
		while (countDown-- > 0) {
			System.out.print(this);
		}
	}
}

// 定制化线程工厂
class DaemonThreadFactory implements ThreadFactory {

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setDaemon(true);
		t.setPriority(4);
		// 设置默认的异常处理器
		t.setUncaughtExceptionHandler(new MyUnCaughtExceptionHandler());
		return t;
	}
}

class MyUnCaughtExceptionHandler implements UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("发现异常 " + e);
	}
}

// 带返回值的任务模式
class TaskWithResult implements Callable<Long> {
	static Random rd = new Random(1988);

	// 带返回值的线程任务
	public Long call() {
		try {
			TimeUnit.MILLISECONDS.sleep(rd.nextInt(1000));
		} catch (Exception e) {
		}
		return System.currentTimeMillis();
	}
}

class LiftOff implements Runnable {

	private int countDown = 3;

	private static int taskCount = 0;
	private final int id = taskCount++;

	public LiftOff() {
	}

	public LiftOff(int cd) {
		this.countDown = cd;
	}

	public String status() {
		StringBuilder sb = new StringBuilder("#").append(id).append("(")
				.append((countDown > 0 ? countDown : "0/off")).append("), ");
		return sb.toString();
	}

	// 常规线程任务
	public void run() {
		while (countDown-- > 0) {
			System.out.print(status());
			Thread.yield();
		}
	}

}
