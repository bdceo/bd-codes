/**
 * BasicFileOutput.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

/**
 * 18.6.4——基本的文件输出
 * 
 * @author bdceo
 * @date 2016-12-28 上午12:03:14
 * @version V1.0
 */
public class BasicFileOutput {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		String file = "d:/pom.xml";
		String buf = BufferedInputFile.read("./pom.xml");
		BufferedReader in = new BufferedReader(new StringReader(buf));

		BufferedWriter bout = new BufferedWriter(new FileWriter(file));
		PrintWriter txtout = new PrintWriter(bout);
		PrintWriter out = new PrintWriter(file);

		int no = 1;
		String line = null;
		while ((line = in.readLine()) != null) {
			out.println((no++) + ": " + line);
		}
		out.close();

		buf = BufferedInputFile.read(file);
		System.out.println(buf);
	}

}
