package com.bdsoft.bdceo.j2se.thread;

public class P2C {

	// 生产和消费线程演示
	public static void main(String[] args) {
		// 资源引用
		Factory factory = new Factory();

		// 生产线程
		Producer pro = new Producer(factory);
		new Thread(pro).start();

		// 消费线程
		Consumer con = new Consumer(factory);
		new Thread(con).start();
	}
}

class Factory {
	private int index = 0;

	public synchronized int gen() {
		while (index == 10) {
			try {
				this.wait();
			} catch (Exception e) {
			}
		}
		index++;
		this.notify();
		return index;
	}

	public synchronized int sale() {
		while (index == 0) {
			try {
				this.wait();
			} catch (Exception e) {
			}
		}
		index--;
		this.notify();
		return index;
	}
}

class Producer implements Runnable {
	private Factory factory;

	public Producer(Factory f) {
		this.factory = f;
	}

	public void run() {
		try {
			for (int i = 0; i < 20; i++) {
				int id = this.factory.gen();
				System.out.println("gene index is " + id);
				Thread.sleep((long) (Math.random() * 100));
			}
		} catch (Exception e) {
			System.out.println("Produce wrong!");
		}
	}
}

class Consumer implements Runnable {
	private Factory factory;

	public Consumer(Factory f) {
		this.factory = f;
	}

	public void run() {
		try {
			for (int i = 0; i < 20; i++) {
				int id = this.factory.sale();
				System.out.println("\tsale index is " + id);
				Thread.sleep((long) (Math.random() * 200));
			}
		} catch (Exception e) {
			System.out.println("Consume wrong!");
		}
	}
}
