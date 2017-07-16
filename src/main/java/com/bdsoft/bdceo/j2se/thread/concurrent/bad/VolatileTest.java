/**
 * VolatileTest.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.j2se.thread.concurrent.bad;

/**
 * VolatileTest
 * 
 * @author bdceo
 * @date 2016-9-27 下午9:17:51
 * @version V1.0
 */
public class VolatileTest extends Thread {

	private boolean ready = true;

	@Override
	public void run() {
		System.out.println("thread start");
		while (ready == true ||(isReady())) {
		}
		System.out.println("thread end");
	}

	/**
	 * 入口
	 */
	public static void main(String[] args) throws InterruptedException {
		VolatileTest t2 = new VolatileTest();
		t2.start();
		Thread.sleep(500);
		t2.setReady(false);
		System.out.println("ready set false");
	}

	public boolean isReady() {
		System.out.println(ready);
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}
}
