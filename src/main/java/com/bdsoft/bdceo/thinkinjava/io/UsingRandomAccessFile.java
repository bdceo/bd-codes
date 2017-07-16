/**
 * UsingRandomAccessFile.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.io;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * UsingRandomAccessFile
 * 
 * @author bdceo
 * @date 2016-12-28 上午12:31:17
 * @version V1.0
 */
public class UsingRandomAccessFile {

	static String file = "d:/pom.xml";

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		RandomAccessFile rf = new RandomAccessFile(file, "rw");
		for (int i = 0; i < 7; i++) {
			rf.writeDouble(i * 1.414);
		}
		rf.writeUTF("End");
		rf.close();
		display();

		rf = new RandomAccessFile(file, "rw");
		rf.seek(5 * 8); // double 占8字节，修改第五个数值
		rf.writeDouble(1988.0517);
		rf.close();
		display();
	}

	static void display() throws IOException {
		System.out.println("\n");
		RandomAccessFile rf = new RandomAccessFile(file, "r");
		for (int i = 0; i < 7; i++) {
			System.out.println(String.format("Value %d : %f", i, rf.readDouble()));
		}
		System.out.println(rf.readUTF());
		rf.close();
	}
}
