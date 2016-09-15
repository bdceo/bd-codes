package com.bdsoft.bdceo.spring.appfx.jdkevent;

import java.util.ArrayList;
import java.util.List;

public class MethodExecutionPublisher {

	private List<MethodExecutionEventListener> listeners = new ArrayList<MethodExecutionEventListener>();

	// 事件的发布
	public void methodToMonitor() {
		MethodExecutionEvent event = new MethodExecutionEvent(this,
				"methodToMonitor");
		publishEvent(1, event);
		System.out.println("方法执行中");
		publishEvent(2, event);
	}

	// 发布事件监听
	protected void publishEvent(int flag, MethodExecutionEvent event) {
		List<MethodExecutionEventListener> mlis = new ArrayList<MethodExecutionEventListener>(
				this.listeners);
		for (MethodExecutionEventListener lis : mlis) {
			if (flag == 1) {
				lis.onMethodBegin(event);
			} else if (flag == 2) {
				lis.onMethodEnd(event);
			}
		}
	}

	public void addMethodExecutionListener(MethodExecutionEventListener lis) {
		this.listeners.add(lis);
	}

	public void removeListener(MethodExecutionEventListener lis) {
		this.listeners.remove(lis);
	}

	public void removeAllListeners() {
		this.listeners.clear();
	}

	// 基于J2SE的事件监听管理
	public static void main(String[] args) {
		MethodExecutionPublisher pub = new MethodExecutionPublisher();
		MethodExecutionEventListener lis = new SimpleMethodExecutionEventListenerImpl();
		// 添加事件监听
		pub.addMethodExecutionListener(lis);
		// 发布事件动作
		pub.methodToMonitor();
	}

}
