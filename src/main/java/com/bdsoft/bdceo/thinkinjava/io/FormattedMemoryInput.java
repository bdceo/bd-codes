/**
 * FormattedMemoryInput.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.io;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

/**
 * 18.6.3——格式化的内存输入
 * 
 * @author bdceo
 * @date 2016-12-27 下午11:45:18
 * @version V1.0
 */
public class FormattedMemoryInput {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		String buf = BufferedInputFile.read("./pom.xml");
		ByteArrayInputStream bain = new ByteArrayInputStream(buf.getBytes());
		DataInputStream in = new DataInputStream(bain);
		while (in.available() != 0) {
			try {
				System.out.print((char) in.readByte());
			} catch (EOFException e) {
				break;
			}
		}
	}

}
