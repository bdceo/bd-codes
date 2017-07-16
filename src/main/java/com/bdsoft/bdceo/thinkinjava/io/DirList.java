/**
 * DirList.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * 文件目录过滤
 * 
 * @author bdceo
 * @date 2016-12-27 下午9:45:42
 * @version V1.0
 */
public class DirList {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		File path = new File(".");
		String[] list;
		if (args.length == 0) {
			list = path.list();
		} else {
			list = path.list(new FilenameFilter() {
				private Pattern pat = Pattern.compile(args[0]);

				@Override
				public boolean accept(File dir, String name) {
					return pat.matcher(name).matches();
				}
			});
		}

		Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
		for (String dir : list) {
			System.out.println(dir);
		}
	}

}
