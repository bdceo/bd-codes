package com.bdsoft.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URL;

public class FileUtil {

	public static boolean writeFile(String path, InputStream in, boolean flag) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(path);
			int nextByte = 0;
			while ((nextByte = in.read()) != -1) {
				out.write(nextByte);
			}
			System.out.println("file save success --> " + path);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
				out.flush();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}

	// 把内容写到指定文件中
	public static void writeFile(String path, String content, boolean flag) {
		try {
			File f = new File(path);
			if (f.exists()) {
				System.out.println("文件已存在，内容被覆盖！");
			} else {
				System.out.println("文件不存在，正在创建...");
				if (f.createNewFile()) {
					System.out.println("文件创建成功！");
				} else {
					System.out.println("文件创建失败！");
				}
			}
			BufferedWriter out = new BufferedWriter(new FileWriter(f));
			out.write(content);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getClassPath() {
		URL url = FileUtil.class.getClassLoader().getResource("");
		if (null == url) {
			return "";
		}
		return url.getPath();
	}

	public static void main(String[] args) {

	}
}
