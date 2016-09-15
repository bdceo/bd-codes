package com.bdsoft.bdceo.spring.aop;

import java.lang.reflect.Method;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

public class QueryMethodPointcut extends StaticMethodMatcherPointcut {

	@Override
	public boolean matches(Method method, Class clazz) {
		return method.getName().startsWith("get")
				&& clazz.getPackage().getName().startsWith("com.bdsoft");
	}

}
