package com.bdsoft.bdceo.dp.proxy;

public class SubjectDemo {

	public static void main(String[] args) {
		ISubject sub = new SubjectImpProxy(new SubjectImp());
		sub.request();
	}
}

// 代理接口，抽象模拟
interface ISubject {
	String request();
}

// 代理默认实现
class SubjectImp implements ISubject {

	// 真实主体对象的方法实现
	public String request() {
		System.out.println(this.getClass().getSimpleName() + ".request");
		return null;
	}

}

class SubjectImpProxy implements ISubject {

	// 代理对象的引用
	private ISubject subject;

	public SubjectImpProxy(ISubject sub) {
		this.subject = sub;
	}

	// 设计模式-代理模式
	public String request() {
		System.out.println("proxy-before-request");
		subject.request();
		System.out.println("proxy-after-request");
		return null;
	}

	public ISubject getSubject() {
		return subject;
	}

	public void setSubject(ISubject subject) {
		this.subject = subject;
	}

}
