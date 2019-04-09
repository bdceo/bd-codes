/**
 * TestAnnotation.java
 * com.bdsoft.bdceo.j2se.ano
 * Copyright (c) 2016, 北京微课创景教育科技有限公司版权所有.
*/

package com.bdsoft.bdceo.j2se.ano;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 注解测试
 *
 * @author   丁辰叶
 * @date	 2016-6-14
 * @version  1.0.0
 */
@BDAno(name = "class-testAnnotation", desc = "一个类")
public class TestAnnotation {

	@BDAno(name = "class-variable", desc = "私有属性")
	private String var;

	@BDAno(name = "class-constructor", desc = "默认构造函数")
	public TestAnnotation() {
	}

	@BDAno(name = "atm-main", desc = "入口方法")
	public static void main(@BDAno(name = "main-args", desc = "运行参数") String[] args) throws Exception {
		boolean hasBdAno = false;
		Class cs = TestAnnotation.class;
		showAns(cs.getAnnotations());

		Constructor<TestAnnotation> cc = cs.getConstructor(null);
		hasBdAno = cc.isAnnotationPresent(BDAno.class);
		showAns(cc.getAnnotations());

		Method mm = cs.getMethod("main", String[].class);
		hasBdAno = mm.isAnnotationPresent(BDAno.class);
		showAns(mm.getAnnotations());

		Annotation[][] ans = mm.getParameterAnnotations();
		for (Annotation[] an : ans) {
			showAns(an);
		}

		Field f = cs.getDeclaredField("var");
		showAns(f.getAnnotations());
	}

	static void showAns(Annotation[] ans) {
		for (Annotation an : ans) {
			if (an instanceof BDAno) {
				BDAno bd = (BDAno) an;
				System.out.println(bd.name() + "," + bd.desc());
			}
		}
	}
}
