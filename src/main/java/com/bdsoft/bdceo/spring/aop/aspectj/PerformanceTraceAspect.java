package com.bdsoft.bdceo.spring.aop.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;

import com.bdsoft.bdceo.spring.aop.CounterImpl;
import com.bdsoft.bdceo.spring.aop.ICounter;

@Aspect
public class PerformanceTraceAspect {

	// Pointcut定义
	@Pointcut("execution(public void *.method1()) || execution(public void *.method2())")
	public void pointcutName() {
	}

	@Pointcut("execution(public void *.dox())")
	private void pointcutTest() {
	}

	@Pointcut("pointcutTest() && com.bdsoft.bdceo.spring.aop.aspectj.SystemCommonsAspect.p1()")
	public void pointcutAnd() {
	}

	// Advice定义
	@Before("execution(boolean *.xxaa())")
	public void setupResourceBefore() throws Throwable {
		// before-advice，资源准备处理
	}

	@AfterThrowing(pointcut = "execution(boolean *.xxee(String,..))", throwing = "e")
	public void afterThrowing(RuntimeException e) {
		// after-throwing-advice，处理异常
	}

	@AfterReturning("execution(boolean *.aaoo(String,..))")
	public void taskExecutionCommpleted(JoinPoint jp) {
		// after-returning-advice，处理方法放回前的处理
	}

	@After("execution(boolean *.aexzzz(String,..))")
	public void cleanUpResourceIfnecessary() throws Throwable {
		// after-advice，资源回收释放
	}

	@Around("pointcutName()")
	public Object performanceTrace(ProceedingJoinPoint joinPoint)
			throws Throwable {
		joinPoint.getArgs();// 访问joinpoint处的参数
		try {
			// TODO 监控系统运行性能

			return joinPoint.proceed();// 返回操作后的值

		} finally {
			// TODO 停止检测，记录日志等信息
		}
	}

	// Introduction型Advice定义
	@DeclareParents(value = "com.bdsoft.bdceo.spring.aop.MockTask", defaultImpl = CounterImpl.class)
	public ICounter counter;

}

@Aspect
class SystemCommonsAspect {
	@Pointcut("execution(* doxxxxxx(..))")
	public void p1() {
	}

	@Pointcut("execution(* *sexxxxxxx(..))")
	public void p2() {
	}
}
