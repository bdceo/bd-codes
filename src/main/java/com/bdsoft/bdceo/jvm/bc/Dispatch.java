package com.bdsoft.bdceo.jvm.bc;

public class Dispatch {

	static class QQ {
	}

	static class Ali {
	}

	public static class Fat {

		public void hardChoice(QQ arg) {
			System.out.println("father choose qq");
		}

		// 方法重载：静态分派，多分派：依据静态类型和方法参数
		public void hardChoice(Ali arg) {
			System.out.println("fatcher choose alibaba");
		}
	}

	public static class Son extends Fat {
		// 方法重写：动态分派，但分派，编译器已确定实际类型-方法接受者，所以只关注方法参数
		public void hardChoice(QQ arg) {
			System.out.println("son choose qq");
		}

		public void hardChoice(Ali arg) {
			System.out.println("son choose alibaba");
		}
	}

	// 单分派，多分派演示：宗量【方法接收者+方法参数】
	public static void main(String[] args) {
		Fat fat = new Fat();
		Fat son = new Son();

		fat.hardChoice(new QQ());
		son.hardChoice(new Ali());
	}
}
