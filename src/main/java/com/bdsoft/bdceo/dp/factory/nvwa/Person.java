package com.bdsoft.bdceo.dp.factory.nvwa;

public interface Person {

	void eat();

	void speak();
}

class Man implements Person {

	@Override
	public void eat() {
		System.out.println("Man eat");
	}

	@Override
	public void speak() {
		System.out.println("Man speak");
	}

}

class Woman implements Person {

	@Override
	public void eat() {
		System.out.println("Woman eat");
	}

	@Override
	public void speak() {
		System.out.println("Woman speak");
	}

}
