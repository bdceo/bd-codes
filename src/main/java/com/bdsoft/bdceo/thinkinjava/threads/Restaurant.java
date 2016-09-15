package com.bdsoft.bdceo.thinkinjava.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 测试模拟生产者和消费者：厨师和服务员
 */
public class Restaurant {

	ExecutorService es = Executors.newCachedThreadPool();

	Meal meal = null;

	Waiter w = new Waiter(this);
	Chef c = new Chef(this);

	public Restaurant() {
		es.execute(w);
		es.execute(c);
	}

	/**
	 * 入口
	 */
	public static void main(String[] args) {
		new Restaurant();
	}

}

class Waiter implements Runnable {

	private Restaurant rest;

	public Waiter(Restaurant r) {
		rest = r;
	}

	public void run() {
		System.out.println("++服务员上班了");
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (rest.meal == null) {
						wait();
					}
				}
				System.out.println("服务员送菜去：" + rest.meal);
				synchronized (rest.c) {
					rest.meal = null;
					rest.c.notifyAll();
				}
			}
		} catch (Exception e) {
			System.out.println("服务员意外退出");
		}
		System.out.println("--服务员下班了");
	}
}

class Chef implements Runnable {

	private int count = 0;
	private Restaurant rest;

	public Chef(Restaurant r) {
		rest = r;
	}

	public void run() {
		System.out.println("++厨师上班了");
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (rest.meal != null) {
						System.out.println("厨师等做菜");
						wait();
					}
				}
				if (++count == 10) {
					System.out.println("没食材了");
					rest.es.shutdownNow();
				}
				System.out.println("有人点菜啦...");
				synchronized (rest.w) {
					rest.meal = new Meal(count);
					System.out.println("厨师菜做好了：" + rest.meal);
					rest.w.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(200);
			}
		} catch (Exception e) {
			System.out.println("厨师意外退出");
		}
		System.out.println("--厨师下班了");
	}
}

// 菜肴
class Meal {
	private final int orderNum;

	public Meal(int on) {
		orderNum = on;
	}

	public String toString() {
		return String.format("Meal#%d", orderNum);
	}
}
