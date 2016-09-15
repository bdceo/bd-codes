package com.bdsoft.bdceo.dp.singleton;

public class Singleton {

	public static void main(String[] args) {
		Single s = Single.getInstance();
		s.say();
	}
}

class Single {

	// 最好是声明为final不可修改，且直接为变量初始化，而不是在getInstance方法内部，避免线程问题
	private static final Single s = new Single();

	// 将public改为private，使得实例化操作只能在类内部完成
	private Single() {
	}

	public static Single getInstance() {
		return s;
	}

	public void say() {
		System.out.println("singleton pattern");
	}
}

class Single2 {

	// 内部类只被加载一次
	static class Instance {
		 static Single2 instance = new Single2();
	}

	private Single2() {
	}

	public static Single2 getInstance() {
		return Instance.instance;
	}

}
