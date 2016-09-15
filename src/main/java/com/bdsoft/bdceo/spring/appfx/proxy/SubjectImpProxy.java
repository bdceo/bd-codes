package com.bdsoft.bdceo.spring.appfx.proxy;

public class SubjectImpProxy implements ISubject {

	private ISubject subject;

	public SubjectImpProxy(ISubject sub) {
		this.subject = sub;
	}

	// 设计模式-代理模式
	@Override
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
