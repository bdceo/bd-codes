package com.bdsoft.bdceo.spring.appfx;

// 非静态工厂
public class NonStaticFactory {

	public IBar getBarImpl() {
		return new BarImpl();
	}
}
