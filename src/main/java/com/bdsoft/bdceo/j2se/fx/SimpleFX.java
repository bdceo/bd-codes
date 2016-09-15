package com.bdsoft.bdceo.j2se.fx;

import java.util.ArrayList;
import java.util.List;

public class SimpleFX {

	/**
	 * 泛型基本使用:把类型定义为参数，即泛型化
	 * 
	 * 尖括号相当于泛型类型声明，然后程序才可引用
	 */
	public static void main(String[] args) {
		// 泛型类
		FXDemo<String, Integer> fx = new FXDemo<String, Integer>();
		fx.init("bdceo", 25);
		System.out.println(fx.getPro1());
		System.out.println(fx.getPro2());

		// 泛型方法
		String[] strArr = new String[] { "java1.4", "java5", "java6", "java7" };
		// Integer last = getLastElement(strArr);// 编译无法通过，自动检测类型
		String last = getLastElement(strArr);

		System.out.println(last);

		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			list.add(new String("ele" + i));
		}
		printList(list);
	}

	// 获取任何类型数组的最后一个元素
	public static <T> T getLastElement(T[] arr) {
		return arr[arr.length - 1];
	}

	// 泛型参数的通配符？，可以解决泛型继承与普通类继承的不一致问题:将？换成Object，编译出错
	public static void printList(List<?> list) {
		System.out.println(list);
	}

}

// 泛型类定义：类名后跟尖括号，定义泛型参数
class FXDemo<T, U extends Number> {
	private T pro1;
	private U pro2;

	public void init(T v1, U v2) {
		this.pro1 = v1;
		this.pro2 = v2;
	}

	public T getPro1() {
		return pro1;
	}

	public void setPro1(T pro1) {
		this.pro1 = pro1;
	}

	public U getPro2() {
		return pro2;
	}

	public void setPro2(U pro2) {
		this.pro2 = pro2;
	}
}