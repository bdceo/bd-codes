package com.bdsoft.bjsxt.exception;


public class Test {
	public static void main(String[] args) {
		try {
			Integer.parseInt("2");
		} catch (Exception se) {
			se.printStackTrace();
		}  
		cal(0);
	}

	public static void cal(int i) {
		if (i == 0) {
			throw new ArithmeticException("除数为零！");
		}
	}
}
