/**
 * AutoTester.java
 * com.bdsoft.bdceo.refactor
 * Copyright (c) 2014, 北京微课创景教育科技有限公司版权所有.
*/
package com.bdsoft.bdceo.refactor;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * 自动化测试入口，通过suite方法，添加需要运行的测试类
 * 
 * @author	丁辰叶
 * @date	2014-11-12
 */
public class AutoTester {

	/**
	 * 启动
	 * @param args
	 */
	public static void main(String[] args) {
//		TestRunner.run(suiteMethod()); 
		
		TestRunner.run(suiteClass());
	}

	/**
	 * 配置 JUnit测试套件，自动将类里的test开头方法执行单元测试
	 * @return
	 */
	public static Test suiteClass() {
		TestSuite suite = new TestSuite(FileReaderTester.class);
		return suite;
	}
	
	/**
	 * 配置 JUnit测试套件，通过反射，针对类的指定方法进行测试
	 * @return
	 */
	public static Test suiteMethod() {
		TestSuite suite = new TestSuite();
		suite.addTest(new FileReaderTester("testRead"));
		suite.addTest(new FileReaderTester("testReadAtEnd"));
		return suite;
	}

}
