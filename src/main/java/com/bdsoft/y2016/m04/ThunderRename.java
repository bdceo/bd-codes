package com.bdsoft.y2016.m04;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 迅雷下载后重命名
 * 
 * http://cn163.net/archives/2046/ http://cn163.net/archives/1636/
 * http://cn163.net/archives/1637/
 */
public class ThunderRename {

	static String basePath = "d:/download/";
	static String destPath = "f:/电视剧/24小时-第9季/";

	static {
		new File(destPath).mkdirs();
	}

	public static void main(String[] args) {

		final String fileExt = "mkv";

		File dir = new File(basePath);
		File[] fs = dir.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				return pathname.getName().endsWith(fileExt);
			}
		});

		// [YYeTs][24][S01E02][CN][HR-HDTV.AC3][800X448][H264].avi
		Pattern pat = Pattern.compile(".*\\.S[\\d]{2}E([\\d]{2})\\..*");

		for (File f : fs) {
			System.out.println("from=" + f.getName());
			Matcher mat = pat.matcher(f.getName());
			if (mat.find()) {
				String dest = formDest(mat.group(1), fileExt);
				System.out.println("dest=" + dest);
				f.renameTo(new File(dest));
			}
		}

	}

	public static String formDest(String name, String ext) {
		return destPath + name + "." + ext;
	}
}
