package com.chanceol.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Random;

/**
 * 反射工具测试
 * 
 * @author 丁辰叶
 */
public class ConversionUtils {

	private int age = 28;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * 测试反射方法调用
	 */
	public static void main(String[] args) throws Exception {

		// 目标对象
		ConversionUtils obj = new ConversionUtils();

		Class ref = obj.getClass();

		// 获取指定getter方法，并调用
		Method getter = ref.getDeclaredMethod("getAge", null);
		Object val = getter.invoke(obj, null);
		System.out.println(val);

		// 获取指定setter方法，并调用，之后调用getter方法查看值的变化
		Method setter = ref.getDeclaredMethod("setAge", int.class);
		setter.invoke(obj, new Random().nextInt(100));
		val = getter.invoke(obj, null);
		System.out.println(val);

		// 属性类型
		Field pro = ref.getDeclaredField("age");
		String proType = pro.getType().getCanonicalName();
		System.out.println(proType);

	}
}
