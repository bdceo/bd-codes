package com.bdsoft.bdceo.jvm.bc;

public class LocalVarTableTest {

	/**
	 * -verbose:gc
	 */
	public static void main(String[] args) {
		int a;
		// 局部变量未初始化，不能使用
		// System.out.println(a);

		reuseSlot1();
		reuseSlot2();
		reuseSlot3();
	}

	// 分配12M内存空间后调用gc，此时GC Roots依然保持数组对象引用链，不会发起回收
	public static void reuseSlot1() {
		byte[] placeholder = new byte[12 * 1024 * 1024];
		System.gc();
		System.out.println();
	}

	// 虽然退出作用域范围，但是后续并未对局部变量表有读写操作，导致数组对象引用在GCRoots中依然存在
	public static void reuseSlot2() {
		{
			byte[] placeholder = new byte[12 * 1024 * 1024];
		}
		System.gc();
		System.out.println();
	}

	// 修改了局部变量表，发生回收
	public static void reuseSlot3() {
		{
			byte[] placeholder = new byte[12 * 1024 * 1024];
		}
		int a = 4;
		System.gc();
	}

}
