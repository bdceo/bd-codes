package com.bdsoft.y2011.m03;

public class IntTOByteArr {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int i = 13223;
		// System.out.println(Integer.toHexString(i));
		// System.out.println(0xff);
		byte[] b = new byte[4];
		b[3] = (byte) (13223 & 0xff);
		i = i >> 8;
		b[2] = (byte) (i & 0xff);
		i = i >> 8;
		b[1] = (byte) (i & 0xff);
		i = i >> 8;
		b[0] = (byte) (i & 0xff);
		for (byte bb : b) {
			System.out.println(bb + " : " + Integer.toHexString(bb));
		}
		System.out.println("-------------------------");
		byte[] b3 = intToBytes2(13223);
		for (byte b3b : b3) {
			System.out.println(b3b);
		}
	}

	// 将int数值装换成byte数组
	public static byte[] intToBytes2(int n) {
		byte[] b = new byte[4];
		for (int i = 0; i < 4; i++) {
			b[i] = (byte) (n >> (24 - i * 8));
		}
		return b;
	}

}
