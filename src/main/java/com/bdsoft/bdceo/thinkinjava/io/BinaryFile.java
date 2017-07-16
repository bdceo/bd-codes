/**
 * BinaryFile.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 18.7.1——读取二进制文件
 * 
 * @author bdceo
 * @date 2016-12-28 上午11:05:17
 * @version V1.0
 */
public class BinaryFile {

	public static byte[] read(String bFile) throws IOException {
		return read(new File(bFile).getAbsoluteFile());
	}

	public static byte[] read(File bFile) throws IOException {
		BufferedInputStream bf = new BufferedInputStream(new FileInputStream(bFile));
		try {
			byte[] data = new byte[bf.available()];
			bf.read(data);
			return data;
		} finally {
			bf.close();
		}
	}

}
