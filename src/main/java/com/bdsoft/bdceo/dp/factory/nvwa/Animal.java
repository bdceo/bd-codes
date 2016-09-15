package com.bdsoft.bdceo.dp.factory.nvwa;

public interface Animal {

	void eat();

	void sleep();
}

class Bull implements Animal {

	@Override
	public void eat() {
		System.out.println("Bull eat");
	}

	@Override
	public void sleep() {
		System.out.println("Bull sleep");
	}

}

class Cow implements Animal {

	@Override
	public void eat() {
		System.out.println("Cow eat");
	}

	@Override
	public void sleep() {
		System.out.println("Cow sleep");
	}
}
