/**
 * BufferToText.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * 18.10.1--转换数据
 * 
 * @author bdceo
 * @date 2016-12-28 下午12:51:53
 * @version V1.0
 */
public class BufferToText {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// 采用默认编码方式写入
		FileChannel fc = new FileOutputStream("d:/btt.txt").getChannel();
		fc.write(ByteBuffer.wrap("hello bdceo".getBytes()));
		fc.close();

		// 第一次读取，出现乱码
		fc = new FileInputStream("d:/btt.txt").getChannel();
		ByteBuffer buf = ByteBuffer.allocate(1024);
		fc.read(buf);
		buf.flip();
		System.out.println(buf.asCharBuffer());
		buf.rewind();

		// 第二次，采用写入是默认文件编码进行解码
		String encoding = System.getProperty("file.encoding");
		CharBuffer decode = Charset.forName(encoding).decode(buf);
		System.out.println(String.format("%s : %s", encoding, decode));

		// 采用指定编码方式写入
		fc = new FileOutputStream("d:/btt.txt").getChannel();
		fc.write(ByteBuffer.wrap("hello bdceo".getBytes("UTF-16BE")));
		fc.close();

		// 第三次，读取正常
		fc = new FileInputStream("d:/btt.txt").getChannel();
		buf.clear();
		fc.read(buf);
		buf.flip();
		System.out.println(buf.asCharBuffer());

		// 采用charbuffer方式写入
		fc = new FileOutputStream("d:/btt.txt").getChannel();
		buf = ByteBuffer.allocate(30);
		buf.asCharBuffer().put("hello bdceo");
		fc.write(buf);
		fc.close();

		// 第四次，直接读取正常
		fc = new FileInputStream("d:/btt.txt").getChannel();
		buf.clear();
		fc.read(buf);
		buf.flip();
		System.out.println(buf.asCharBuffer());
	}

}
