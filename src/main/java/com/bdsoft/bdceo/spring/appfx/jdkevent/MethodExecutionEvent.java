package com.bdsoft.bdceo.spring.appfx.jdkevent;

import java.util.EventObject;

public class MethodExecutionEvent extends EventObject {

	private String methodName;

	public MethodExecutionEvent(Object source) {
		super(source);
	}

	public MethodExecutionEvent(Object source, String methodName) {
		super(source);
		this.methodName = methodName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6201324575019696678L;

}
