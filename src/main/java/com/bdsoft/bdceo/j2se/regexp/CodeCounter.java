package com.bdsoft.bdceo.j2se.regexp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CodeCounter {

	static long normalLines = 0;

	static long commentLines = 0;

	static long whiteLines = 0;

	public static void main(String[] args) {
		File f = new File(
				"D:\\Test\\MyEclipse\\JavaWebPro\\Pro01-03\\src\\ch04\\servlet\\");
		File[] codeFiles = f.listFiles();
		for (File child : codeFiles) {
			if (child.getName().matches(".*\\.java$")) {
				parse(child);
			}
		}
		System.out.println("normalLines:" + normalLines);
		System.out.println("commentLines:" + commentLines);
		System.out.println("whiteLines:" + whiteLines);
	}

	private static void parse(File f) {
		BufferedReader br = null;
		boolean comment = false;
		try {
			br = new BufferedReader(new FileReader(f));
			String line = "";
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (line.matches("^[\\s&&[^\\n]]*$")) {
					whiteLines++;// empty row
				} else if (line.startsWith("/*") && !line.endsWith("*/")) {
					commentLines++;// comment row
					comment = true;
				} else if (true == comment) {
					commentLines++;
					if (line.endsWith("*/")) {
						comment = false;
					}
				} else if (line.startsWith("//")) {
					commentLines++;
				} else {
					normalLines++;// code row
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
