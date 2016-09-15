package com.bdsoft.bdceo.thinkinjava.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 模拟线程之间交互：通过Object的notify，wait方法
 */
public class NotifyTest {

	/**
	 * 入口
	 */
	public static void main(String[] args) throws Exception {
		// 共享资源
		Car car = new Car();

		// 模拟线程之间的交互
		ExecutorService es = Executors.newCachedThreadPool();
		es.execute(new WaxOn(car));
		es.execute(new WaxOff(car));

		TimeUnit.SECONDS.sleep(3);
		// 出发线程的中断异常
		es.shutdownNow();
	}

}

// 模拟给汽车打蜡
class WaxOn implements Runnable {
	private Car car;

	public WaxOn(Car c) {
		car = c;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				System.out.println("++开始打蜡");// 开始打蜡
				TimeUnit.MILLISECONDS.sleep(200);// 模拟打蜡耗时
				car.waxed();// 打蜡完毕，通知抛光线程
				car.waitForBuffing();// 挂起自己
			}
		} catch (InterruptedException e) {
			System.out.println("waxOn exit by interrrupt");
		}
		System.out.println("waxOn exit!");
	}
}

// 模拟给汽车抛光
class WaxOff implements Runnable {
	private Car car;

	public WaxOff(Car c) {
		car = c;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				car.waitForWaxing();// 等待打蜡，挂起自己
				System.out.println("--开始抛光");// 开始抛光
				TimeUnit.MILLISECONDS.sleep(200);// 模拟抛光
				car.buffed();// 抛光完毕，通知打蜡
			}
		} catch (InterruptedException e) {
			System.out.println("waxOff exit by interrrupt");
		}
		System.out.println("waxOff exit!");
	}
}

class Car {
	// 汽车默认未打蜡
	private boolean waxOn = false;

	// 打蜡完毕
	public synchronized void waxed() {
		waxOn = true;
		notifyAll();
	}

	// 抛光完毕
	public synchronized void buffed() {
		waxOn = false;
		notifyAll();
	}

	// 等待打蜡
	public synchronized void waitForWaxing() throws InterruptedException {
		while (waxOn == false) {
			wait();
		}
	}

	// 等待抛光
	public synchronized void waitForBuffing() throws InterruptedException {
		while (waxOn == true) {
			wait();
		}
	}
}
