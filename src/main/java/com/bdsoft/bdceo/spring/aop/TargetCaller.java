package com.bdsoft.bdceo.spring.aop;

public class TargetCaller {

	private GeneracTargetObject target;

	public void callMethod() {
		target.method1();
	}

	public TargetCaller(GeneracTargetObject t) {
		this.target = t;
	}

}
