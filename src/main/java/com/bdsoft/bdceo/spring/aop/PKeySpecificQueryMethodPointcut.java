package com.bdsoft.bdceo.spring.aop;

import java.lang.reflect.Method;

import org.springframework.aop.support.DynamicMethodMatcherPointcut;

public class PKeySpecificQueryMethodPointcut extends
		DynamicMethodMatcherPointcut {

	@Override
	public boolean matches(Method method, Class clazz, Object[] args) {
		if (method.getName().startsWith("get")
				&& clazz.getPackage().getName().startsWith("com.bdsoft")) {
			if (args != null) {
				return args[0].equals("bdceo");
			}
		}
		return false;
	}

}
