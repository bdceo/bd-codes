package com.bdsoft.bjsxt.thread;

public class YieldTest {

	public static void main(String[] args) {
		Th1 t1 = new Th1("A");
		Th1 t2 = new Th1("B");
		t1.start();
		t2.start();
	}
}

class Th1 extends Thread {
	public Th1(String s) {
		super(s);
	}

	public void run() {
		for (int i = 0; i < 50; i++) {
			System.out.println(getName() + "---" + i);
			if (i % 10 == 0) {
				yield();
			}
		}
	}
}