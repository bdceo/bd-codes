package com.bdsoft.bdceo.spring.aop.demo;

public class AOPMain {

	private AOPDemo demo;

	public AOPMain() {
	}

	public void testException() {
		try {
			demo.login("", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AOPDemo getDemo() {
		return demo;
	}

	public void setDemo(AOPDemo demo) {
		this.demo = demo;
	}

}
