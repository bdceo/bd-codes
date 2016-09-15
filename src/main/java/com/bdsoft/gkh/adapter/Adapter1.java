package com.bdsoft.gkh.adapter;

//如果将B接到A上用，这叫做单向适配器模式，A主动，B被动
//单向适配器模式要点：继承法
//考虑被动方以后可能被切换，为了方便，最好面向接口编程
class A {
	public void funA() {
		System.out.println("图像清晰化");
	}
}

interface IEM {// 定义一个接口，管理所有的扩展模块(被动模块)
	public abstract void extendFun();
}

class B implements IEM {// IEM = IExtendModule
	public void extendFun() {
		System.out.println("去噪声-旧算法");
	}
}

class C implements IEM {
	public void extendFun() {
		System.out.println("去噪声-新算法");
	}
}

class SubA extends A {// 将A进行扩展，以后将SubA当成A来用
	private IEM iem;// 完全面向接口编程，可以将iem任何实现类对象接入(可以用Spring注入)

	public SubA(IEM iem) {
		this.iem = iem;
	}

	public void funA() {
		super.funA();// 值得一提的是：这句代码可以去掉，如果去掉，就是功能替换
		iem.extendFun();
	}
}

public class Adapter1 {
	public static void main(String[] args) {
		B b = new B();
		C c = new C();
		SubA sa = new SubA(c);
		sa.funA();
	}
}