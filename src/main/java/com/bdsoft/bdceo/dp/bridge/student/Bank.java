package com.bdsoft.bdceo.dp.bridge.student;

public interface Bank {
	public void method();
}

class Ccb implements Bank {
	public void method() {
		System.out.println("建设银行");
	}
}

class Icbc implements Bank {
	public void method() {
		System.out.println("工商银行");
	}
}
