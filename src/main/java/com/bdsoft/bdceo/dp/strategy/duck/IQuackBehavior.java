package com.bdsoft.bdceo.dp.strategy.duck;

public interface IQuackBehavior {
	public String quack();
}

class QuackOne implements IQuackBehavior {

	public String quack() {
		return "GAGAGA";
	}

}

class QuackThree implements IQuackBehavior {

	public String quack() {
		return "gagaga";
	}

}

class QuackTwo implements IQuackBehavior {

	public String quack() {
		return "ga...ga...ga";
	}

}