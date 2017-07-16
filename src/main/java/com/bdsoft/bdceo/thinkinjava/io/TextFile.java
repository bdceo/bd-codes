/**
 * TextFile.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * 18.7——文件读写的实用工具
 * 
 * @author bdceo
 * @date 2016-12-28 上午10:43:37
 * @version V1.0
 */
public class TextFile extends ArrayList<String> {

	public TextFile(String fileName, String splitter) {
		super(Arrays.asList(read(fileName).split(splitter)));
		if (get(0).equals(""))
			remove(0);
	}

	public TextFile(String fileName) {
		this(fileName, "\n");
	}

	public void write(String fileName) {
		try {
			PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
			try {
				for (String item : this) {
					out.print(item);
				}
			} finally {
				out.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String file = read("./pom.xml");
		write("d:/pom.txt", file);

		TextFile txt = new TextFile("d:/pom.txt");
		txt.write("d:/pom.log");

		TreeSet<String> words = new TreeSet<String>(new TextFile("d:/pom.log", "\\W+"));
		System.out.println(words.headSet("4"));
	}

	static String read(String fileName) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					new File(fileName).getAbsoluteFile()));
			try {
				String line = null;
				while ((line = in.readLine()) != null) {
					sb.append(line).append("\n");
				}
			} finally {
				in.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return sb.toString();
	}

	static void write(String fileName, String content) {
		try {
			PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
			try {
				out.print(content);
			} finally {
				out.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
