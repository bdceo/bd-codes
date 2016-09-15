package com.bdsoft.bdceo.spring.aop.schema;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class SchemaBaseAspect {

	public void doBefore(JoinPoint jp) {
		System.out.println("SchemaBaseAspect-->doBefore");
	}

	public void doAfterReturning(JoinPoint jp) {
		System.out.println("SchemaBaseAspect-->doAfterReturning");
	}

	public void doAfterThrowing(RuntimeException e) {
		System.out.println("SchemaBaseAspect-->doAfterThrowing");
		System.out.println("在advice中进行统一异常处理");
	}

	public void doAfter() {
		System.out.println("SchemaBaseAspect-->doAfter");
	}

	public Object doProfile(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("SchemaBaseAspect-->doProfile-start");
		try {
			return pjp.proceed();
		} finally {
			System.out.println("SchemaBaseAspect-->doProfile-end");
		}
	}
}
