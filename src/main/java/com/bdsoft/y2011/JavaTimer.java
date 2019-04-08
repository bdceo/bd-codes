package com.bdsoft.y2011;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class JavaTimer {

	private static int count = 0;
	private Thread thread;
	private boolean flag = true;

	public JavaTimer() {
		this.thread = new Thread() {
			public void run() {
				while (true) {
					try {
						sleep(500);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (flag) {
						System.out.println((new Date()).toLocaleString());
					}
				}
			}
		};
		this.thread.start();
	}

	public static void main(String[] args) throws Exception {
		opONtimer();
		JavaTimer jt = new JavaTimer();
		Thread.sleep(2000);
		jt.setFlag(false);
		Thread.sleep(2000);
		jt.setFlag(true);
	}

	public static void opONtimer() {
		TimerTask jt = new TimerTask() {
			public void run() {
				System.out.println("第" + count + "次执行");
				count++;
			}
		};
		Timer t = new Timer();
		t.schedule(jt, 2 * 1000, 1 * 1000);
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
