package com.bdsoft.y2015.m12;

public class JavaThread {

	public static void main(String[] args) {

		SaleThread st = new SaleThread();

		for (int i = 0; i < 20; i++) {
			Thread td = new Thread(st);
			td.start();
		}
	}

}

class SaleThread implements Runnable {

	int num = 10;

	@Override
	public void run() {
		while (true) {
			synchronized (this) {
				if (num > 0) {
					System.out.println(String.format("售票口：%s，买了%d张票，还剩%d张票",
							Thread.currentThread().getName(), 1, --num));
				}
				if (num == 0) {
					break;
				}
			}
		}
	}

}
