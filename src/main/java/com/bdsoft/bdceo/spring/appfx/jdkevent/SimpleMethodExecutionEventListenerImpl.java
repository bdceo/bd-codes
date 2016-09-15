package com.bdsoft.bdceo.spring.appfx.jdkevent;

public class SimpleMethodExecutionEventListenerImpl implements
		MethodExecutionEventListener {

	@Override
	public void onMethodBegin(MethodExecutionEvent event) {
		System.out.println("开始执行：" + event.getMethodName());
	}

	@Override
	public void onMethodEnd(MethodExecutionEvent event) {
		System.out.println("执行完毕：" + event.getMethodName());
	}

}
