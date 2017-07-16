/**
 * Redirecting.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * 18.8.3--标准I/O重定向
 * 
 * @author bdceo
 * @date 2016-12-28 上午11:33:31
 * @version V1.0
 */
public class Redirecting {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {

		PrintStream console = System.out;

		BufferedInputStream in = new BufferedInputStream(new FileInputStream("d:/pom.txt"));

		PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(
				"d:/pom.log")));

		// System.setIn(in);
		System.setOut(out);
		System.setErr(out);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		while ((line = br.readLine()) != null && line.length() != 0) {
			System.out.println(line);
		}
		out.close();

		System.setOut(console);
	}

}
