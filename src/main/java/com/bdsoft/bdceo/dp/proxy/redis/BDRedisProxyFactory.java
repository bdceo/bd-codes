/**
 * BDRedisProxyFactory.java
 * com.bdsoft.bdceo.dp.proxy.redis
 * Copyright (c) 2014, 北京微课创景教育科技有限公司版权所有.
*/
package com.bdsoft.bdceo.dp.proxy.redis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 模拟代理方式实现redis的事务机制
 * 
 * 实现InitializingBean，spring自动加载可以实现代理类初始化
 * 实现FactoryBean，实现其他类注入此类，自动返回代理实现
 * 实现InvocationHandler，实现代理逻辑
 * 
 * @author	丁辰叶
 * @date	2015-11-27
 */
public class BDRedisProxyFactory implements InvocationHandler, FactoryBean<BDRedis>, InitializingBean {

	BDRedis proxy = null;

	/**
	 * 初始化代理
	 */
	public BDRedisProxyFactory() {
		proxy = (BDRedis) Proxy.newProxyInstance(BDRedisProxyFactory.class.getClassLoader(),
				new Class[] { BDRedis.class }, this);
	}

	/**
	 * 测试调用
	 */
	public static void main(String[] args) {
		BDRedisProxyFactory redis = new BDRedisProxyFactory();
		// 代理调用
		redis.proxy.get("bdceo");
	}

	/**
	 * 调用代理时的实际处理
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("start tran");
		Object data = method.invoke(new BDRedisClient(), args);
		System.out.println("end tran");
		return data;
	}

	@Override
	public BDRedis getObject() throws Exception {
		return proxy;
	}

	@Override
	public Class<?> getObjectType() {
		return BDRedis.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public void afterPropertiesSet() throws Exception {

	}

}

class BDRedisClient implements BDRedis {

	@Override
	public void get(String key) {
		System.out.println("get>" + key);
	}

	@Override
	public void set(String key, Object obj) {
		System.out.println("set>" + key + "=" + obj);
	}

}
