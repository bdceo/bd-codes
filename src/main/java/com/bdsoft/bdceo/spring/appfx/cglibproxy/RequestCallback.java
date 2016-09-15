package com.bdsoft.bdceo.spring.appfx.cglibproxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class RequestCallback implements MethodInterceptor {

	// cglib，实现方法拦截
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		if (method.getName().equals("request")) {
			// aop横切逻辑控制...
			System.out.println("cglib-proxy-before-request");
			proxy.invokeSuper(obj, args);
			System.out.println("cglib-proxy-after-request");
			return "cglib proxy is ok";
		}
		return null;
	}

}
