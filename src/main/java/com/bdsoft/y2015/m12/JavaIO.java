package com.bdsoft.y2015.m12;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.RandomAccessFile;

/**
 * Java io 测试
 */
public class JavaIO {

	public static void main(String[] args) throws Exception {

		File file = new File("d:/home");

		testFileter(file);

		testOutStream();
		testInStream();

		testWrite();
		testReader();

		testRandomAcc();
	}

	static void testRandomAcc() throws Exception {
		File file = new File("d:/home/2.txt");

		RandomAccessFile raf = new RandomAccessFile(file, "rw");

		raf.seek(3); // 定位读写操作的位置
		raf.writeBytes("xxoo");
		
		raf.seek(raf.length());
		raf.writeBytes("$$$");
		
		raf.seek(0);
		System.out.println(raf.readLine());
		
		raf.seek(raf.length() - 10);
		System.out.println(raf.readLine());

		raf.close();
	}

	static void testReader() throws Exception {
		File file = new File("d:/home/2.txt");
		FileReader fr = new FileReader(file);

		char[] cs = new char[10];
		fr.read(cs);
		System.out.println(new String(cs));

		BufferedReader br = new BufferedReader(fr, 10);
		String line = br.readLine();
		while (line != null) {
			System.out.println(line);
			line = br.readLine();
		}

		br.close();

		fr.close();
	}

	static void testWrite() throws Exception {
		File file = new File("d:/home/2.txt");
		// file.delete();
		// 重载构造函数：true，不覆盖，追加
		FileWriter fw = new FileWriter(file, true);

		fw.write("FileInputStream in = new FileInputStream(file);");

		BufferedWriter bw = new BufferedWriter(fw, 10);
		bw.newLine();
		bw.write("BufferedWriter bw = new BufferedWriter(fw);");

		bw.close();

		fw.close();
	}

	static void testInStream() throws Exception {
		File file = new File("d:/home/1.txt");
		if (!file.exists()) {
			return;
		}
		FileInputStream in = new FileInputStream(file);
		byte[] line = new byte[in.available()];
		in.read(line);
		System.out.println(new String(line));
		in.close();

		System.out.println("--------------");
		in = new FileInputStream(file);
		// 按字节读取
		byte[] b = new byte[10];
		while (in.read(b) > 0) {
			// System.out.println(in.available());
			System.out.println(new String(b));
			if (in.available() < b.length) {
				byte[] left = new byte[in.available()];
				in.read(left);
				System.out.println(new String(left));
				break;
			}
		}

		in.close();
	}

	static void testOutStream() throws Exception {
		File file = new File("d:/home/1.txt");
		file.delete();

		FileOutputStream fos = new FileOutputStream(file);

		fos.write("FileOutputStream fos = new FileOutputStream(file);"
				.getBytes());
		fos.write("\r\n".getBytes());
		fos.write("public static void main(String[] args) throws Exception {"
				.getBytes());

		fos.close();
	}

	// 文件过滤
	static void testFileter(File file) {
		String[] fs1 = file.list(new BDFilenameFilter());
		for (String f : fs1) {
			System.out.println(f);
		}
		File[] fs2 = file.listFiles(new BDFileFilter());
		for (File f : fs2) {
			System.out.println(f.getAbsolutePath());
		}
	}

	// 文件名过滤器
	public static class BDFilenameFilter implements FilenameFilter {

		public boolean accept(File dir, String name) {
			return name.endsWith("png");
		}

	}

	// 文件过滤器
	public static class BDFileFilter implements FileFilter {

		public boolean accept(File pathname) {
			return pathname.getName().endsWith("txt");
		}

	}

}
