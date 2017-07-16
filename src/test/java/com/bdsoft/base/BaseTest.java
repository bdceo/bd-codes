package com.bdsoft.base;

public class BaseTest  extends Thread{

	private  boolean ready = true;

	@Override
	public void run() {
		int tmp =0;
		System.out.println("开始thread...");
		while (this.ready == true) {
			
		}
		System.out.println(tmp);
		System.out.println("结束thread...");
	}

	public static void main(String[] args) throws InterruptedException {
		BaseTest t2 = new BaseTest();
		t2.start();
		Thread.sleep(500);
		t2.ready=false;
		System.out.println("ready的值已经被设置了false");
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

}
