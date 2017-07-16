package com.bdsoft.bdceo.j2se.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOTest {

	private static final String TEST_FILE = "d:/download/xx.txt";
	private static final String DEST_FILE = "d:/download/dest.txt";

	public static void main(String[] args) throws Exception {
		// testFileInputStream();
		// testFileOutputStream();
		// testReader();
		// testWriter();
		// testBufferedStream();
		// testBufferedReader();

		File f = new File("/home/dcy/xx");
		System.out.println(f.exists());
		FileInputStream in = new FileInputStream(f);
		FileOutputStream out = new FileOutputStream("/home/dcy/cp");
		byte[] tmp = new byte[1024];
		int rc = 0;
		while ((rc = in.read(tmp)) != -1) {
			out.write(tmp, 0, rc);
		}
		out.flush();

	}

	// IO，基本概念及相关常用类演示
	public static void baseIO() throws Exception {
		// 字节流和字符流顶层接口：
		// 字节流:InputStream,OutputStream
		// 字符流：Reader，Writer

		// 节点流分析
		// 字节流：FileInputStream，ByteArrayInputStream
		// 字符流：FileReader，CharArrayInputStream
		FileInputStream ifs = new FileInputStream(TEST_FILE);
		FileOutputStream iof = new FileOutputStream(TEST_FILE);
		ByteArrayInputStream bais = new ByteArrayInputStream(new byte[1024]);

		FileReader fr = new FileReader(TEST_FILE);
		CharArrayReader car = new CharArrayReader(new char[1024]);

		// 处理流分析
		// 字节流：BufferedInputStream
		// 字符流：BufferedReader,InputStreamReader
		BufferedInputStream bis = new BufferedInputStream(ifs);

		BufferedReader br = new BufferedReader(fr);
		InputStreamReader isr = new InputStreamReader(ifs);// 将字节流转换为字符流
	}

	// 文件字节输入流 测试
	public static void testFileInputStream() {
		FileInputStream in = null;
		try {
			in = new FileInputStream(TEST_FILE);
			int b = 0;
			long bc = 0l;
			while ((b = in.read()) != -1) {
				bc++;
				System.out.print((byte) b);
				if (b % 20 == 0) {
					System.out.println();
				}
			}
			System.out.println("\nfile " + TEST_FILE + ", read total byte = " + bc);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	// 文件字节输入输出流 测试:文件内容复制
	public static void testFileOutputStream() {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(TEST_FILE);
			fos = new FileOutputStream(DEST_FILE);
			int buf = 0;
			while ((buf = fis.read()) != -1) {
				fos.write(buf);
			}
			System.out.println("file content copy finish!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				fos.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	// 文件输入字符流 测试
	public static void testReader() {
		FileReader fr = null;
		try {
			fr = new FileReader(TEST_FILE);
			int c = 0;
			long cc = 0l;
			while ((c = fr.read()) != -1) {
				System.out.print((char) c);
				cc++;
				if (cc % 20 == 0) {
					System.out.println();
				}
			}
			System.out.println("file " + TEST_FILE + ", read total char = " + cc);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fr.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	// 文件输入输出字符流测试：文件内容复制
	public static void testWriter() {
		FileReader fr = null;
		FileWriter fw = null;
		try {
			fr = new FileReader(TEST_FILE);
			fw = new FileWriter(DEST_FILE);
			int c = 0;
			while ((c = fr.read()) != -1) {
				fw.write(c);
			}
			System.out.println("file copy finish (by char)");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fr.close();
				fw.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	// 缓冲字节流 测试
	public static void testBufferedStream() {
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		try {
			fis = new FileInputStream(TEST_FILE);
			bis = new BufferedInputStream(fis);

			int c = 0;
			System.out.println((byte) bis.read());
			System.out.println((byte) bis.read());

			System.out.println();
			bis.mark(10);
			c = bis.read();
			for (int i = 0; i < 10 && c != -1; i++) {
				System.out.print((byte) c + " ");
				c = bis.read();
			}

			bis.reset();
			System.out.println();
			c = bis.read();
			for (int i = 0; i < 10 && c != -1; i++) {
				System.out.print((byte) c + " ");
				c = bis.read();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				bis.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	// 缓冲字符流 测试：文件内容复制
	public static void testBufferedReader() {
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new FileReader(TEST_FILE));
			bw = new BufferedWriter(new FileWriter(DEST_FILE));

			String line = null;

			line = br.readLine();
			while (line != null) {
				System.out.println(line);
				bw.write(line);
				// 开启新的一行
				bw.newLine();

				line = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				bw.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
