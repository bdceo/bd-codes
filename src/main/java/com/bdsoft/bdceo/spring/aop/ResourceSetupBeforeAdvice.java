package com.bdsoft.bdceo.spring.aop;

import java.lang.reflect.Method;

import org.aspectj.util.FileUtil;
import org.springframework.core.io.Resource;

public class ResourceSetupBeforeAdvice {//implements MethodBeforeAdvice {

	private Resource resource;

	public ResourceSetupBeforeAdvice() {
	}

	public ResourceSetupBeforeAdvice(Resource res) {
		this.resource = res;
	}

//	@Override
//	public void before(Method atm, Object[] args, Object target)
//			throws Throwable {
//		// 资源初始化，准备工作适合次advice的横切逻辑添加
//		if (!resource.exists()) {
//			// 创建路径
//		}
//	}

}
