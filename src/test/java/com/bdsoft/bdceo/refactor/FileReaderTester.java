/**
 * FileReaderTester.java
 * com.bdsoft.bdceo.refactor
 * Copyright (c) 2014, 北京微课创景教育科技有限公司版权所有.
*/
package com.bdsoft.bdceo.refactor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import junit.framework.TestCase;

/**
 * 
 * @author	丁辰叶
 * @date	2014-11-12
 */
public class FileReaderTester extends TestCase {

	private FileReader _file;

	public FileReaderTester(String name) {
		super(name);
	}

	@Override
	protected void setUp() throws Exception {
		try {
			_file = new FileReader("d:/download/data.txt");
		} catch (FileNotFoundException e) {
			throw new RuntimeException("unable to open test file");
		}
	}

	/**
	 * 测试文件读取
	 * @throws IOException
	 */
	public void testRead() throws IOException {
		char ch = '&';
		//		_file.close();
		for (int i = 0; i < 4; i++) {
			ch = (char) _file.read();
		}
		assertEquals(ch, 'd');
	}

	/**
	 * 测试文件读取末尾字符
	 * @throws IOException
	 */
	public void testReadAtEnd() throws IOException {
		for (int i = 0; i < 7; i++) {
			_file.read();
		}
		assertEquals(-1, _file.read());
	}

	/**
	 * 测试文件读取边界
	 * @throws IOException
	 */
	public void testReadBoundaries() throws IOException {
		assertEquals("read first char", 'B', _file.read());
		for (int i = 0; i < 5; i++) {
			_file.read();
		}
		assertEquals("read last char", 'm', _file.read());
		assertEquals(-1, _file.read());
	}

	@Override
	protected void tearDown() throws Exception {
		try {
			_file.close();
		} catch (IOException e) {
			throw new RuntimeException("error on closing test file");
		}
	}

}
