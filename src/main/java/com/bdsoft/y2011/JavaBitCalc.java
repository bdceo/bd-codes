package com.bdsoft.y2011;

public class JavaBitCalc {

	public static void main(String[] args) {
		int a = 200, b = 1, c = 257;
		// 0x68 = 0110 1000 = 104
		// 0xff = 1111 1111 = 255
		// 256 = 0001 0000 0000
		// 257 = 0001 0000 0001
		byte st = 0x68;
		System.out.println(st);
		System.out.println(0xff);
		// 200 = 1100 1000 & 1111 1111 = 200
		// 小于等于255的值，并上(&) 0xff 都等于原值
		// 大于256的值，可以减去255后，在计算 并值
		System.out.println(a & 0xff);// 200
		System.out.println(b & 0xff);// 1
		System.out.println(c & 0xff);// 0

		System.out.println(Integer.valueOf("FFFF", 16).toString());// 65535
		System.out.println(1010 >> 7 & 0x01);// 1
		System.out.println(1010 >> 6 & 0x01);// 1
		System.out.println(1010 >> 5 & 0x01);// 1
		System.out.println(1010 >> 4 & 0x01);// 1
		System.out.println(Integer.toHexString(((byte) 68) & 0xff));// 44

		// 255 1111 1111

		// 01 0000 0001

		// 64 0110 0100
		// 100 0110 0100

		// 67 0110 0111
		// 103 0110 0111
		// System.out.println(0xff00);
		// System.out.println(charToByte('B'));
	}

	public static void parseAsdu() {
		// byte[] frame = new byte[] { 68, 0f, 0F, 68, 53, 01, 67, 01, 06, 01,
		// 00,
		// 00, E4, 28, 23, 0F, 01, 02, 09, 0D, 16 };
		// Convert.ToByte();
	}

	// hexString = 4B
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		// 一个十六进制是4位，所以一字节除以2就剩4位了
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;// 两个十六进制存到一个字节，正好8位
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
			// byte before4 = charToByte(hexChars[pos]) << 4;
			// 100(4) --> 100 0000(64) 左移，右边用0补
			// --> 4<<4 4*（2的4次方） == 64
			// byte after4=charToByte(hexChars[pos + 1]); --> 11 -->1011
			// 0100 0000
			// | 0000 1011 (或)
			// --> 0100 1011 （4B）
		}
		return d;
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

}
