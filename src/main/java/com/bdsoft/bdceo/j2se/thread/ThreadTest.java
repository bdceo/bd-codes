package com.bdsoft.bdceo.j2se.thread;

import java.util.Date;

public class ThreadTest {

	private volatile int i = 4;

	public static void main(String[] args) {
		threadCount();

	}

	public static void threadCount() {
		// 线程计时统计，推荐使用nanoTime,两个值相减，得到纳秒级的差值
		System.out.println(System.nanoTime());
		System.out.println(new Date().getTime());
		System.out.println(System.currentTimeMillis());
	}
}

class PrimeProducer extends Thread {

	public void run() {
		// 判断当前线程是否中断
		boolean f = Thread.currentThread().isInterrupted();

		// 回复中断状态
		PrimeProducer.interrupted();
	}
	
	public void cancel(){
		// 发出中断信息
		this.interrupt();
	}
	
}
