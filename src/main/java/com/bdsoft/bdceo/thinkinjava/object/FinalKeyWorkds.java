package com.bdsoft.bdceo.thinkinjava.object;

public class FinalKeyWorkds {

	// 静态final，必须定义时初始化或在类加载被引用时通过static代码块初始化
	static final int a;

	// 普通final，必须在定义时或构造函数第一行进行初始化
	final int b;

	static {
		a = 1;
	}

	public FinalKeyWorkds() {
		super();
		b = 2;
	}
 
	public static void main(String[] args) {
		
		
		
	}

}
