package com.bdsoft.bdceo.spring.appfx;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

public class MethodEventPubllish implements ApplicationEventPublisherAware {

	private ApplicationEventPublisher eventPublish;

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher pub) {
		this.eventPublish = pub;
	}

	public void methodToMonitor() {
		MethodEvent evt = new MethodEvent(this, "methodToMonitor", 1);
		this.eventPublish.publishEvent(evt);
		System.out.println("业务方法执行");
		evt = new MethodEvent(this, "methodToMonitor", 2);
		this.eventPublish.publishEvent(evt);
	}

}
