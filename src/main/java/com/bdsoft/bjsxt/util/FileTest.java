package com.bdsoft.bjsxt.util;

import java.io.File;

public class FileTest {

	public static void main(String[] args) {
		File file = new File("c:/temp");
		System.out.println(file.isDirectory());
		System.out.println(file.lastModified());

		String separator = File.separator;
		String fileName = "a.txt";
		String directory = "c:" + separator + "temp";
		File f = new File(directory, fileName);
		if (f.exists()) {
			System.out.println(f.getAbsolutePath());
			System.out.println(f.length());
		} else {
			f.getParentFile().mkdirs();
			try {
				f.createNewFile();
			} catch (Exception se) {
				se.printStackTrace();
			}
		}

		System.out.println("-------------------");
		File top = new File("G:\\FastTV_1.4.7.0");
		if (top.isDirectory()) {
			File[] files = top.listFiles();
			listFile(files, 0);
		} else {
			System.exit(-1);
		}
	}

	public static void listFile(File[] fs, int level) {
		String preStr = "";
		for (int i = 0; i < level; i++) {
			preStr += "  ";
		}
		int len = fs.length;
		for (int i = 0; i < len; i++) {
			System.out.println(preStr + fs[i].getName());
			if (fs[i].isDirectory()) {
				System.out.println(preStr + fs[i].getName());
				listFile(fs[i].listFiles(), level + 1);
			}
		}
	}
}
