package com.bdsoft.bdceo.spring.appfx.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RequestInvocationHandler implements InvocationHandler {

	private Object target;

	public RequestInvocationHandler(Object tar) {
		this.target = tar;
	}

	// jdk-动态代理：在代理实例上处理方法调用并返回结果。在与方法关联的代理实例上调用方法时，将在调用处理程序上调用此方法。
	// proxy - 在其上调用方法的代理实例
	// method - 对应于在代理实例上调用的接口方法的 Method 实例
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("jdk-dyProxy-name = " + proxy.getClass().getName());
		if (method.getName().equals("request")) {
			// aop横切逻辑控制...
			System.out.println("jdk-dyProxy-before-request");
			method.invoke(target, args);
			System.out.println("jdk-dyProxy-after-request");
			return "jdk dy proxy is ok";
		}
		return null;
	}

}
