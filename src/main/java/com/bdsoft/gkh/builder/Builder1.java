package com.bdsoft.gkh.builder;

import java.awt.Panel;

public class Builder1 {
	public static void main(String[] args) {
	}
}

// 对象实例化、构造，组装所需步骤
interface IBuilder {
	public Panel getPanel1();

	public Panel getPanel2();

	public Panel getPanel3();

	public Panel getPanel4();
}

// 有四个小模块组成
class Builder implements IBuilder {// 负责各个模块的生成
	public Panel getPanel1() {// 以后修改的话，只需要修改相应的函数
		Panel p1 = null;
		// 建造p1
		return p1;
	}

	public Panel getPanel2() {
		Panel p2 = null;
		// 建造p2
		return p2;
	}

	public Panel getPanel3() {
		Panel p3 = null;
		// 建造p3
		return p3;
	}

	public Panel getPanel4() {
		Panel p4 = null;
		// 建造p4
		return p4;
	}
}

class Director {
	private IBuilder b = null;// 用Spring做注入

	public Director(IBuilder b) {
		this.b = b;
	}

	// 复杂对象构造，简单化
	public void contruct() {
		Panel p1 = b.getPanel1();
		Panel p2 = b.getPanel2();
		Panel p3 = b.getPanel3();
		Panel p4 = b.getPanel4();
		// 组装
	}
}
