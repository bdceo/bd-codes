/**
 * BufferedInputFile.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 18.6.1——缓冲输入文件
 * 
 * @author bdceo
 * @date 2016-12-27 下午11:26:06
 * @version V1.0
 */
public class BufferedInputFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		String inp ="/Users/bdceo/home/xxx.txt";
		String content = read(inp);
//		System.out.println(content);
		
		content = new String(content.getBytes("GB2312"),"UTF-8");
		System.out.println(content);
	}

	static String read(String fileName) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		String line = null;
		while ((line = in.readLine()) != null) {
			sb.append(line).append("\n");
		}
		in.close();
		return sb.toString();
	}

}
