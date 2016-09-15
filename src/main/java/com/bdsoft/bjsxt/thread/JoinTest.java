package com.bdsoft.bjsxt.thread;

public class JoinTest {

	/**
	 * 相当于方法的调用
	 */
	public static void main(String[] args) {
		MyThreads th = new MyThreads("jdi");
		th.start();
		try {
			// 把th这个线程合并到了main主线程。此时就相当于方法的调用
			// 打印结果相当于th中打印完之后，才会打印主线程的内容
			th.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 10; i++) {
			System.out.println("main thread:" + i);
		}
	}
}

class MyThreads extends Thread {
	public MyThreads(String s) {
		super(s);
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("i am :" + getName());
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}
