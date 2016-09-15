package com.bdsoft.bdceo.dp.factory.method;

public class FactoryMethod {

	// 工厂方法演示
	// 参考：http://haolloyin.blog.51cto.com/1177454/332576
	// http://alaric.iteye.com/blog/1908876
	public static void main(String[] args) {
		IProduct p = null;

		Factory fat = new ConcreteFactory();

		p = fat.createProduct(ConcreteProductA.class);
		p.fun1();

		p = fat.createProduct(ConcreteProductB.class);
		p.fun2();
	}

}

// 产品定义
interface IProduct {

	void fun1();

	void fun2();
}

// 产品A实现
class ConcreteProductA implements IProduct {

	public void fun1() {
		System.out.println(this.getClass().getSimpleName() + "-->fun1");
	}

	public void fun2() {
		System.out.println(this.getClass().getSimpleName() + "-->fun2");
	}
}

// 产品B实现
class ConcreteProductB implements IProduct {

	public void fun1() {
		System.out.println(this.getClass().getSimpleName() + "-->fun1");
	}

	public void fun2() {
		System.out.println(this.getClass().getSimpleName() + "-->fun2");
	}
}

// 工厂方法，抽象类，定义创建对象的接口，
abstract class Factory {
	abstract <T extends IProduct> T createProduct(Class<T> c);
}

// 派生类，实现具体的对象创建逻辑
class ConcreteFactory extends Factory {

	public <T extends IProduct> T createProduct(Class<T> c) {
		T product = null;
		try {
			product = (T) Class.forName(c.getName()).newInstance();
		} catch (Exception e) {
		}
		return product;
	}
}
