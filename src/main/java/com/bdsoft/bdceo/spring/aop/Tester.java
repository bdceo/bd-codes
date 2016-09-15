package com.bdsoft.bdceo.spring.aop;

public class Tester implements ITester {

	private boolean busyAsTester;

	@Override
	public void testSoftware() {
		System.out.println("i am a tester");
	}

	@Override
	public boolean isBusyAsTester() {
		return busyAsTester;
	}

	public void setBusyAsTester(boolean busyAsTester) {
		this.busyAsTester = busyAsTester;
	}

}
