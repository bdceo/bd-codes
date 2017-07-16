/**
 * GetData.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.io.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

/**
 * 18.10.2--获取基本类型
 * 
 * @author bdceo
 * @date 2016-12-28 下午10:01:48
 * @version V1.0
 */
public class GetData {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		ByteBuffer bf = ByteBuffer.allocate(1024);
		int i = 0;
		while (i++ < bf.limit()) {
			if (bf.get() != 0) {
				System.out.println("非0");
			}
		}
		System.out.println("i=" + i);
		bf.rewind();

		bf.asCharBuffer().put("bdceo");
		char c;
		while ((c = bf.getChar()) != 0) {
			System.out.print(c);
		}
		System.out.println();
		bf.rewind();

		bf.asShortBuffer().put((short) 1228);
		System.out.println(bf.getShort());
		bf.rewind();

		bf.asIntBuffer().put(1988);
		System.out.println(bf.getInt());
		bf.rewind();

		bf.asLongBuffer().put(198805170790L);
		System.out.println(bf.getLong());
		bf.rewind();

		// 试图缓冲器
		intBufferDemo();

		viewBuffers();
	}

	static void intBufferDemo() {
		System.out.println("---------------------");
		ByteBuffer bf = ByteBuffer.allocate(1024);
		IntBuffer ib = bf.asIntBuffer();

		ib.put(new int[] { 11, 42, 47, 99, 143, 811, 1016 });
		System.out.println(ib.get(1));

		ib.put(4, 1988);
		ib.flip();

		while (ib.hasRemaining()) {
			System.out.println(ib.get());
		}

	}

	static void viewBuffers() {
		System.out.println("---------------------");
		ByteBuffer bf = ByteBuffer.wrap(new byte[] { 0, 0, 0, 0, 0, 0, 0, 'a' });
		bf.rewind();
		System.out.println("ByteBuffer");
		while (bf.hasRemaining()) {
			System.out.println(bf.position() + " -> " + bf.get());
		}
		bf.rewind();
		System.out.println();

		CharBuffer cb = bf.asCharBuffer();
		System.out.println("CharBuffer");
		while (cb.hasRemaining()) {
			System.out.println(cb.position() + " -> " + cb.get());
		}
		bf.rewind();
		System.out.println();

		ShortBuffer sb = bf.asShortBuffer();
		System.out.println("ShortBuffer");
		while (sb.hasRemaining()) {
			System.out.println(sb.position() + " -> " + sb.get());
		}
		bf.rewind();
		System.out.println();

		IntBuffer ib = bf.asIntBuffer();
		System.out.println("IntBuffer");
		while (ib.hasRemaining()) {
			System.out.println(ib.position() + " -> " + ib.get());
		}
		bf.rewind();
		System.out.println();

		FloatBuffer fb = bf.asFloatBuffer();
		System.out.println("FloatBuffer");
		while (fb.hasRemaining()) {
			System.out.println(fb.position() + " -> " + fb.get());
		}
		bf.rewind();
		System.out.println();
		
		DoubleBuffer db = bf.asDoubleBuffer();
		System.out.println("DoubleBuffer");
		while (db.hasRemaining()) {
			System.out.println(db.position() + " -> " + db.get());
		}
		bf.rewind();
		System.out.println();

		LongBuffer lb = bf.asLongBuffer();
		System.out.println("LongBuffer");
		while (lb.hasRemaining()) {
			System.out.println(lb.position() + " -> " + lb.get());
		}
		bf.rewind();
		System.out.println();

	}

}
