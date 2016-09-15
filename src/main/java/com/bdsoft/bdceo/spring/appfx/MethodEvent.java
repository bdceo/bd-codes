package com.bdsoft.bdceo.spring.appfx;

import org.springframework.context.ApplicationEvent;

public class MethodEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3404400367846961518L;

	private String method;
	private int flag;

	public MethodEvent(Object source) {
		super(source);
	}

	public MethodEvent(Object source, String method, int flag) {
		super(source);
		this.method = method;
		this.flag = flag;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

}
