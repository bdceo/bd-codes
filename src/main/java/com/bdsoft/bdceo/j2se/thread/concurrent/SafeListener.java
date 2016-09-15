package com.bdsoft.bdceo.j2se.thread.concurrent;

import java.awt.Event;
import java.util.EventListener;


// 安全的对象发布，是构造函数私有化，提供静态工厂方法返回构造完成的对象
public class SafeListener {

	private final EventListener listener;

	private SafeListener() {
		listener = new EventListener() {
			public void onEvent(Event e) {
				// doSomething
			}
		};
	}

	public static SafeListener newInstance(Object o) {
		SafeListener sl = new SafeListener();
		// o.addListener(sl.listener);
		return sl;
	}
}
