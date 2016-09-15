package com.bdsoft.bjsxt.thread;

public class ProducerConsumer {

	/**
	 * 生产消费线程演示
	 */
	public static void main(String[] args) {
		// 生产和消费线程，需要持有同一个线程栈的引用
		SyncStack ss = new SyncStack();

		Producer p = new Producer(ss);
		new Thread(p).start();

		Consumer c = new Consumer(ss);
		new Thread(c).start();
	}
}

// 线程同步栈：处理、控制线程的生产和消费
class SyncStack {
	private int index = 0;
	private Wotou[] ws = new Wotou[10];// 默认10个最大

	// 进栈：生产线程调用，满10个产品，线程暂停
	public synchronized void push(Wotou wt) {
		while (index == ws.length) {
			try {
				this.wait();
				System.out.println("push-wait");
			} catch (InterruptedException e) {
				System.out.println("Product exception 。。。");
			}
		}
		ws[index] = wt;
		index++;
		this.notify();
	}

	// 出栈：消费线程调用，不足1时，线程暂停
	public synchronized Wotou pop() {
		while (index == 0) {
			try {
				this.wait();
				System.out.println("pop-wait");
			} catch (InterruptedException e) {
				System.out.println("Customer exception 。。。");
			}
		}
		index--;
		Wotou w = ws[index];
		this.notify();
		return w;
	}
}

// 生产者线程
class Producer implements Runnable {
	private SyncStack st;

	public Producer(SyncStack ss) {
		this.st = ss;
	}

	public void run() {
		for (int i = 0; i < 20; i++) {
			Wotou wt = new Wotou(i);
			st.push(wt);
			System.out.println("Produce Wotou of " + wt);
			try {
				Thread.sleep((long) (Math.random() * 100));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

// 消费者线程
class Consumer implements Runnable {
	private SyncStack st;

	public Consumer(SyncStack ss) {
		this.st = ss;
	}

	public void run() {
		for (int i = 0; i < 20; i++) {
			Wotou wt = st.pop();
			System.out.println("\tConsume Wotou of " + wt);
			try {
				Thread.sleep((long) (Math.random() * 500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

// 生产产品抽象：窝窝头
class Wotou {
	private int id;

	public Wotou(int id) {
		this.id = id;
	}

	public String toString() {
		return "ID=" + id;
	}
}
