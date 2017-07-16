/**
 * ChangeSystemOut.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.io;

import java.io.PrintWriter;

/**
 * 18.8.2--将System.out转化成PrintWriter
 * 
 * @author bdceo
 * @date 2016-12-28 上午11:21:30
 * @version V1.0
 */
public class ChangeSystemOut {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PrintWriter out = new PrintWriter(System.out, true);
		out.println("i am bdceo");
	}

}
