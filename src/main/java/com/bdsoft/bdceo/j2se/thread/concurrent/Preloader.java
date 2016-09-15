package com.bdsoft.bdceo.j2se.thread.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

// FutureTask-闭锁模拟
public class Preloader {

	private final FutureTask<Object> future = new FutureTask<Object>(
			new Callable<Object>() {
				public Object call() {
					StringBuffer sb = new StringBuffer();
					for (int i = 0; i < 100; i++) {
						sb.append(i).append(" ");
					}
					return sb.toString();
				}
			});

	private final Thread thread = new Thread(future);

	public void start() {
		thread.start();
	}

	public Object get() {
		try {
			return future.get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
