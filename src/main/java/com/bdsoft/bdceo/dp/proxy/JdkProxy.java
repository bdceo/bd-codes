package com.bdsoft.bdceo.dp.proxy;

import java.lang.reflect.Proxy;

/**
 * jdk动态代理
 */
public class JdkProxy {

	public static void main(String[] args) {
		// 代理类的类加载器，代理类的实现接口列表，代理对象方法调用的处理程序
		ISubject sub = (ISubject) Proxy.newProxyInstance(JdkProxy.class.getClassLoader(),
				new Class[] { ISubject.class }, new JdkProxyHandler(new SubjectImp()));

		// 返回指定类的代理对象，并且包含了代理方法处理程序
		String res = sub.request();

		System.out.println("响应：" + res);
	}

}
