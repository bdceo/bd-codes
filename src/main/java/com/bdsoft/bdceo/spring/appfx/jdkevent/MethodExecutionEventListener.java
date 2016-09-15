package com.bdsoft.bdceo.spring.appfx.jdkevent;

import java.util.EventListener;

public interface MethodExecutionEventListener extends EventListener {

	void onMethodBegin(MethodExecutionEvent event);

	void onMethodEnd(MethodExecutionEvent event);

}
