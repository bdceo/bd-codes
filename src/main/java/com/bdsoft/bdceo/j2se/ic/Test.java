package com.bdsoft.bdceo.j2se.ic;

// 内部类测试
public class Test {

	private int id = 4;

	private static int code = 200;

	private InnerInter ii;

	public static void main(String[] args) {
		System.out.println("外部类-实例方法内，创建非静态内部类");
		Test t = new Test();
		t.crtInClass();
		System.out.println("----------------------");

		System.out.println("外部类-类方法内，创建非静态内部类对象");
		InClass icClass = new Test().new InClass();
		// InClass ic = t.new InClass();
		icClass.dis();
		icClass.show();
		System.out.println("----------------------");

		System.out.println("外部类，实例方法中的内部类——局部内部类");
		t.innerMethod();
		t.ii.answer();
		System.out.println("----------------------");

		System.out.println("外部类，类方法中的内部类——局部内部类");
		Test.staticInMethod();
		System.out.println("----------------------");

		System.out.println("外部类,类方法中创建静态内部类");
		InClass4 ic4 = new InClass4();
		ic4.show();
		System.out.println("----------------------");

		System.out.println("匿名内部类");
		SuperClass ic5 = new SuperClass() {
			{
				sid = (int) (Math.random() * 100);
			}

			public void show() {
				System.out.println("SuperClass子类中的show方法，初始化成员：" + sid);
			}
		};
		ic5.show();
		System.out.println("----------------------");

		System.out.println("匿名内部接口");
		InnerInter ic5_2 = new InnerInter() {
			public void answer() {
				System.out.println("InnerInter实现类中的answer方法");
			}
		};
		ic5_2.answer();

		System.exit(1);
	}

	// 外部类的实例方法里创建内部类对象
	public void crtInClass() {
		// 实例方法调用前，内部类已加载，实例化内部类不用再指定外部类
		InClass ic = new InClass();
		ic.dis();
		ic.show();
		System.out.println("外部类 - 访问非静态内部类成员：" + ic.id);

		InClass4 ic4 = new InClass4();
		ic4.show();
	}

	// 非静态内部类：方法和属性定义跟普通外部类一样，只是访问外部类属性时特殊
	class InClass {
		private int id = 0;

		// public static String a = "s";
		public void dis() {
			System.out.println("非静态内部类 - 方法dis");
		}

		// 访问外部类属性：[外部类.this.属性]
		private void show() {
			System.out.println("非静态内部类 - 访问外部类属性[外部类.this.属性]：" + Test.this.id);
			System.out.println("非静态内部类 - 访问自身属性[this.属性]：" + this.id);
		}
	}

	// 局部内部类
	public void innerMethod() {
		final int CODE = 404;
		class InClass2 implements InnerInter {
			public void show() {
				System.out
						.println("局部内部类 - 访问外部类属性：" + Test.this.id + "/" + id);
				System.out.println("局部内部类 - 访问局部属性：" + CODE);
			}

			public void answer() {
				System.out
						.println("局部内部类 - Test.innerMethod().InClass2.answer()");
			}
		}
		InClass2 ic2 = new InClass2();
		ic2.show();
		ii = ic2;
	}

	// 静态方法中的内部类
	public static void staticInMethod() {
		class InClass3 {
			public void show() {
				System.out.println("静态方法中的局部内部类 - 访问外部类静态属性：" + Test.code + "/"
						+ code);
			}
		}
		InClass3 i3 = new InClass3();
		i3.show();
	}

	// 静态内部类
	static class InClass4 {
		public void show() {
			System.out.println("静态内部类 - 方法show");
		}
	}
}

interface InnerInter {
	public void answer();
}

class SuperClass {
	int sid;

	public void show() {
		System.out.println("SuperClass的show方法");
	}
}
