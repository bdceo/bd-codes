package com.bdsoft.bdceo.spring.aop;

public class CounterImpl implements ICounter {

	private int counter;

	@Override
	public void resetCounter() {
		counter = 4;
	}

	@Override
	public int getCounter() {
		counter++;
		return counter;
	}

}
