/**
 * StoringAndRecoveringData.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 18.6.5——存储和恢复数据
 * 
 * @author bdceo
 * @date 2016-12-28 上午12:19:19
 * @version V1.0
 */
public class StoringAndRecoveringData {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		String file = "d:/pom.xml";
		BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(file));
		DataOutputStream out = new DataOutputStream(bout);

		out.writeBoolean(true);
		out.writeDouble(2016.1228);
		out.writeUTF("bdceo");
		out.writeDouble(2016.0021);
		out.writeUTF("shanghai");
		out.close();

		BufferedInputStream bin = new BufferedInputStream(new FileInputStream(file));
		DataInputStream in = new DataInputStream(bin);

		System.out.println(in.readBoolean());
		System.out.println(in.readDouble());
		System.out.println(in.readUTF());
		System.out.println(in.readDouble());
		System.out.println(in.readUTF());
	}

}
