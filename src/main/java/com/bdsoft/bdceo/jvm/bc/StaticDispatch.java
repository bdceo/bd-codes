package com.bdsoft.bdceo.jvm.bc;

public class StaticDispatch {

	static abstract class Human {
	}

	static class Man extends Human {
	}

	static class Woman extends Human {
	}

	public void sayHello(Human guy) {
		System.out.println("hello, guy!");
	}

	public void sayHello(Man guy) {
		System.out.println("hello, gentleman!");
	}

	public void sayHello(Woman guy) {
		System.out.println("hello, lady!");
	}

	// 静态分派：方法重载是通过参数的“静态类型”来判断调用哪个方法的
	public static void main(String[] args) {
		// Human - 静态类型
		// Man、Woman - 实际类型
		Human man = new Man();
		Human lady = new Woman();
		StaticDispatch sd = new StaticDispatch();

		// 静态类型在编译期已知
		sd.sayHello(man);// guy
		sd.sayHello(lady);// guy

		// 静态类型旨在使用时发生变化
		sd.sayHello((Woman) lady);// lady
	}

}
