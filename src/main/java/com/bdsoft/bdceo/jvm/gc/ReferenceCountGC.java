package com.bdsoft.bdceo.jvm.gc;

public class ReferenceCountGC {

	public Object instance = null;

	private static final int _1MB = 1024 * 1024;
	private byte[] bigSize = new byte[2 * _1MB];

	public static void main(String[] args) {
		// 引用计数算法的缺陷：对象之间相互循环引用
		testGC();
	}

	public static void testGC() {
		ReferenceCountGC objA = new ReferenceCountGC();
		ReferenceCountGC objB = new ReferenceCountGC();
		objA.instance = objB;
		objB.instance = objA;

		objA = null;
		objB = null;

		System.gc();
	}

}
