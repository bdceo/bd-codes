package com.bdsoft.bdceo.spring.appfx.cglibproxy;

public class Requestable {

	// 未实现接口，模拟动态代理
	public void request() {
		System.out.println(this.getClass().getCanonicalName() + "-->request");
	}
}
