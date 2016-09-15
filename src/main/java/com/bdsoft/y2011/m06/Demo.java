package com.bdsoft.y2011.m06;

public class Demo {

	private final static D ss = D.getInstance();

	public static void main(String[] args) {
		System.out.println(ss.s);
		//D.getInstance().s = "ff";
		ss.s="ff";
		 
		System.out.println(ss.s);
	}

}

class D {

	// 如果这个ｓ是一个对象，那么就可一修改这个对象了
	public String s = "s";

	public D() {

	}

	private static D d = new D();

	public static synchronized D getInstance() {
		return d;
	}

}
