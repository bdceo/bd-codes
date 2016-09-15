package com.bdsoft.bdceo.dp.strategy;

public class SimpleStrategy {

	public static void main(String[] a) {
		Context c1 = new Context(new ConcreteStrategyA());
		c1.contextDo();

		System.out.println();

		Context c2 = new Context(new ConcreteStrategyB());
		c2.contextDo();
	}
}

// 算法抽象
abstract class Strategy {
	public abstract void suanfa();
}

// 真实算法实现
class ConcreteStrategyA extends Strategy {
	public void suanfa() {
		System.out.println("ConcreteStrategyA.suanfa();");
	}
}

class ConcreteStrategyB extends Strategy {
	public void suanfa() {
		System.out.println("ConcreteStrategyB.suanfa();");
	}
}

// 算法使用环境，上下文
class Context {

	// 算法的抽象引用
	private Strategy st;

	// 此处决定使用哪个具体的算法
	public Context(Strategy st) {
		this.st = st;
	}

	public void contextDo() {
		this.st.suanfa();
	}
}