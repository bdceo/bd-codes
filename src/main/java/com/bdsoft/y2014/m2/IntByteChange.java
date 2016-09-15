package com.bdsoft.y2014.m2;

public class IntByteChange {

	// 测试Int和Byte之间的转换
	public static void main(String[] args) {

		IntByteChange ibc = new IntByteChange();

		int i = -423111104;
		System.out.println("原值=" + i);
		byte[] b = ibc.intTO4byte(i);

		int r = ibc.byte4TOint(b);
		System.out.println("转换=" + r);

	}

	public byte[] intTO1byte(int i) {
		byte[] b = new byte[1];
		b[0] = (byte) i;
		return b;
	}

	// int转byte，将int的高位，按顺序放入byte数组
	public byte[] intTO2byte(int i) {
		byte[] b = new byte[2];
		b[0] = (byte) ((i >> 8) & 0xff);
		b[1] = (byte) (i & 0xff);
		return b;
	}

	public byte[] intTO4byte(int i) {
		byte[] b = new byte[4];
		b[0] = (byte) ((i >> 24) & 0xff);
		b[1] = (byte) ((i >> 16) & 0xff);
		b[2] = (byte) ((i >> 8) & 0xff);
		b[3] = (byte) (i & 0xff);
		return b;
	}

	public int byte1TOint(byte[] b) {
		int i = b[0];
		return i;
	}

	// byte转int，按顺序从byte数组取出，左移，相加每个字节的和
	public int byte2TOint(byte[] b) {
		int i = ((b[0] & 0xff) << 8) + (b[1] & 0xff);
		return i;
	}

	public int byte4TOint(byte[] b) {
		int i = ((b[0] & 0xff) << 24) + ((b[1] & 0xff) << 16)
				+ ((b[2] & 0xff) << 8) + (b[3] & 0xff);
		return i;
	}
}
