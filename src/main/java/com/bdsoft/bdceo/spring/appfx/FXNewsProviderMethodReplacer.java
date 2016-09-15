package com.bdsoft.bdceo.spring.appfx;

import java.lang.reflect.Method;

import org.springframework.beans.factory.support.MethodReplacer;

public class FXNewsProviderMethodReplacer implements MethodReplacer {

	@Override
	public Object reimplement(Object arg0, Method arg1, Object[] arg2)
			throws Throwable {
		System.out.println("haha,replace all!");
		return null;
	}

}
