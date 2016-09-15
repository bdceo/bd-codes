package com.bdsoft.bdceo.thinkinjava.object;

public class OverridTest {

	public int i = 0;

	public int getI() {
		return i;
	}

	private void f() {
		System.out.println("private void f");
	}

	public static void staticGet() {
		System.out.println("Base staticGet()");
	}

	/**
	 * 覆盖 私有方法
	 */
	public static void main(String[] args) {
		System.out.println("子类重写私有方法，父类中的私有方法对子类是不可见的，也就不存在子类重写了");
		OverridTest t = new SubClass();
		t.f(); // private void f

		System.out
				.println("\n继承中的属性不存在多态现象，任何属性的访问操作都由编译器解析，不是多态的，子类访问父类属性，通过super关键字");
		OverridTest sup = new SubClass();
		System.out.println(String.format("sup.i=%d, sup.getI()=%d", sup.i,
				sup.getI()));
		SubClass sub = new SubClass();
		System.out.println(String.format(
				"sub.i=%d, sub.getI()=%d, sub.getSuperI()=%d", sub.i,
				sub.getI(), sub.getSuperI()));

		System.out.println("\n继承中的静态方法也不存在多态现象");
		sup.staticGet();
		sub.staticGet();
	}

}

class SubClass extends OverridTest {

	public int i = 1;

	public int getI() {
		return i;
	}

	public int getSuperI() {
		return super.i;
	}

	// @Override 注解失效，父类的私有方法无法被重写
	public void f() {
		System.out.println("public void f");
	}

	public static void staticGet() {
		System.out.println("Sub staticGet()");
	}
}
