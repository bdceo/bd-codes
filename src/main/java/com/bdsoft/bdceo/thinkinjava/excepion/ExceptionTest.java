package com.bdsoft.bdceo.thinkinjava.excepion;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常测试
 */
public class ExceptionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			f();
		} catch (MyException e) {
			// 输出到错误输出流
			e.printStackTrace();
			// 输出到控制台
			// e.printStackTrace(System.out);
		}
	}

	public static void f() throws MyException {
		System.out.println("execute f()");
		throw new MyException();
	}

}

class MyException extends Exception {

	public MyException() {
		StringWriter writer = new StringWriter();
		printStackTrace(new PrintWriter(writer));
		System.out.println("myout: " + writer.toString());
	}

	public MyException(String msg) {
		super(msg);
	}
}
