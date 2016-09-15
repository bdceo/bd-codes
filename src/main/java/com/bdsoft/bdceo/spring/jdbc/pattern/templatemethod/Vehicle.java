package com.bdsoft.bdceo.spring.jdbc.pattern.templatemethod;

// 模板方法模式：汽车启动

// 抽象模板类
public abstract class Vehicle {

	// final的模板方法：方法内逻辑不可更改
	public final void drive() {
		startTheEngine();// 启动
		putIntoGear();// 挂档
		looseHandBrake();// 手刹
		stepOnTheGasAndGo();// 踩油门
	}

	// 抽象方法：自动挡和手动挡的区别，留给子类自己实现
	protected abstract void putIntoGear();

	private void stepOnTheGasAndGo() {
	}

	private void looseHandBrake() {
	}

	private void startTheEngine() {
	}
}
