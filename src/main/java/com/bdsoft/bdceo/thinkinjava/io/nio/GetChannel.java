/**
 * GetChannel.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 18.10--新I/O
 * 
 * @author bdceo
 * @date 2016-12-28 下午12:22:43
 * @version V1.0
 */
public class GetChannel {

	/**
	 * 演示FileChannel
	 */
	public static void main(String[] args) throws IOException {

		// 写文件
		FileChannel fc = new FileOutputStream("d:/pom.log").getChannel();
		fc.write(ByteBuffer.wrap("i am bdceo".getBytes()));
		fc.close();

		// 文件末尾追加
		fc = new RandomAccessFile("d:/pom.log", "rw").getChannel();
		fc.position(fc.size());
		fc.write(ByteBuffer.wrap("\nEnd.".getBytes()));
		fc.close();

		// 读文件
		fc = new FileInputStream("d:/pom.log").getChannel();
		ByteBuffer buf = ByteBuffer.allocate(1024);
		fc.read(buf);
		buf.flip();
		while (buf.hasRemaining()) {
			System.out.print((char) buf.get());
		}
	}

}
