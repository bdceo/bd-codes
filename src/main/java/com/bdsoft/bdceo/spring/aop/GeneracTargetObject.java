package com.bdsoft.bdceo.spring.aop;

@ClassLevelAnnotation
public class GeneracTargetObject {
	@MethodLevelAnnotation
	public void method1() {
		System.out.println("GeneracTargetObject-->atm===1");
	}

	public void method2() {
		System.out.println("GeneracTargetObject-->atm===2");
	}
}
