/**
 * MemoryInput.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.io;

import java.io.IOException;
import java.io.StringReader;

/**
 * 18.6.2——从内存输入
 * 
 * @author bdceo
 * @date 2016-12-27 下午11:33:04
 * @version V1.0
 */
public class MemoryInput {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		String buf = BufferedInputFile.read("./pom.xml");
		StringReader in = new StringReader(buf);
		int c;
		while ((c = in.read()) != -1) {
			System.out.print((char) c);
		}
	}

}
