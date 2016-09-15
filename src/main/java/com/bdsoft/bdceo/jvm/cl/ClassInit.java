package com.bdsoft.bdceo.jvm.cl;

public class ClassInit {

	static {
		i = 2;
		// System.out.println(i);
		// 定义在静态语句块之后的类变量，只能赋值，不能访问！！！
	}
	static int i = 1;

	static class Parent {
		// 类初始化过程，调用clinit方法的执行逻辑：顺序对类变量进行初始化
		public static int A = 1;// 先执行
		static {
			A = 2;// 后执行
		}
	}

	static class Sub extends Parent {
		public static int B = A; // 2
	}

	public static void main(String[] args) {
		System.out.println(Sub.B); // 2
	}

}
