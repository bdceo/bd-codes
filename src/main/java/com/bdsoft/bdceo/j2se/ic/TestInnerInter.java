package com.bdsoft.bdceo.j2se.ic;

public class TestInnerInter {

	// 测试内部接口继承实现
	public static void main(String[] args) {
		TestInnerInter tii = new TestInnerInter();
		TestInnerInter.InnerInter ii = tii.getIni();
		ii.show();// 非静态内部类实现内部接口

		ii = new Other();
		ii.show();// 外部类实现内部接口

		OutInter.InInter oii = new InImpl();
		oii.inShow();// 接口中的内部接口

		OutInter oi = new OutImpl();
		oi.outShow();// 普通接口实现
	}

	public InnerInter getIni() {
		return new InClass();
	}

	// 内部接口
	public interface InnerInter {
		public void show();
	}

	class InClass implements InnerInter {
		public void show() {
			System.out.println("非静态内部类实现内部接口");
		}
	}
}

class Other implements TestInnerInter.InnerInter {
	public void show() {
		System.out.println("外部类实现内部接口");
	}
}

class OutImpl implements OutInter {
	public void outShow() {
		System.out.println("普通接口实现");
	}
}

class InImpl implements OutInter.InInter {
	public void inShow() {
		System.out.println("接口中的内部接口");
	}
}

interface OutInter {

	interface InInter {
		public void inShow();
	}

	public void outShow();
}
