package com.bdsoft.bdceo.thinkinjava.object;

import java.util.Date;

/**
 * A class
 * 
 * <pre>
 * 	This class just show a basic java program
 *  it used :
 *     <ol>
 *  	<li>System.out</li>
 *  	<li>java.util.Date</li>
 *  	<li>System.getProperties</li>
 *  	<li>String.format</li>
 *     </ol>
 * </pre>
 * 
 * @author	丁辰叶
 * @date	2014-11-11
 * @version 1.0
 */
public class HelloObject {
	
	/**
	 * A field 
	 */
	public int i;
	
	/**
	 * A method
	 */
	public void f(){}

	/**
	 * Entry point to class & application
	 * 
	 * @param args args array of string arguments
	 * @throws exceptions No exceptions thrown
	 */
	public static void main(String[] args) {
	    System.out.println("Hello, it's: ");
	    System.out.println(new Date());

		// 打印系统属性到控制台
		System.getProperties().list(System.out);

		System.out.println("*********************************");

		System.out.println(String.format("jdk版本 %s", System.getProperty("java.runtime.version")));
		System.out.println(String.format("系统用户 %s", System.getProperty("user.name")));
		System.out.println(String.format("系统环境变量 %s", System.getProperty("java.library.path")));
	}

}
