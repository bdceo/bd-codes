package com.bdsoft.utils;

import java.io.File;

/**
 * 清除项目中的.svn版本文件
 */
public class RmSvnFiles {

//	static String DEL_DIR = ".git";
	static String DEL_DIR = "target";

	public static void main(String[] args) {
		String path = "e:/juranspace";

		ls4rmf(new File(path));
	}

	/**
	 * 递归找到.svn目录执行删除
	 */
	public static void ls4rmf(File dir) {
		File[] files = dir.listFiles();
		for (File f : files) {
			if (f.isFile()) {
				continue;
			}
			System.out.println(">>" + f.getAbsolutePath());
			if (f.getName().equals(DEL_DIR)) {
				rmf(f);
				f.deleteOnExit();
				continue;
			}
			if (f.isDirectory()) {
				ls4rmf(f);
			}
		}
	}

	/**
	 * 递归删除文件夹下的文件
	 */
	public static void rmf(File file) {
		File[] files = file.listFiles();
		if (files != null && files.length > 0) {
			for (File f : files) {
				if (f.isFile()) {
					System.out.println("--" + (f.isFile() ? " f " : " d ") + f.getName());
					f.delete();
					continue;
				} else {
					rmf(f);
				}
				System.out.println("--" + (f.isFile() ? " f " : " d ") + f.getName());
				f.delete();
			}
		}
	}

}
