package com.bdsoft.bdceo.dp.adapter;

// 模拟吃东西的抽象
interface Eat {
	public void eatBread();

	public void eatApple();

	public void eatBunana();
}

// 对吃的所有默认实现
abstract class WhoEat implements Eat {
	public void eatBread() {
		System.out.println("eat bread");
	}

	public void eatApple() {
		System.out.println("eat apple");
	}

	public void eatBunana() {
		System.out.println("eat bunana");
	}
}

// 不同物种可能对吃有不同的实现
class Persons extends WhoEat {
	public void eatBread() {
		System.out.println("person eat bread");
	}
}

class Moncks extends WhoEat {
	public void eatBunana() {
		System.out.println("monck eat bunana");
	}
}

public class SimpleDemo {

	public static void main(String[] args) {
		WhoEat p = new Persons();
		p.eatBread();
		p.eatApple();
		System.out.println();
		p = new Moncks();
		p.eatBread();
		p.eatBunana();
	}
}
