package com.bdsoft.bdceo.dp.strategy.duck;

public class DuckMain {

	public static void main(String[] ss) {
		MallardDuck md = new MallardDuck();
		RedheadDuck red = new RedheadDuck();
		RubberDuck rud = new RubberDuck();

		System.out.println(md.display() + "--" + md.performFly() + "--"
				+ md.performQuack());
		
		System.out.println(red.display() + "--" + red.performFly() + "--"
				+ red.performQuack());
		
		System.out.println(rud.display() + "--" + rud.performFly() + "--"
				+ rud.performQuack());

		System.exit(1);
	}
}

// 鸭子的抽象类
abstract class Duck {

	// 具体行为的抽象引用
	private IFlyBehavior flyBehavior;
	private IQuackBehavior quackBehavior;

	// 在构造时，指定具体的行为实现，相当于上下文定义
	public Duck(IFlyBehavior fly, IQuackBehavior quack) {
		flyBehavior = fly;
		quackBehavior = quack;
	}

	public String swim() {
		return "All kinds of duck can swim.";
	}

	public abstract String display();

	public String performFly() {
		return flyBehavior.fly();
	}

	public String performQuack() {
		return quackBehavior.quack();
	}

}

// 野鸭
class MallardDuck extends Duck {

	public MallardDuck() {
		super(new FlyWithWings(), new QuackOne());
	}

	public String display() {
		return "I am a Mallard-duck";
	}

}

// 红头鸭
class RedheadDuck extends Duck {

	public RedheadDuck() {
		super(new FlyWithWings(), new QuackTwo());
	}

	public String display() {
		return "I am a Redhead-duck";
	}

}

// 橡皮鸭
class RubberDuck extends Duck {

	public RubberDuck() {
		super(new FlyNoWay(), new QuackThree());
	}

	public String display() {
		return "I am a Rubber-duck";
	}

}
