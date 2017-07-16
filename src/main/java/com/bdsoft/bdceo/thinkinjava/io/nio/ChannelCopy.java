/**
 * ChannelCopy.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 18.10--新I/O
 * 
 * @author bdceo
 * @date 2016-12-28 下午12:31:36
 * @version V1.0
 */
public class ChannelCopy {

	/**
	 * 演示通过FileChannel文件拷贝
	 */
	public static void main(String[] args) throws IOException {

		FileChannel in = new FileInputStream("d:/tt.txt").getChannel();
		FileChannel out = new FileOutputStream("d:/cc.txt").getChannel();

		ByteBuffer buf = ByteBuffer.allocateDirect(2048);
		while (in.read(buf) != -1) {
			buf.flip();// 准备写，切换缓冲区
			out.write(buf);
			buf.clear();// 准备读，切换缓冲区
		}

	}

}
