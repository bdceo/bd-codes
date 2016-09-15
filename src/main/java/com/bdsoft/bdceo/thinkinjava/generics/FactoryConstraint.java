/**
 * FactoryConstraint.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.generics;

/**
 * 泛型，创建类型实例
 * 
 * @author bdceo
 * @date 2016-8-29 下午12:44:58
 * @version V1.0
 */
public class FactoryConstraint {

	/**
	 * 入口
	 */
	public static void main(String[] args) {
		// 工厂方法，避免构造的反省对象没有默认构造函数，无法使用newInstance方法创建
		new Foo<Integer>(new IntegerFactory());
		new Foo<Widget>(new Widget.Factory());

		// 模板方法
		new Creator().f();
	}

}

// 泛型工厂接口定义
interface IFactory<T> {
	T create();
}

// 泛型工厂方法
class Foo<T> {
	private T x;

	public <F extends IFactory<T>> Foo(F factory) {
		this.x = factory.create();
	}
}

class IntegerFactory implements IFactory<Integer> {
	public Integer create() {
		return new Integer(0);
	}
}

class Widget {
	public static class Factory implements IFactory<Widget> {
		public Widget create() {
			return new Widget();
		}
	}
}

// 模板方法
abstract class GenericWithCreate<T> {
	final T ele;

	public GenericWithCreate() {
		ele = create();
	}

	abstract T create();
}

class Xxx {
}

class Creator extends GenericWithCreate<Xxx> {
	Xxx create() {
		return new Xxx();
	}

	void f() {
		System.out.println(ele.getClass().getSimpleName());
	}
}