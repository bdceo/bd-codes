package com.bdsoft.y2012.m12;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/** 静态代理模拟实现 **/
interface Count {
	  void queryCount();

	  void updateCount();
}

class CountImpl implements Count {
	public void queryCount() {
		System.out.println("queryCount");
	}

	public void updateCount() {
		System.out.println("updateCount");
	}
}

// 静态代理实现-代理类
class CountProxy implements Count {
	private Count ci;

	public CountProxy(Count ci) {
		this.ci = ci;
	}

	public void queryCount() {
		System.out.println("queryCount-before");
		ci.queryCount();
		System.out.println("queryCount-after");
	}

	public void updateCount() {
		System.out.println("updateCount-before");
		ci.updateCount();
		System.out.println("updateCount-after");
	}

}

/** 静态代理模拟实现 **/

public class JdkProxyTest {

	// 参考：http://www.cnblogs.com/jqyp/archive/2010/08/20/1805041.html
	public static void main(String[] args) {
		// 静态代理模拟实现
		CountProxy cp = new CountProxy(new CountImpl());
		cp.queryCount();
		cp.updateCount();

		// Jdk动态代理
		BookFacadeProxy proxy = new BookFacadeProxy();
		BookFacade book = (BookFacade) proxy.bind(new BookFacadeImpl());
		book.addBook();

		// cglib动态代理
		BookFacadeCglib cglib = new BookFacadeCglib();
		BookFacadeImpl2 bookImpl2 = (BookFacadeImpl2) cglib
				.getInstance(new BookFacadeImpl2());
		bookImpl2.addBook();
	}

}

/** JDK-动态代理模拟实现 **/
interface BookFacade {
	  void addBook();
}

class BookFacadeImpl implements BookFacade {
	public void addBook() {
		System.out.println("addBook");
	}
}

// jdk动态代理，需要实现InvocationHandler接口
class BookFacadeProxy implements InvocationHandler {
	private Object target;

	/**
	 * 绑定委托对象并返回一个代理类
	 * 
	 * @param target
	 * @return
	 */
	public Object bind(Object target) {
		this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), this);
	}

	/**
	 * 调用方法
	 */
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object result = null;
		System.out.println("exe - before");
		// 执行目标方法
		result = method.invoke(target, args);
		System.out.println("exe - after");
		return result;
	}

}

/** JDK-动态代理模拟实现 **/

/** cglib-动态代理模拟实现 **/
class BookFacadeImpl2 {
	public void addBook() {
		System.out.println("addBook - 2 of cglib");
	}
}

class BookFacadeCglib implements MethodInterceptor {
	private Object target;

	/**
	 * 创建代理对象
	 * 
	 * @param target
	 * @return
	 */
	public Object getInstance(Object target) {
		this.target = target;
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(this.target.getClass());
		enhancer.setCallback(this);
		return enhancer.create();
	}

	/**
	 * 回调方法
	 */
	@Override
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		System.out.println("exe - before");
		// 执行目标方法
		proxy.invokeSuper(obj, args);
		System.out.println("exe - after");
		return null;
	}

}
/** cglib-动态代理模拟实现 **/
