package com.bdsoft.bjsxt.thread;

public class TT implements Runnable {

	/**
	 * 此示例说明的是锁的实质，锁的是对象
	 * 
	 * 所以第一个td线程启动之后，TT对象实例就被锁定了
	 * 
	 * 当td线程在休眠时，调用TT实例的m2方法，暂且是无法调用的
	 * 
	 * 当TT实例的m2方法执行中，也锁住了TT对象实例
	 * 
	 * 所以在打印main中b的值时，是m2执行完毕释放对象所后实际的TT实例b值
	 * 
	 */
	public static void main(String[] args) throws Exception {
		TT tt = new TT();
		Thread td = new Thread(tt);
		td.start();

//		Thread.sleep(1000);
		tt.m2();
		System.out.println(Thread.currentThread().getId() + "-->main");
		System.out.println("\tmain中b=" + tt.b);
	}

	private int b = 0;

	public synchronized void m1() throws Exception {
		System.out.println(Thread.currentThread().getId() + "-->m1");
		b = 1000;
		Thread.sleep(5000);
		System.out.println("\tm1中b=" + b); 
	}

	public synchronized void m2() throws Exception {
		System.out.println(Thread.currentThread().getId() + "-->m2");
		b = 3000;
		Thread.sleep(2000);
		System.out.println("\tm2中b=" + b);
	}

	public void run() {
		System.out.println(Thread.currentThread().getId() + "-->run");
		try {
			m1();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
