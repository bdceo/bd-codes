package com.bdsoft.bdceo.spring.aop;

public class MockTask implements ITask {

	@Override
	public void execute(Object obj) {
		System.out.println("MockTask.execute");
	}

}
