package com.bdsoft.bdceo.jvm.gc;

public class ObjectAllocation {

	private static final int _1MB = 1024 * 1024;

	public static void main(String[] args) {
		// testAllocation();
		// testPretenureSizeThreshold();
//		testTenuringThreshold();
		testTenuringThreshold2();
	}

	// 测试对象分配，默认在YoungGen，不足时，会发生MinorGC
	// -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails
	// -XX:SurvivorRatio=8
	public static void testAllocation() {
		byte[] al1, al2, al3, al4;
		al1 = new byte[2 * _1MB];
		al2 = new byte[2 * _1MB];
		al3 = new byte[5 * _1MB];
		al4 = new byte[4 * _1MB]; // 出现一次Minor GC
	}

	// 测试老年代直接分配阀值
	// XX:PretenureSizeThreshold 只对Serial和ParNew两款收集器有效
	// -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails
	// -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
	public static void testPretenureSizeThreshold() {
		byte[] al;
		al = new byte[5 * _1MB];
	}

	// 测试对象年龄阀值,分别测试XX:MaxTenuringThreshold=1或15
	// -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails
	// -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1
	public static void testTenuringThreshold() {
		byte[] al1, al2, al3;
		al1 = new byte[_1MB / 4];
		al2 = new byte[4 * _1MB];
		al3 = new byte[4 * _1MB];
		// al3 = null;
		// al3 = new byte[4 * _1MB];
	}

	// 动态对象年龄判断,分别测试XX:MaxTenuringThreshold=1或15
	// -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15
	public static void testTenuringThreshold2() {
		byte[] al1, al2, al3, al4;
		// al1+al2大于survivo空间一半
		al1 = new byte[_1MB / 4];
		al2 = new byte[_1MB / 4];

		al3 = new byte[4 * _1MB];
		al4 = new byte[4 * _1MB];
	}
}
