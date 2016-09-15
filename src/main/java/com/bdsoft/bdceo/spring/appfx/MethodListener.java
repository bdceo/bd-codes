package com.bdsoft.bdceo.spring.appfx;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class MethodListener implements ApplicationListener {

	@Override
	public void onApplicationEvent(ApplicationEvent evt) {
		if (evt instanceof MethodEvent) {
			System.out.println("方法事件触发并执行了-->" + ((MethodEvent) evt).getFlag());
		}
	}

}
