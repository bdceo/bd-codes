package com.bdsoft.bjsxt.dp.observer;

public class WakeUpEvent {
	private long time;
	private String loc;
	private Child c;

	public WakeUpEvent(long time, String loc, Child c) {
		super();
		this.time = time;
		this.loc = loc;
		this.c = c;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public Child getC() {
		return c;
	}

	public void setC(Child c) {
		this.c = c;
	}
}
