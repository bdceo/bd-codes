package com.bdsoft.bdceo.dp.adapter;

public class EachAdapter {

	// 双向适配器，采用组合方法，在一个新的组合类里同时拥有不同实现的引用
	public static void main(String[] args) {
		// Frame1 f1 = new Frame1();
		// Frame2 f2 = new Frame2();

		// 接口
		F1 f1 = new Frame1();
		F2 f2 = new Frame2();

		Fram f = new Fram(f1, f2);
		f.newChn();
		f.newRed();
	}

}

// 面向接口编程
interface F1 {
	void red();
}

interface F2 {
	void chn();
}

class Frame1 implements F1 {
	public void red() {
		System.out.println("Frame1.red");
	}
}

class Frame2 implements F2 {
	public void chn() {
		System.out.println("Frame2.chn");
	}
}

class Fram implements F1, F2 {
	// private Frame1 f1;
	private F1 f1;
	// private Frame2 f2;
	private F2 f2;

	// public Fram(Frame1 f1, Frame2 f2) {
	public Fram(F1 f1, F2 f2) {
		this.f1 = f1;
		this.f2 = f2;
	}

	public void newChn() {
		// f1.red();
		f2.chn();
	}

	public void newRed() {
		// f2.chn();
		f1.red();
	}

	public void red() {
		f1.red();
	}

	public void chn() {
		f2.chn();
	}
}