package com.bdsoft.y2010.m08;

public class Chess implements Runnable {
	public void run() {
		move(Thread.currentThread().getId());

	}

	static synchronized void move(long id) {
		System.out.println(id + " ");
		System.out.println(id + " ");
	}

	public static void main(String[] args) {
		Chess ch = new Chess();
		new Thread(ch).start();
		new Thread(new Chess()).start();
	}
}
