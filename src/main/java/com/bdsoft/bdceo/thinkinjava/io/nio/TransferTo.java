/**
 * TransferTo.java
 * bd-codes
 * Copyright (c) 2016, bdsoft版权所有.
*/
package com.bdsoft.bdceo.thinkinjava.io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 18.10--新I/O
 * 
 * @author bdceo
 * @date 2016-12-28 下午12:36:29
 * @version V1.0
 */
public class TransferTo {

	/**
	 * 演示通过 transferTo/ transferFrom 文件拷贝
	 */
	public static void main(String[] args) throws IOException {

		FileChannel in = new FileInputStream("d:/tt.txt").getChannel();
		FileChannel out = new FileOutputStream("d:/cc.txt").getChannel();
		
		in.transferTo(0, in.size(), out);
//		out.transferFrom(in, 0, in.size());
	}

}
