package com.bdsoft.bdceo.spring.appfx;

// 静态工厂
public class StaticFactory {

	public static IBar getBarImpl() {
		return new BarImpl();
	}
}
