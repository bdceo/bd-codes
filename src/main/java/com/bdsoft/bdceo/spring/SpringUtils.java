package com.bdsoft.bdceo.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class SpringUtils {

	// 基于BeanFactory的容器初始化
	public static BeanFactory beanIocStart() {
		return new XmlBeanFactory(new ClassPathResource("spring.xml"));
	}

	// 基于Application的容器初始化
	public static ApplicationContext appIocStart() {
		return new ClassPathXmlApplicationContext("spring.xml");
	}
}
