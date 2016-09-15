package com.bdsoft.y2011.m05;

public class ByteArr {

	public static void main(String[] args) {

		byte[] b = new byte[] { 0x21, 0x43 };
		int tmp = 0;
		// 0x21=2*16+1=33 + 0x43=4*16+3=67 = 100
		tmp = (0x21 & 0xff) + (0x43 & 0xff);
		ps(tmp);

		for (int i = 0; i < b.length - 1; i++) {
			tmp = (b[i] & 0xff) * 0xff + (b[i + 1] & 0xff);
			ps(tmp);
		}

	}

	public static void ps(int i) {
		System.out.println(i);
	}

	public static void ps(String str) {
		System.out.println(str);
	}

	public static void ps(boolean b) {
		System.out.println(b);
	}

}
