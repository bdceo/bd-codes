package com.bdsoft.bdceo.j2se.thread.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableThreadTest {

	// 待返回值的线程调用测试：
	public static void main(String[] args) {
		ExecutorService pool = Executors.newSingleThreadExecutor();

		try {
			// 线程池的submit方法，开启任务,任务实现Callable接口，返回的是Future实例
			Future future = pool.submit(new CallImpl("bdceo"));

			// get方法，开始阻塞线程，直到线程返回
			Object obj = future.get();

			System.out.println("data : " + obj);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.shutdown();
		}
	}

}

// 待返回值的线程调用，需要实现Callable接口
class CallImpl implements Callable {
	private String name;

	public CallImpl(String n) {
		this.name = n;
	}

	public Object call() {
		System.out.println(name + " execute");
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "sucess";
	}
}
