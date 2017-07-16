/**
 * TestAnnotationExtend.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.j2se.ano;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TestAnnotationExtend
 * 
 * @author bdceo
 * @date 2017-2-12 下午9:14:13
 * @version V1.0
 */
public class TestAnnotationExtend {

	public static void main(String[] args) {

		Annotation[] ians = IOrderService.class.getAnnotations();
		for (Annotation an : ians) {
			System.out.println(an.annotationType());
		}

		System.out.println("-----------------");
		
		IOrderService service = new OrderServiceImpl();
		System.out.println(service.getClass());
		Annotation[] ans = service.getClass().getAnnotations();
		for (Annotation an : ans) {
			System.out.println(an.annotationType());
		}
	}

	@ExtObjectAnno("interface")
	interface IOrderService {
		void info();
	}

	@ExtObjectAnno2("abstract")
	static abstract class AOrderService {
	}

	static class OrderServiceImpl extends AOrderService implements IOrderService {

		@ExtMethodAnno("implement")
		@Override
		public void info() {
			System.out.println("impl");
		}
	}

	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface ExtMethodAnno {
		public String value();
	}

	@Target(ElementType.TYPE)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface ExtObjectAnno {
		public String value();
	}

	@Target(ElementType.TYPE)
	@ExtObjectAnno("ExtObjectAnno2")
	@Inherited
	public @interface ExtObjectAnno2 {
		public String value();
	}

}

