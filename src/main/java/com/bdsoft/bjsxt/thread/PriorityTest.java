package com.bdsoft.bjsxt.thread;

public class PriorityTest {

	public static void main(String[] args) {
		T1 t1 = new T1();
		T2 t2 = new T2();

		// 设置t1线程最高优先级，在只有两个线程运行情况下
		// cpu会分配更多的执行机会给t1线程，t2也会较慢的执行
		// 只有在t1执行完毕，t2才开始正常运行
		t1.setPriority(Thread.MAX_PRIORITY);
		t1.start();
		t2.start();
	}
	
}

class T1 extends Thread {
	public void run() {
		for (int i = 0; i < 300; i++) {
			System.out.println("+++++++" + i);
		}
	}
}

class T2 extends Thread {
	public void run() {
		for (int i = 0; i < 300; i++) {
			System.out.println("*******" + i);
		}
	}
}