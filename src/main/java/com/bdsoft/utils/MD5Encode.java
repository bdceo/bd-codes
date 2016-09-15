package com.bdsoft.utils;

import java.security.MessageDigest;

public class MD5Encode {

	public static String encode(String sourceString) {
		String resultString = null;
		try {
			resultString = new String(sourceString);
			MessageDigest md = MessageDigest.getInstance("MD5");

			resultString = byte2hexString(md.digest(resultString
					.getBytes("UTF-8")));
		} catch (Exception ex) {
		}
		return resultString;
	}

	private static final String byte2hexString(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if ((bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString(bytes[i] & 0xff, 16));
		}
		return buf.toString();
	}

	// 加密测试
	public static void main(String[] args) {
		System.out.println("md5 is: " + MD5Encode.encode("bdceo"));
	}
}
