package com.bdsoft.bdceo.dp.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * jdk动态代理处理器
 */
public class JdkProxyHandler implements InvocationHandler {

	// 代理对象实现
	private Object target;

	public JdkProxyHandler(Object tar) {
		this.target = tar;
	}

	// jdk-动态代理：在代理实例上处理方法调用并返回结果。在与方法关联的代理实例上调用方法时，将在调用处理程序上调用此方法。
	// proxy - 在其上调用方法的代理实例
	// atm - 对应于在代理实例上调用的接口方法的 Method 实例
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (method.getName().equals("request")) {
			System.out.println(proxy.getClass().getSimpleName() + ".request");
			// aop横切逻辑控制...
			System.out.println("jdk-dyProxy-before-request");
			method.invoke(target, args);
			System.out.println("jdk-dyProxy-after-request");
			return "jdk dy proxy is ok";
		}
		return "nothing";
	}

}
