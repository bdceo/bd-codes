package com.bdsoft.bdceo.j2se.thread.concurrent;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mail {

	// 只执行一次的服务
	public boolean checkMail(Set<String> hosts, long timeout, TimeUnit unit)
			throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		final AtomicBoolean hasNewMail = new AtomicBoolean(false);
		try {
			for (final String host : hosts) {
				exec.execute(new Runnable() {
					public void run() {
						if (checkMail(host)) {
							hasNewMail.set(true);
						}
					}
				});
			}
		} finally {
			exec.shutdown();
			exec.awaitTermination(timeout, unit);
		}
		return hasNewMail.get();
	}

	public boolean checkMail(String host) {
		return false;
	}

}

// 线程池工作者线程标准结构
class BaseThread extends Thread {

	public BaseThread() {
		this.setUncaughtExceptionHandler(new UEHLogger());
	}

	public void regHook() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				System.out.println("注册jvm关闭时的《关闭钩子》");
			}
		});
	}

	public void run() {
		Throwable exp = null;
		try {
			while (!isInterrupted()) {
				// do task
			}
		} catch (Exception e) {
			exp = e;
		} finally {
			// this.getUncaughtExceptionHandler().uncaughtException(this, exp);
			threadExited(this, exp);
		}
	}

	public void threadExited(Thread t, Throwable e) {

	}
}

// 未捕获异常的处理
class UEHLogger implements Thread.UncaughtExceptionHandler {
	public void uncaughtException(Thread t, Throwable e) {
		Logger logger = Logger.getAnonymousLogger();
		logger.log(Level.SEVERE,
				"Thread terminated with exception: " + t.getName(), e);
	}
}
