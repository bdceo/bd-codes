/**
 * Echo.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 18.8.1--从标准输入中读取
 * 
 * @author bdceo
 * @date 2016-12-28 上午11:11:24
 * @version V1.0
 */
public class Echo {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {

		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		while ((line = stdin.readLine()) != null && line.length() != 0) {
			System.out.println(line);
		}

	}

}
