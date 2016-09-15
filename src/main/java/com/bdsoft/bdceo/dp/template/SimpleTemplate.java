package com.bdsoft.bdceo.dp.template;

public class SimpleTemplate {

	public static void main(String[] args) {
		BaseTemplate tmp = new TemplateImpl();
		tmp.execute();
	}

}

abstract class BaseTemplate {

	// 父类提供核心的模板方法，封装了可供子类直接调用的逻辑
	public final void execute() {
		fun();
		System.out.println("BaseTemplate-execute()");
		method();
	}

	// 具体细节实现，留给子类自己覆写
	protected abstract void fun();

	protected abstract void method();
}

class TemplateImpl extends BaseTemplate {

	@Override
	public void fun() {
		System.out.println("TemplateImpl-fun()");
	}

	@Override
	public void method() {
		System.out.println("TemplateImpl-method()");
	}

}