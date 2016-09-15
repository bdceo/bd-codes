package com.bdsoft.bdceo.j2se.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class SimpleReflect {

	public static void main(String[] args) throws Exception {
		Class cls = Class.forName("com.bdsoft.bdceo.j2se.reflect.Common");
		Common c = (Common) cls.newInstance();

		// Jpa框架，反射获取dao的操作对象结合泛型测试：
		Dao dao = new Dao();
	}

}

interface IJpa<T> {
}

class Jpa<T> implements IJpa<T> {
	public Jpa() {
		Type type = this.getClass().getGenericSuperclass();
		System.out.println("父类：" + type);
		Class<?> t = (Class<?>) ((ParameterizedType) type)
				.getActualTypeArguments()[0];
		System.out.println("泛型：" + t);
	}
}

interface IDao extends IJpa<Base> {
}

class Dao extends Jpa<Base> implements IDao {
}

class Base {
	private String name;

	public void execute() {
		System.out.println("Base.execute");
	}

	public String getName() {
		return this.name;
	}

}

class Common extends Base {
	private int age;

	public void say() {
		System.out.println("Common.say");
	}

	public int getAge() {
		return this.age;
	}
}
