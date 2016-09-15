package com.bdsoft.y2011.m05;

public class ThreadExam  implements Runnable{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread thread = new Thread(new ThreadExam());
		thread.start();
		
	}
	
	public void run(){
		System.out.println("ThreadExam --> run().");
		
	}
	
	public void disp(){
		System.out.println("ThreadExam --> disp().");
	}

}
